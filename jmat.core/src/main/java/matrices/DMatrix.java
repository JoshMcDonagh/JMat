package main.java.matrices;

import java.util.Random;

/**
 * This CMatrix establishes itself as a comparable matrix using the double generic type, 
 * and represents a double matrix.
 * This class is useful for mathematical processes using matrices.
 * 
 * @author Joshua McDonagh
 *
 */
public class DMatrix extends CMatrix<Double>
{
	private final static double emptyVal = 0;
	private static Random random = new Random();
	
	/**
	 * Constructor method which creates an empty matrix.
	 */
	public DMatrix()
	{
		super(emptyVal);
	}
	
	/**
	 * Constructor method which creates a matrix with a set height and width.
	 * @param height Integer value to set the matrix height to
	 * @param width Integer value to set the matrix width to
	 */
	public DMatrix(int height, int width)
	{
		super(emptyVal, height, width);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D array.
	 * @param values A 2D array to construct the matrix from
	 */
	public DMatrix(Double[][] values)
	{
		super(emptyVal, values);
	}
	
	/**
	 * Sets a specific value within the matrix.
	 * @param row Row index of the value to be set
	 * @param column Column index of the value to be set
	 * @param value Double value to set and store in the matrix
	 */
	public void set(int row, int column, double value)
	{
		super.set(row, column, value + 0.0);
	}
	
	/**
	 * Method returns a set of rows from the matrix as a new double matrix.
	 * @param start The row index of the first row of the matrix to be extracted (inclusive)
	 * @param end The row index of the last row of the matrix to be extracted (exclusive)
	 * @return A double matrix consisting of the selected rows
	 */
	public DMatrix getRows(int start, int end)
	{
		return ConvertMatrix.toDouble(super.getRows(start, end));
	}
	
	/**
	 * Method returns a row from the matrix as a new double matrix.
	 * @param row The row index of the row of the matrix to be extracted
	 * @return A double matrix consisting of the selected row
	 */
	public DMatrix getRows(int row)
	{
		return getRows(row, row + 1);
	}
	
	/**
	 * Method returns a set of columns from the matrix as a new double matrix.
	 * @param start The column index of the first column of the matrix to be extracted (inclusive)
	 * @param end The column index of the last column of the matrix to be extracted (exclusive)
	 * @return A double matrix consisting of the selected columns
	 */
	public DMatrix getColumns(int start, int end)
	{
		return ConvertMatrix.toDouble(super.getColumns(start, end));
	}
	
	/**
	 * Method returns a column from the matrix as a new double matrix.
	 * @param column The column index of the column of the matrix to be extracted
	 * @return A double matrix consisting of the selected column
	 */
	public DMatrix getColumns(int column)
	{
		return getColumns(column, column + 1);
	}
	
	/**
	 * Converts an array of integers into an array of doubles.
	 * @param integers Array of integers to be converted
	 * @return Array of doubles produced by converting the given array of integers
	 */
	private Double[] intToDouble(int[] integers)
	{
		Double[] doubles = new Double[integers.length];
		
		for (int i = 0; i < integers.length; i++)
			doubles[i] = (double) integers[i];
		
		return doubles;
	}
	
	/**
	 * Adds a set of integer values (which are converted into double values) as a new row in the matrix.
	 * @param values Values of the new row to be appended to the matrix
	 */
	public void addRow(int... values)
	{
		addRow(intToDouble(values));
	}
	
	/**
	 * Adds a set of integer values (which are converted into double values) as a new column in the matrix.
	 * @param values Values of the new column to be appended to the matrix
	 */
	public void addColumn(int... values)
	{
		addColumn(intToDouble(values));
	}
	
	/**
	 * Populates the matrix with random double values.
	 * @param min Minimum random value
	 * @param max Maximum random value
	 */
	public void doubleRandomise(double min, double max)
	{
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
				set(i, j, min + (max - min) * random.nextDouble());
		}
	}
	
	/**
	 * Populates the matrix with random integer values.
	 * @param min Minimum random value
	 * @param max Maximum random value
	 */
	public void intRandomise(int min, int max)
	{
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
				set(i, j, (double) random.nextInt(max - min) + min);
		}
	}
	
	/**
	 * Method returns a transpose of the matrix.
	 * @return A transposed version of the double matrix
	 */
	public DMatrix transpose()
	{
		return ConvertMatrix.toDouble(super.transpose());
	}
	
	/**
	 * Evaluates whether a given double matrix has the same content as this matrix.
	 * @param dMatrix Double matrix to compare with
	 * @return Boolean value which is {@code true} if the matrices are equal, otherwise {@code false}
	 */
	public boolean equalTo(DMatrix dMatrix)
	{
		return super.equalTo(ConvertMatrix.toComparable(dMatrix));
	}
	
	/**
	 * Evaluates whether a given double matrix is contained as a sub matrix within this matrix.
	 * @param dMatrix Double matrix to find as a sub matrix
	 * @return Boolean value which is {@code true} if the given matrix exists as a sub matrix, otherwise {@code false}
	 */
	public boolean contains(DMatrix dMatrix)
	{
		return super.contains(ConvertMatrix.toComparable(dMatrix));
	}
	
	/**
	 * Generates and returns a duplicated version of the matrix.
	 * @return A double matrix consisting of the same values
	 */
	public DMatrix clone()
	{
		return ConvertMatrix.toDouble(super.clone());
	}
}
