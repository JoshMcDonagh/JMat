package main.java.markovmodelling;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class RandomWalkResults {
	private String[] statesForEachStep;
	
	private HashMap<String, Integer> stateFrequencies = new HashMap<String, Integer>();
	private HashMap<String, Double> stateProbabilities = new HashMap<String, Double>();
	
	RandomWalkResults(String[] stateNames, String[] statesForEachStep) {
		this.statesForEachStep = statesForEachStep;
		
		for (String state : stateNames) {
			int stateFrequency = Collections.frequency(Arrays.asList(statesForEachStep), state);
			double stateProbability = stateFrequency / statesForEachStep.length;
			stateFrequencies.put(state, stateFrequency);
			stateProbabilities.put(state, stateProbability);
		}
	};
	
	public String[] getStatesForEachStep() {
		return statesForEachStep;
	}
	
	public int getStateFrequency(String stateName) {
		return stateFrequencies.get(stateName);
	}
	
	public double getStateProbability(String stateName) {
		return stateProbabilities.get(stateName);
	}
}
