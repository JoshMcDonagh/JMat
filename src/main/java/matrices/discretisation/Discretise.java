package main.java.matrices.discretisation;

import java.util.ArrayList;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.utilities.MatrixUtilities;

public class Discretise 
{
	public static CMatrix<String> byFrequency(DMatrix doubleMatrix, int numOfBins)
	{
		ArrayList<ArrayList<Bin>> bins = new ArrayList<ArrayList<Bin>>();
		
		int frequency = (int) Math.ceil((double) doubleMatrix.height() / (double) numOfBins);
		
		for (int i = 0; i < doubleMatrix.width(); i++)
		{
			bins.add(new ArrayList<Bin>());
			DMatrix matrixColumn = MatrixUtilities.sort(0, doubleMatrix.getColumns(i, i + 1));
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
	
	public static CMatrix<String> byWidth(DMatrix doubleMatrix, int numOfBins)
	{
		ArrayList<ArrayList<Bin>> bins = new ArrayList<ArrayList<Bin>>();
		
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
		
		return buildFromBins(doubleMatrix, bins);
	}
	
	private static CMatrix<String> buildFromBins(DMatrix doubleMatrix, ArrayList<ArrayList<Bin>> bins)
	{
		CMatrix<String> discreteMatrix = new CMatrix<String>(doubleMatrix.height(), doubleMatrix.width());
		
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
