package main.java.markovmodelling;

import main.java.matrices.DMatrix;
import main.java.matrices.util.MatMaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class DiscreteTimeMarkovModel {
	private String currentState;
	
	private HashMap<String, Integer> stateIndexes = new HashMap<String, Integer>();
	private DMatrix stochasticMatrix;
	
	public DiscreteTimeMarkovModel() {
		stochasticMatrix = new DMatrix();
	}
	
	public DMatrix getAsStochasticMatrix() {
		return stochasticMatrix;
	}
	
	public String[] getStates() {
		return stateIndexes.keySet().toArray(new String[stateIndexes.size()]);
	}
	
	public void addState(String stateName) {
		stateIndexes.put(stateName, stateIndexes.size());
		
		/**
		if (stochasticMatrix == null)
		{
			Double[][] values = {{0.0}};
			stochasticMatrix = new DMatrix(values);
			return;
		}
		*/
		
		Double[] columnValues = new Double[stateIndexes.size()-1];
		Double[] rowValues = new Double[stateIndexes.size()];
		Arrays.fill(columnValues, 0.0);
		Arrays.fill(rowValues, 0.0);
		
		stochasticMatrix.addColumn(columnValues);
		stochasticMatrix.addRow(rowValues);
	}
	
	public void removeState(String stateName) {
		int stateIndex = stateIndexes.get(stateName);
		
		stochasticMatrix.deleteColumns(stateIndex, stateIndex+1);
		stochasticMatrix.deleteRows(stateIndex, stateIndex+1);
		
		stateIndexes.remove(stateName);
	}
	
	public void setTransitionProbability(String sourceState, String destinationState, double probability) {
		int rowIndex = stateIndexes.get(sourceState);
		int columnIndex = stateIndexes.get(destinationState);
		stochasticMatrix.set(rowIndex, columnIndex, probability);
	}
	
	public double getTransitionProbability(String sourceState, String destinationState) {
		int rowIndex = stateIndexes.get(sourceState);
		int columnIndex = stateIndexes.get(destinationState);
		return stochasticMatrix.get(rowIndex, columnIndex);
	}
	
	public RandomWalkResults performRandomWalk(String startingState, int numberOfSteps) throws Exception {
		if (!areAllProbabilitiesValid())
			throw new Exception("Transition probabilities are invalid.");
		
		if (!stateIndexes.containsKey(startingState))
			throw new Exception("Starting state is invalid.");
		
		Random random = new Random();
		currentState = startingState;
		String[] statesForEachStep = new String[numberOfSteps];
		
		for (int step = 0; step < numberOfSteps; step++) {
			int currentStateRowIndex = stateIndexes.get(currentState);
			double randomDouble = random.nextDouble();
			double accumulatedProbabilities = 0.0;
			
			statesForEachStep[step] = currentState;
			
			if (step >= numberOfSteps-1)
				break;
			
			for (int i = 0; i < stateIndexes.size(); i++) {
				accumulatedProbabilities += stochasticMatrix.get(currentStateRowIndex, i);
				if (randomDouble <= accumulatedProbabilities) {
					currentState = getStateByIndex(i);
					break;
				}
			}
		}
		
		return new RandomWalkResults(getStates(), statesForEachStep);
	}
	
	public StationaryDistribution getStationaryDistribution(String startingState) throws Exception {
		if (!hasStationaryDistribution())
			throw new Exception("The markov model does not have a stochastic distribution.");
		
		int startingStateIndex = stateIndexes.get(startingState);
		Double[][] operationInputValues = new Double[1][stateIndexes.size()];
		
		for (int i = 0; i < stateIndexes.size(); i++) {
			double value = 0.0;
			if (i == startingStateIndex)
				value = 1.0;
			operationInputValues[0][i] = value;
		}
		
		DMatrix operationInput = new DMatrix(operationInputValues);
		DMatrix operationOutput = null;
		
		while (operationOutput == null || !operationInput.equalTo(operationOutput))
			operationOutput = MatMaths.mul(operationInput, stochasticMatrix);
		
		HashMap<String, Double> stateDistributions = new HashMap<String, Double>();
		
		for (int i = 0; i < stateIndexes.size(); i++)
			stateDistributions.put(getStateByIndex(i),  operationOutput.get(i, 0));
		
		return new StationaryDistribution(stateDistributions);
	}
	
	public boolean hasStationaryDistribution() {
		if (!areAllProbabilitiesValid())
			return false;
		
		double[] eigenvalues = MatMaths.eigenvaluesOf(stochasticMatrix);
		for (int i = 0; i < eigenvalues.length; i++) {
			double eigenvalue = eigenvalues[i];
			if (Math.abs(eigenvalue - 1.0) < 1e-6)
				return true;
		}
		
		return false;
	}
	
	public String[] getTransientStates() {
		ArrayList<String> transientStates = new ArrayList<String>();
		
		Set<String> recurrentStates = new HashSet<>(Arrays.asList(getRecurrentStates()));

        for (String state : getStates()) {
            if (!recurrentStates.contains(state))
                transientStates.add(state);
        }
		
		return transientStates.toArray(new String[transientStates.size()]);
	}
	
	public String[] getRecurrentStates() {
		ArrayList<String> recurrentStates = new ArrayList<String>();
		
		ArrayList<HashSet<String>> communicatingClasses = findCommunicatingClasses();
        
        for (HashSet<String> communicatingClass : communicatingClasses) {
            if (containsRecurrentState(communicatingClass))
                recurrentStates.addAll(communicatingClass);
        }
		
		return recurrentStates.toArray(new String[recurrentStates.size()]);
	}
	
	private ArrayList<HashSet<String>> findCommunicatingClasses() {
        ArrayList<HashSet<String>> classes = new ArrayList<>();

        Set<String> visitedStates = new HashSet<>();

        for (String state : getStates()) {
            if (!visitedStates.contains(state)) {
                HashSet<String> communicatingClass = new HashSet<>();
                depthFirstSearch(state, visitedStates, communicatingClass);
                classes.add(communicatingClass);
            }
        }

        return classes;
    }

    private void depthFirstSearch(String state, Set<String> visitedStates, Set<String> communicatingClass) {
        visitedStates.add(state);
        communicatingClass.add(state);

        for (String nextState : getStates()) {
            if (stochasticMatrix.get(stateIndexes.get(state), stateIndexes.get(nextState)) > 0 && !visitedStates.contains(nextState))
                depthFirstSearch(nextState, visitedStates, communicatingClass);
        }
    }

    private boolean containsRecurrentState(Set<String> communicatingClass) {
        for (String state : communicatingClass) {
            if (isRecurrentState(state))
                return true;
        }
        return false;
    }

    private boolean isRecurrentState(String state) {
        int stateIndex = stateIndexes.get(state);
        return stochasticMatrix.get(stateIndex, stateIndex) > 0;
    }
	
	private String getStateByIndex(Integer stateIndex) {
		for (Entry<String, Integer> entry : stateIndexes.entrySet()) {
			if (Objects.equals(stateIndex, entry.getValue()))
				return entry.getKey();
		}
		return null;
	}
	
	private boolean areAllProbabilitiesValid() {
		int numberOfStates = stateIndexes.size();
		for (int i = 0; i < numberOfStates; i++) {
			double totalProbability = 0.0;
			for (int j = 0; j < numberOfStates; j++)
				totalProbability += stochasticMatrix.get(i, j);
			if (totalProbability != 1.0)
				return false;
		}
		return true;
	}
}
