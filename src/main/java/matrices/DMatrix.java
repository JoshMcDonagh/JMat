package main.java.matrices;

import java.util.Random;

import main.java.matrices.utilities.MatrixUtilities;

public class DMatrix extends CMatrix<Double>
{
	private final static double emptyVal = 0;
	private static Random random = new Random();
	
	public DMatrix()
	{
		super(emptyVal);
	}
	
	public DMatrix(int height, int width)
	{
		super(emptyVal, height, width);
	}
	
	public DMatrix(Double[][] values)
	{
		super(emptyVal, values);
	}
	
	public void set(int row, int column, double value)
	{
		super.set(row, column, value + 0.0);
	}
	
	public DMatrix getRows(int start, int end)
	{
		return MatrixUtilities.comparableToDouble(super.getRows(start, end));
	}
	
	public DMatrix getColumns(int start, int end)
	{
		return  MatrixUtilities.comparableToDouble(super.getColumns(start, end));
	}
	
	private Double[] intToDouble(int[] integers)
	{
		Double[] doubles = new Double[integers.length];
		
		for (int i = 0; i < integers.length; i++)
			doubles[i] = (double) integers[i];
		
		return doubles;
	}
	
	public void addRow(int... values)
	{
		addRow(intToDouble(values));
	}
	
	public void addColumn(int... values)
	{
		addColumn(intToDouble(values));
	}
	
	public void doubleRandomise(double min, double max)
	{
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
				set(i, j, min + (max - min) * random.nextDouble());
		}
	}
	
	public void intRandomise(int min, int max)
	{
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
				set(i, j, (double) random.nextInt(max - min) + min);
		}
	}
	
	public DMatrix transpose()
	{
		return MatrixUtilities.comparableToDouble(super.transpose());
	}
	
	public boolean equalTo(DMatrix dMatrix)
	{
		return super.equalTo(MatrixUtilities.doubleToComparable(dMatrix));
	}
	
	public boolean contains(DMatrix dMatrix)
	{
		return super.contains(MatrixUtilities.doubleToComparable(dMatrix));
	}
	
	public DMatrix clone()
	{
		return MatrixUtilities.comparableToDouble(super.clone());
	}
}
