package main.java.markovmodelling;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MarkovModelResults {
	private String[] statesForEachStep;
	
	private HashMap<String, Double> stateProbabilities = new HashMap<String, Double>();
	
	MarkovModelResults(String[] stateNames, String[] statesForEachStep) {
		this.statesForEachStep = statesForEachStep;
		
		for (String state : stateNames) {
			double stateProbability = Collections.frequency(Arrays.asList(statesForEachStep), state) / statesForEachStep.length;
			stateProbabilities.put(state, stateProbability);
		}
	};
	
	public String[] getStatesForEachStep() {
		return statesForEachStep;
	}
	
	public double getStateProbability(String stateName) {
		return stateProbabilities.get(stateName);
	}
}
