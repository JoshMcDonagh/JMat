package main.java.matrices;

import java.util.ArrayList;

/**
 * This GMatrix represents a comparable matrix that contains comparable objects.
 * This allows for further functionality to be applied to matrices that require comparing matrix content.
 * 
 * @author Joshua McDonagh
 *
 * @param <T> Comparable generic type
 */
public class CMatrix<T extends Comparable<T>> extends GMatrix<T>
{
	/**
	 * Constructor method which creates an empty matrix.
	 */
	public CMatrix()
	{
		super();
	}
	
	/**
	 * Constructor method which creates a matrix with a set height and width.
	 * @param height Integer value to set the matrix height to
	 * @param width Integer value to set the matrix width to
	 */
	public CMatrix(int height, int width)
	{
		super(height, width);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D array.
	 * @param values A 2D array to construct the matrix from
	 */
	public CMatrix(T[][] values)
	{
		super(values);
	}
	
	/**
	 * Constructor method which creates an empty matrix with a set empty value.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 */
	CMatrix(T newEmptyVal)
	{
		super(newEmptyVal);
	}
	
	/** 
	 * Constructor method which creates a matrix with a set empty value, height, and width.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 * @param height Integer value to set the matrix height to
	 * @param width Integer value to set the matrix width to
	 */
	CMatrix(T newEmptyVal, int height, int width)
	{
		super(newEmptyVal, height, width);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D array with a set empty value.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 * @param values A 2D array to construct the matrix from
	 */
	CMatrix(T newEmptyVal, T[][] values)
	{
		super(newEmptyVal, values);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D ArrayList.
	 * @param newMatrixData A 2D ArrayList to construct the matrix from
	 */
	CMatrix(ArrayList<ArrayList<T>> newMatrixData)
	{
		super(newMatrixData);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D ArrayList with a set empty value.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 * @param newMatrixData A 2D ArrayList to construct the matrix from
	 */
	CMatrix(T newEmptyVal, ArrayList<ArrayList<T>> newMatrixData)
	{
		super(newEmptyVal, newMatrixData);
	}
	
	/**
	 * Method returns a set of rows from the matrix as a new comparable matrix.
	 * @param start The row index of the first row of the matrix to be extracted (inclusive)
	 * @param end The row index of the last row of the matrix to be extracted (exclusive)
	 * @return A comparable matrix consisting of the selected rows
	 */
	public CMatrix<T> getRows(int start, int end)
	{
		return ConvertMatrix.toComparable(super.getRows(start, end));
	}
	
	/**
	 * Method returns a row from the matrix as a new comparable matrix.
	 * @param row The row index of the row of the matrix to be extracted
	 * @return A comparable matrix consisting of the selected row
	 */
	public CMatrix<T> getRows(int row)
	{
		return getRows(row, row + 1);
	}
	
	/**
	 * Method returns a set of columns from the matrix as a new comparable matrix.
	 * @param start The column index of the first column of the matrix to be extracted (inclusive)
	 * @param end The column index of the last column of the matrix to be extracted (exclusive)
	 * @return A comparable matrix consisting of the selected columns
	 */
	public CMatrix<T> getColumns(int start, int end)
	{
		return ConvertMatrix.toComparable(super.getColumns(start, end));
	}
	
	/**
	 * Method returns a column from the matrix as a new comparable matrix.
	 * @param column The column index of the column of the matrix to be extracted
	 * @return A comparable matrix consisting of the selected column
	 */
	public CMatrix<T> getColumns(int column)
	{
		return getColumns(column, column + 1);
	}
	
	/**
	 * Method returns a transpose of the matrix.
	 * @return A transposed version of the comparable matrix
	 */
	public CMatrix<T> transpose()
	{
		return ConvertMatrix.toComparable(super.transpose());
	}
	
	/**
	 * Flattens the matrix into a matrix consisting of a single row.
	 * @return A flattened version of the comparable matrix
	 */
	public CMatrix<T> flattenToRow()
	{
		return ConvertMatrix.toComparable(super.flattenToRow());
	}
	
	/**
	 * Flattens the matrix into a matrix consisting of a single column.
	 * @return A flattened version of the comparable matrix
	 */
	public CMatrix<T> flattenToColumn()
	{
		return ConvertMatrix.toComparable(super.flattenToColumn());
	}
	
	/**
	 * Generates and returns a duplicated version of the matrix.
	 * @return A comparable matrix consisting of the same values
	 */
	public CMatrix<T> clone()
	{
		return ConvertMatrix.toComparable(super.clone());
	}
	
	/**
	 * Evaluates whether a given comparable matrix has the same content as this matrix.
	 * @param matrix Comparable matrix to compare with
	 * @return Boolean value which is {@code true} if the matrices are equal, otherwise {@code false}
	 */
	public boolean equalTo(CMatrix<T> matrix)
	{
		if (matrix.height() != height() || matrix.width() != width())
			return false;
		
		for (int i = 0 ; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
			{
				if (get(i, j).compareTo(matrix.get(i, j)) != 0)
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Evaluates whether a given comparable matrix is contained as a sub matrix within this matrix.
	 * @param matrix Comparable matrix to find as a sub matrix
	 * @return Boolean value which is {@code true} if the given matrix exists as a sub matrix, otherwise {@code false}
	 */
	public boolean contains(CMatrix<T> matrix)
	{
		if (matrix.height() > height() || matrix.width() > width())
			return false;
		
		for (int i = 0; i < height() - matrix.height() + 1; i++)
		{
			for (int j = 0; j < width() - matrix.width() + 1; j++)
			{
				if (matrix.equalTo(getRows(i, i + matrix.height()).getColumns(j, j + matrix.width())))
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the minimum value of a column.
	 * @param index Column index to check
	 * @return The minimum value of the column
	 */
	public T columnMin(int index)
	{
		return Matrix.sort(index, this).get(0, index);
	}
	
	/**
	 * Returns the maximum value of a column.
	 * @param index Column index to check
	 * @return The maximum value of the column
	 */
	public T columnMax(int index)
	{
		return Matrix.sort(index, this, false).get(0, index);
	}
	
	/**
	 * Returns the minimum value of a row.
	 * @param index Row index to check
	 * @return The minimum value of the row
	 */
	public T rowMin(int index)
	{
		return Matrix.sort(index, transpose()).get(0, index);
	}
	
	/**
	 * Returns the maximum value of a row.
	 * @param index Row index to check
	 * @return The maximum value of the row
	 */
	public T rowMax(int index)
	{
		return Matrix.sort(index, transpose(), false).get(0, index);
	}
}
