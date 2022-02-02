package main.java.matrices.util;

import java.util.ArrayList;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.Matrix;

/**
 * Interface which is used to access the static methods that control matrix discretisation.
 * Note that matrix discretisation can only be performed on dMatrix objects.
 * 
 * @author Joshua McDonagh
 *
 */
public interface Discretise 
{
	/**
	 * Discretises matrix content by the frequency of matrix values.
	 * @param doubleMatrix Matrix to be discretised
	 * @param numOfBins Number of bins to use for discretisation
	 * @return Discretised comparable matrix containing string values
	 */
	public static CMatrix<String> byFrequency(DMatrix doubleMatrix, int numOfBins)
	{
		ArrayList<ArrayList<Bin>> bins = new ArrayList<ArrayList<Bin>>();
		
		int frequency = (int) Math.ceil((double) doubleMatrix.height() / (double) numOfBins);
		
		// Splits the data up into bins
		for (int i = 0; i < doubleMatrix.width(); i++)
		{
			bins.add(new ArrayList<Bin>());
			DMatrix matrixColumn = Matrix.sortByColumn(0, doubleMatrix.getColumns(i, i + 1));
			for (int j = 0; j < numOfBins; j++)
			{
				String binValue = "" + j;
				int minIndex = frequency * j;
				int maxIndex = minIndex + frequency - 1;
				
				double min;
				double max;
				
				if (minIndex < matrixColumn.height())
					min = matrixColumn.get(minIndex, 0);
				else
					min = matrixColumn.get(matrixColumn.height() - 1, 0);
				
				if (maxIndex < matrixColumn.height())
					max = matrixColumn.get(maxIndex, 0);
				else
					max = matrixColumn.get(matrixColumn.height() - 1, 0);
				
				bins.get(i).add(new Bin(binValue, min, max));
			}
		}
		
		return buildFromBins(doubleMatrix, bins);
	}
	
	/**
	 * Discretises matrix content by the width of the bin ranges.
	 * @param doubleMatrix Matrix to be discretised
	 * @param numOfBins Number of bins to use for discretisation
	 * @return Discretised comparable matrix containing string values
	 */
	public static CMatrix<String> byWidth(DMatrix doubleMatrix, int numOfBins)
	{
		ArrayList<ArrayList<Bin>> bins = new ArrayList<ArrayList<Bin>>();
		
		// Splits the data up into bins
		for (int i = 0; i < doubleMatrix.width(); i++)
		{
			bins.add(new ArrayList<Bin>());
			double matrixMax = doubleMatrix.columnMax(i);
			double matrixMin = doubleMatrix.columnMin(i);
			double difference = matrixMax - matrixMin;
			double width = difference / (double)numOfBins;
			
			for (int j = 0; j < numOfBins; j++)
			{
				String binValue = "" + j;
				double min = matrixMin + width * j;
				double max;
				if (j + 1 != numOfBins || min + width == matrixMax)
					max = min + width;
				else
					max = matrixMax;
				bins.get(i).add(new Bin(binValue, min, max));
			}
		}
		
		// Constructs and returns the final discretised matrix
		return buildFromBins(doubleMatrix, bins);
	}
	
	/**
	 * Combines the content of bins to build the discretised matrix.
	 * @param doubleMatrix Matrix to be discretised
	 * @param bins Bins to be utilised to discretise the matrix
	 * @return Discretised comparable matrix containing string values
	 */
	private static CMatrix<String> buildFromBins(DMatrix doubleMatrix, ArrayList<ArrayList<Bin>> bins)
	{
		CMatrix<String> discreteMatrix = new CMatrix<String>(doubleMatrix.height(), doubleMatrix.width());
		
		// Uses the bins to construct the final discretised matrix
		for (int i = 0; i < discreteMatrix.height(); i++)
		{
			for (int j = 0; j < discreteMatrix.width(); j++)
			{
				double matrixVal = doubleMatrix.get(i, j);
				String discreteVal = "ERR";
				for (int k = 0; k < bins.get(j).size(); k++)
				{
					Bin bin = bins.get(j).get(k);
					if (bin.contains(matrixVal))
					{
						discreteVal = bin.value();
						break;
					}
				}
				discreteMatrix.set(i, j, discreteVal);
			}
		}
		
		return discreteMatrix;
	}
}

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
