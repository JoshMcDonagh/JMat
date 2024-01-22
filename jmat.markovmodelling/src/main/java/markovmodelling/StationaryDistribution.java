package main.java.markovmodelling;

import java.util.HashMap;

public class StationaryDistribution {
	private HashMap<String, Double> stateDistributions = new HashMap<String, Double>();
	
	StationaryDistribution(HashMap<String, Double> stateDistributions) {
		this.stateDistributions = stateDistributions;
	}
	
	public double getStateDistribution(String stateName) {
		return stateDistributions.get(stateName);
	}
}
