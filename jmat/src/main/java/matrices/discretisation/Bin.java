package main.java.matrices.discretisation;

/**
 * This class is used for discretisation to hold a certain number of values between a set minimum and maximum and represent them as another value.
 * 
 * @author Joshua McDonagh
 *
 */
class Bin 
{
	private String binValue;
	private double max;
	private double min;
	
	/**
	 * Constructor builds the object with the representative bin value and the range of values the bin can contain.
	 * @param binValue The string value which represents the bin
	 * @param min The minimum value that the bin can contain
	 * @param max The maximum value that the bin can contain
	 */
	Bin(String binValue, double min, double max)
	{
		this.binValue = binValue;
		this.max = max;
		this.min = min;
	}
	
	/**
	 * Gets the representative bin value
	 * @return String bin value
	 */
	String value()
	{
		return binValue;
	}
	
	/**
	 * Checks whether a given value is contained in the bin.
	 * @param value The value to check if is contained in the bin
	 * @return Boolean value which is {@code true} if the given value is contained in the bin, otherwise {@code false}
	 */
	boolean contains(double value)
	{
		if (value <= max && value >= min)
			return true;
		
		return false;
	}
}
