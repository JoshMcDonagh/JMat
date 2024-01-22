package main.java.markovmodelling;

import main.java.matrices.DMatrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;

public class MarkovModel {
	private String currentState;
	
	private HashMap<String, Integer> stateIndexes = new HashMap<String, Integer>();
	private DMatrix stochasticMatrix = null;
	
	public DMatrix getAsStochasticMatrix() {
		return stochasticMatrix;
	}
	
	public void addState(String stateName) {
		stateIndexes.put(stateName, stateIndexes.size());
		
		if (stochasticMatrix == null)
		{
			Double[][] values = {{0.0}};
			stochasticMatrix = new DMatrix(values);
			return;
		}
		
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
	
	public void addTransition(String sourceState, String destinationState, double probability) {
		int rowIndex = stateIndexes.get(sourceState);
		int columnIndex = stateIndexes.get(destinationState);
		stochasticMatrix.set(rowIndex, columnIndex, probability);
	}
	
	public MarkovModelResults run(String startingState, int numberOfRuns) throws Exception {
		if (!areProbabilitiesValid())
			throw new Exception("Transition probabilities are invalid.");
		
		if (!stateIndexes.containsKey(startingState))
			throw new Exception("Starting state is invalid.");
		
		Random random = new Random();
		currentState = startingState;
		String[] statesForEachTurn = new String[numberOfRuns];
		
		for (int run = 0; run < numberOfRuns; run++) {
			int currentStateRowIndex = stateIndexes.get(currentState);
			double randomDouble = random.nextDouble();
			double accumulatedProbabilities = 0.0;
			
			statesForEachTurn[run] = currentState;
			
			if (run >= numberOfRuns-1)
				break;
			
			for (int i = 0; i < stateIndexes.size(); i++) {
				accumulatedProbabilities += stochasticMatrix.get(currentStateRowIndex, i);
				if (randomDouble <= accumulatedProbabilities) {
					currentState = getStateByIndex(i);
					break;
				}
			}
		}
		
		return new MarkovModelResults(statesForEachTurn);
	}
	
	private String getStateByIndex(Integer stateIndex) {
		for (Entry<String, Integer> entry : stateIndexes.entrySet()) {
			if (Objects.equals(stateIndex, entry.getValue()))
				return entry.getKey();
		}
		return null;
	}
	
	private boolean areProbabilitiesValid() {
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
