package main.java.matrices.discretisation;

class Bin 
{
	private String binValue;
	private double max;
	private double min;
	
	Bin(String binValue, double min, double max)
	{
		this.binValue = binValue;
		this.max = max;
		this.min = min;
	}
	
	String value()
	{
		return binValue;
	}
	
	boolean contains(double value)
	{
		if (value <= max && value >= min)
			return true;
		
		return false;
	}
}
