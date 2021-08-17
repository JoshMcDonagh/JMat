package main.java.matrices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This iterator represents a generic matrix that can contain any object.
 * This allows for general and basic matrix functionality for any given object type.
 * 
 * @author Joshua McDonagh
 *
 * @param <T> Generic type
 */
public class GMatrix<T> implements Matrix, Iterable<T>
{
	private ArrayList<ArrayList<T>> matrixData;
	private T emptyVal;
	
	// Member variables utilised if the matrix has been initialised with a width > 0 but and a height of 0
	private int width;
	private boolean widthIsNotZero = false;
	
	/**
	 * Constructor method which creates an empty matrix.
	 */
	public GMatrix()
	{
		emptyVal = null;
		clear();
	}
	
	/**
	 * Constructor method which creates a matrix with a set height and width.
	 * @param height Integer value to set the matrix height to
	 * @param width Integer value to set the matrix width to
	 */
	public GMatrix(int height, int width)
	{
		emptyVal = null;
		clear(height, width);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D array.
	 * @param values A 2D array to construct the matrix from
	 */
	public GMatrix(T[][] values)
	{
		emptyVal = null;
		clear();
		convertArrayToMatrix(values);
	}
	
	/**
	 * Constructor method which creates an empty matrix with a set empty value.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 */
	GMatrix(T newEmptyVal)
	{
		emptyVal = newEmptyVal;
		clear();
	}
	
	/** 
	 * Constructor method which creates a matrix with a set empty value, height, and width.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 * @param height Integer value to set the matrix height to
	 * @param width Integer value to set the matrix width to
	 */
	GMatrix(T newEmptyVal, int height, int width)
	{
		emptyVal = newEmptyVal;
		clear(height, width);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D array with a set empty value.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 * @param values A 2D array to construct the matrix from
	 */
	GMatrix(T newEmptyVal, T[][] values)
	{
		emptyVal = newEmptyVal;
		clear();
		convertArrayToMatrix(values);
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D ArrayList.
	 * @param newMatrixData A 2D ArrayList to construct the matrix from
	 */
	GMatrix(ArrayList<ArrayList<T>> newMatrixData)
	{
		emptyVal = null;
		matrixData = newMatrixData;
	}
	
	/**
	 * Constructor method which creates a matrix from a 2D ArrayList with a set empty value.
	 * @param newEmptyVal The empty value to set to the constructed matrix
	 * @param newMatrixData A 2D ArrayList to construct the matrix from
	 */
	GMatrix(T newEmptyVal, ArrayList<ArrayList<T>> newMatrixData)
	{
		emptyVal = newEmptyVal;
		matrixData = newMatrixData;
	}
	
	/**
	 * Converts the contents of a 2D array into a data structure readable by the matrix.
	 * @param values A 2D array to convert
	 */
	private void convertArrayToMatrix(T[][] values)
	{
		for (int i = 0; i < values.length; i++)
			matrixData.add(new ArrayList<>(Arrays.asList(values[i])));
		normalise();
	}
	
	/**
	 * Clears the contents of the matrix.
	 */
	public void clear()
	{
		matrixData = new ArrayList<ArrayList<T>>();
	}
	
	/**
	 * Clears the contents of the matrix and replaces it with an empty matrix of a set height and width.
	 * @param height Integer value to set the matrix height to
	 * @param width Integer value to set the matrix width to
	 */
	public void clear(int height, int width)
	{
		clear();
		
		if (height == 0 && width > 0)
		{
			this.width = width;
			widthIsNotZero = true;
			return;
		}
		
		for (int i = 0; i < height; i++)
		{
			matrixData.add(new ArrayList<T>());
			for (int j = 0; j < width; j++)
				matrixData.get(i).add(emptyVal);
		}
	}
	
	/**
	 * Gets a specific value from the matrix.
	 * @param row Row index of the value
	 * @param column Column index of the value
	 * @return The specific value at the row and column indexes of the matrix
	 */
	public T get(int row, int column)
	{
		return matrixData.get(row).get(column);
	}
	
	/**
	 * Sets a specific value within the matrix.
	 * @param row Row index of the value to be set
	 * @param column Column index of the value to be set
	 * @param value The value to set and store in the matrix
	 */
	public void set(int row, int column, T value)
	{
		matrixData.get(row).set(column, value);
	}
	
	/**
	 * Appends a value to the end of a row of the matrix.
	 * @param row Row index to append the value to
	 * @param value The value to append to the row
	 */
	public void appendToRow(int row, T value)
	{
		matrixData.get(row).add(value);
		normalise();
	}
	
	/**
	 * Appends a value to the end of a column of the matrix.
	 * @param column Column index to append the value to
	 * @param value The value to append to the column
	 */
	public void appendToColumn(int column, T value)
	{
		matrixData.add(new ArrayList<T>());
		normalise();
		matrixData.get(matrixData.size() - 1).set(column, value);
	}
	
	/**
	 * Method returns a set of rows from the matrix as a new generic matrix.
	 * @param start The row index of the first row of the matrix to be extracted (inclusive)
	 * @param end The row index of the last row of the matrix to be extracted (exclusive)
	 * @return A generic matrix consisting of the selected rows
	 */
	public GMatrix<T> getRows(int start, int end)
	{
		GMatrix<T> newMatrix = new GMatrix<T>();
		
		int count = end - start;
		for (int i = 0; i < count; i++)
			newMatrix.addRow(matrixData.get(start + i));
		
		return newMatrix;
	}
	
	/**
	 * Method returns a row from the matrix as a new generic matrix.
	 * @param row The row index of the row of the matrix to be extracted
	 * @return A generic matrix consisting of the selected row
	 */
	public GMatrix<T> getRows(int row)
	{
		return getRows(row, row + 1);
	}
	
	/**
	 * Method returns a set of columns from the matrix as a new generic matrix.
	 * @param start The column index of the first column of the matrix to be extracted (inclusive)
	 * @param end The column index of the last column of the matrix to be extracted (exclusive)
	 * @return A generic matrix consisting of the selected columns
	 */
	public GMatrix<T> getColumns(int start, int end)
	{
		return transpose().getRows(start, end).transpose();
	}
	
	/**
	 * Method returns a column from the matrix as a new generic matrix.
	 * @param column The column index of the column of the matrix to be extracted
	 * @return A generic matrix consisting of the selected column
	 */
	public GMatrix<T> getColumns(int column)
	{
		return getColumns(column, column + 1);
	}
	
	/**
	 * Appends an ArrayList of values as a new row in the matrix.
	 * @param values ArrayList of values to append as a row
	 */
	private void addRow(ArrayList<T> values)
	{
		matrixData.add(values);
		normalise();
	}
	
	/**
	 * Appends a set of values as a new row in the matrix.
	 * @param values Set of values to append as a row
	 */
	public void addRow(@SuppressWarnings("unchecked") T... values)
	{
		matrixData.add(new ArrayList<>(Arrays.asList(values)));
		normalise();
	}
	
	/**
	 * Appends a set of values as a new column in the matrix.
	 * @param values Set of values to append as a column
	 */
	public void addColumn(@SuppressWarnings("unchecked") T... values)
	{
		GMatrix<T> transposed = transpose();
		transposed.matrixData.add(new ArrayList<>(Arrays.asList(values)));
		matrixData = transposed.transpose().matrixData;
		normalise();
	}
	
	/**
	 * Deletes and removes a defined range of rows from the matrix.
	 * @param start The row index of the first row of the matrix to be deleted (inclusive)
	 * @param end The row index of the last row of the matrix to be deleted (exclusive)
	 */
	public void deleteRows(int start, int end)
	{
		int count = end - start;
		for (int i = 0; i < count; i++)
			matrixData.remove(start);
	}
	
	/**
	 * Deletes and removes a defined range of columns from the matrix.
	 * @param start The column index of the first column of the matrix to be deleted (inclusive)
	 * @param end The column index of the last column of the matrix to be deleted (exclusive)
	 */
	public void deleteColumns(int start, int end)
	{
		GMatrix<T> transposed = transpose();
		transposed.deleteRows(start, end);
		matrixData = transposed.transpose().matrixData;
	}
	
	/**
	 * Inserts a generic matrix as a set of rows in the matrix.
	 * @param row The row index to start inserting from
	 * @param newRows The generic matrix of rows to insert
	 */
	public void insertRows(int row, GMatrix<T> newRows)
	{
		ArrayList<ArrayList<T>> newMatrixData = new ArrayList<ArrayList<T>>();
		ArrayList<ArrayList<T>> newRowData = newRows.matrixData;
		
		for (int i = 0; i < row; i++)
			newMatrixData.add(matrixData.get(i));
		
		for (int i = 0; i < newRowData.size(); i++)
			newMatrixData.add(newRowData.get(i));
		
		for (int i = row; i < matrixData.size(); i++)
			newMatrixData.add(matrixData.get(i));
		
		matrixData = newMatrixData;
		normalise();
	}
	
	/**
	 * Inserts a generic matrix as a set of columns in the matrix.
	 * @param column The column index to start inserting from
	 * @param newColumns The generic matrix of columns to insert
	 */
	public void insertColumns(int column, GMatrix<T> newColumns)
	{
		GMatrix<T> oldColumnData = transpose();
		oldColumnData.insertRows(column, newColumns.transpose());
		matrixData = oldColumnData.transpose().matrixData;
	}
	
	/**
	 * Replaces a set of rows in the matrix with that of another generic matrix.
	 * @param row The row index to start replacing from
	 * @param newRows The generic matrix of rows to replace with
	 */
	public void replaceRows(int row, GMatrix<T> newRows)
	{
		for (int i = 0; i < newRows.height(); i++)
			matrixData.set(row + i, newRows.matrixData.get(i));
	}
	
	/**
	 * Replaces a set of columns in the matrix with that of another generic matrix.
	 * @param column The column index to start replacing from
	 * @param newColumns The generic matrix of columns to replace with
	 */
	public void replaceColumns(int column, GMatrix<T> newColumns)
	{
		GMatrix<T> oldColumnData = transpose();
		oldColumnData.replaceRows(column, newColumns.transpose());
		matrixData = oldColumnData.transpose().matrixData;
	}
	
	/**
	 * Sets the empty value which populates any short rows and columns.
	 * @param newEmptyVal The value to set the empty value as
	 */
	public void setEmptyValue(T newEmptyVal)
	{
		emptyVal = newEmptyVal;
	}
	
	/**
	 * Gets the width of the matrix (i.e. the number of columns).
	 * @return The width of the matrix as an integer
	 */
	public int width()
	{
		if (widthIsNotZero)
			return width;
		
		if (matrixData.size() == 0)
			return 0;
		
		return matrixData.get(0).size();
	}
	
	/**
	 * Gets the height of the matrix (i.e. the number of rows).
	 * @return The height of the matrix as an integer
	 */
	public int height()
	{
		return matrixData.size();
	}
	
	/**
	 * Returns whether the matrix is empty or not.
	 * @return Boolean value which is {@code true} if the matrix is empty, otherwise {@code false}
	 */
	public boolean isEmpty()
	{
		return height() == 0 && width() == 0;
	}
	
	/**
	 * Returns whether the matrix is square (the height and width are equal) or not.
	 * @return Boolean value which is {@code true} if the matrix is square, otherwise {@code false}
	 */
	public boolean isSquare()
	{
		return height() == width();
	}
	
	/**
	 * Method returns a transpose of the matrix.
	 * @return A transposed version of the generic matrix
	 */
	public GMatrix<T> transpose()
	{
		ArrayList<ArrayList<T>> newMatrixData = new ArrayList<ArrayList<T>>();
		
		for (int i = 0; i < width(); i++)
		{
			newMatrixData.add(new ArrayList<T>());
			for (int j = 0; j < height(); j++)
				newMatrixData.get(i).add(matrixData.get(j).get(i));
		}
		
		return new GMatrix<T>(newMatrixData);
	}
	
	/**
	 * Flattens the matrix into a matrix consisting of a single row.
	 * @return A flattened version of the generic matrix
	 */
	public GMatrix<T> flattenToRow()
	{
		ArrayList<T> flattenedList = new ArrayList<T>();
		
		for (T value : this)
			flattenedList.add(value);
		
		GMatrix<T> flattenedMatrix = new GMatrix<T>(1, flattenedList.size());
		
		for (int i = 0; i < flattenedMatrix.width(); i++)
			flattenedMatrix.set(0, i, flattenedList.get(i));
		
		return flattenedMatrix;
	}
	
	/**
	 * Flattens the matrix into a matrix consisting of a single column.
	 * @return A flattened version of the generic matrix
	 */
	public GMatrix<T> flattenToColumn()
	{
		return flattenToRow().transpose();
	}
	
	/**
	 * Converts the matrix into a 2D array.
	 * @return A 2D array containing the matrix's values
	 */
	public Object[][] toArray()
	{
		Object[][] array = new Object[matrixData.size()][];
		
		for (int i = 0; i < matrixData.size(); i++)
			array[i] = (matrixData.get(i).toArray());
		
		return array;
	}
	
	/**
	 * Maps a function across all values of the matrix.
	 * @param mapFunction A functional interface which contains the functional method to apply to the matrix values
	 */
	@SuppressWarnings("unchecked")
	public void map(MapFunction mapFunction)
	{
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
			{
				T matVal = matrixData.get(i).get(j);
				matrixData.get(i).set(j, (T) mapFunction.eval(matVal));
			}
		}
	}
	
	/**
	 * Generates and returns a duplicated version of the matrix.
	 * @return A generic matrix consisting of the same values
	 */
	public GMatrix<T> clone()
	{
		GMatrix<T> newMatrix = new GMatrix<T>(height(),width());
		
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
				newMatrix.set(i, j, matrixData.get(i).get(j));
		}
		
		return newMatrix;
	}
	
	/**
	 * Displays the matrix on the console in a formatted way.
	 */
	public void show()
	{
		ArrayList<ArrayList<String>> stringMatrixData = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> colLengths = new ArrayList<Integer>();
		final String delimiter = "   ";
		
		// Populates the rows to be printed with data from the matrix
		for (int i = 0; i < height(); i++)
		{
			stringMatrixData.add(new ArrayList<String>());
			
			for (int j = 0; j < width(); j++)
			{
				if (matrixData.get(i).get(j) == null)
					stringMatrixData.get(i).add("NULL");
				else				
					stringMatrixData.get(i).add(matrixData.get(i).get(j).toString());
			}
		}
		
		// Formats each row so that each column lines up correctly
		for (int i = 0; i < width(); i++)
		{
			int maxLength = 0;
			
			for (int j = 0; j < height(); j++)
			{
				int length = stringMatrixData.get(j).get(i).length();
				if (length > maxLength)
					maxLength = length;
			}
			
			colLengths.add(maxLength);
		}
		
		// Displays each row of the matrix on the console
		for (int i = 0; i < height(); i++)
		{
			String printLn = "";
			for (int j = 0; j < width(); j++)
			{
				String strVal = stringMatrixData.get(i).get(j);
				printLn += strVal + new String(new char[colLengths.get(j) - strVal.length()]).replace("\0", " ") + delimiter;
			}
			System.out.println(printLn);
		}
	}
	
	/**
	 * Normalises the row and column sizes.
	 */
	private void normalise()
	{
		int finalWidth = 0;
		
		if (widthIsNotZero)
		{
			finalWidth = width;
			widthIsNotZero = false;
		}
		
		for (int i = 0; i < matrixData.size(); i++)
		{
			int currentWidth = matrixData.get(i).size();
			if (currentWidth > finalWidth)
				finalWidth = currentWidth;
		}
		
		for (int i = 0; i < matrixData.size(); i++)
		{
			ArrayList<T> currentRow = matrixData.get(i);
			while (finalWidth > currentRow.size())
				currentRow.add(emptyVal);
		}
	}
	
	/**
	 * Generates a new matrix iterator.
	 * @return An iterator for the matrix
	 */
	public Iterator<T> iterator() 
	{
		return new MatrixIterator<T>(this);
	}
}

/**
 * This iterator is implement for the GMatrix class such that each value of the matrix can be iterated over.
 * 
 * @author Joshua McDonagh
 *
 * @param <T> Generic type used by the corresponding matrix
 */
class MatrixIterator<T> implements Iterator<T>
{
	private GMatrix<T> matrix;
	int currentRow = 0;
	int currentCol = 0;
	
	/**
	 * Constructor which sets the matrix with the contents to be iterated over.
	 * @param matrix
	 */
	MatrixIterator(GMatrix<T> matrix)
	{
		this.matrix = matrix;
	}
	
	/**
	 * Checks whether there is another value in the matrix to be iterated.
	 * @return Boolean value which is {@code true} if there is another matrix value, otherwise {@code false}
	 */
	public boolean hasNext() 
	{
		if (currentCol == matrix.width())
		{
			currentRow++;
			currentCol = 0;
		}
		
		if (currentRow == matrix.height())
			return false;
		
		return true;
	}
	
	/**
	 * Gets the next value in the matrix to be iterated.
	 * @return The next matrix value to be iterated over
	 */
	public T next() 
	{
		T value = matrix.get(currentRow, currentCol);
		currentCol++;
		
		return value;
	}
}
