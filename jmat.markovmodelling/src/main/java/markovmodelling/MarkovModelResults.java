package main.java.markovmodelling;

public class MarkovModelResults {
	private String[] statesForEachStep;
	
	MarkovModelResults(String[] statesForEachStep) {
		this.statesForEachStep = statesForEachStep;
	};
	
	public String[] getStatesForEachStep() {
		return statesForEachStep;
	}
	
	
}
