package main.java;

import main.java.matrices.DMatrix;

public abstract class Generator 
{
	private DMatrix trainingSet;
	private DMatrix testingSet;
	private int classIndex;
	
	public Generator(DMatrix trainingSet, DMatrix testingSet)
	{
		setTrainingSet(trainingSet);
		setTestingSet(testingSet);
		setClassIndex(trainingSet.width() - 1);
	}
	
	public Generator(DMatrix trainingSet, DMatrix testingSet, int classIndex)
	{
		setTrainingSet(trainingSet);
		setTestingSet(testingSet);
		setClassIndex(classIndex);
	}
	
	public void setTrainingSet(DMatrix trainingSet)
	{
		this.trainingSet = trainingSet;
	}
	
	public void setTestingSet(DMatrix testingSet)
	{
		this.testingSet = testingSet;
	}
	
	public void setClassIndex(int classIndex)
	{
		this.classIndex = classIndex;
	}
	
	public DMatrix getTrainingSet()
	{
		return trainingSet;
	}
	
	public DMatrix getTestingSet()
	{
		return testingSet;
	}
	
	public int getClassIndex()
	{
		return classIndex;
	}
	
	public abstract Result train();
}
