package main.java.matrices;

import java.util.Iterator;

/**
 * Interface is the base for all matrix classes and provides access to useful static method functionality.
 * 
 * @author Joshua McDonagh
 *
 */
public interface Matrix
{
	// !!! Static Members !!!
	
	/**
	 * Constructs an identity matrix of a given size.
	 * @param size Integer representing the size of the identity matrix
	 * @return A double matrix representing the identity matrix
	 */
	public static DMatrix makeIdentityMatrix(int size)
	{
		DMatrix identityMatrix = new DMatrix(size, size);
		for (int i = 0; i < size; i++)
			identityMatrix.set(i, i, 1.0);
		return identityMatrix;
	}
	
	/**
	 * Sorts the contents of a comparable matrix by a given column index in either an ascending or descending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> sortByColumn(int index, CMatrix<T> matrix, boolean ascending)
	{
		return MatSorter.quickSort(index, matrix, ascending);
	}
	
	/**
	 * Sorts the contents of a comparable matrix by a given column index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> sortByColumn(int index, CMatrix<T> matrix)
	{
		return sortByColumn(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a comparable matrix by a given row index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Row index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> sortByRow(int index, CMatrix<T> matrix, boolean ascending)
	{
		return sortByColumn(index, matrix.transpose(), ascending).transpose();
	}
	
	/**
	 * Sorts the contents of a comparable matrix by a given row index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Row index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> sortByRow(int index, CMatrix<T> matrix)
	{
		return sortByRow(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a double matrix by a given column index in either an ascending or descending way.
	 * @param index Column index to base the sort process on
	 * @param matrix Double matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted double matrix
	 */
	public static DMatrix sortByColumn(int index, DMatrix matrix, boolean ascending)
	{
		return MatSorter.recursiveBucketSort(index, matrix, ascending);
	}
	
	/**
	 * Sorts the contents of a double matrix by a given column index in an ascending way.
	 * @param index Column index to base the sort process on
	 * @param matrix Double matrix to sort
	 * @return The sorted double matrix
	 */
	public static DMatrix sortByColumn(int index, DMatrix matrix)
	{
		return sortByColumn(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a double matrix by a given row index in either an ascending or descending way.
	 * @param index Row index to base the sort process on
	 * @param matrix Double matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted double matrix
	 */
	public static DMatrix sortByRow(int index, DMatrix matrix, boolean ascending)
	{
		return sortByColumn(index, matrix.transpose(), ascending).transpose();
	}
	
	/**
	 * Sorts the contents of a double matrix by a given row index in an ascending way.
	 * @param index Row index to base the sort process on
	 * @param matrix Double matrix to sort
	 * @return The sorted double matrix
	 */
	public static DMatrix sortByRow(int index, DMatrix matrix)
	{
		return sortByRow(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using merge sort by a given column index in either an ascending or descending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> mergeSortByColumn(int index, CMatrix<T> matrix, boolean ascending)
	{
		return MatSorter.mergeSort(index, matrix, ascending);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using merge sort by a given column index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> mergeSortByColumn(int index, CMatrix<T> matrix)
	{
		return mergeSortByColumn(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using merge sort by a given row index in either an ascending or descending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Row index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> mergeSortByRow(int index, CMatrix<T> matrix, boolean ascending)
	{
		return mergeSortByColumn(index, matrix.transpose(), ascending).transpose();
	}
	
	/**
	 * Sorts the contents of a comparable matrix using merge sort by a given row index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Row index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> mergeSortByRow(int index, CMatrix<T> matrix)
	{
		return mergeSortByRow(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using insertion sort by a given column index in either an ascending or descending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> insertionSortByColumn(int index, CMatrix<T> matrix, boolean ascending)
	{
		return MatSorter.insertionSort(index, matrix, ascending);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using insertion sort by a given column index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> insertionSortByColumn(int index, CMatrix<T> matrix)
	{
		return insertionSortByColumn(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using insertion sort by a given row index in either an ascending or descending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Row index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> insertionSortByRow(int index, CMatrix<T> matrix, boolean ascending)
	{
		return insertionSortByColumn(index, matrix.transpose(), ascending).transpose();
	}
	
	/**
	 * Sorts the contents of a comparable matrix using insertion sort by a given row index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Row index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> insertionSortByRow(int index, CMatrix<T> matrix)
	{
		return insertionSortByRow(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using quick sort by a given column index in either an ascending or descending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> quickSortByColumn(int index, CMatrix<T> matrix, boolean ascending)
	{
		return MatSorter.quickSort(index, matrix, ascending);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using quick sort by a given column index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> quickSortByColumn(int index, CMatrix<T> matrix)
	{
		return quickSortByColumn(index, matrix, true);
	}
	
	/**
	 * Sorts the contents of a comparable matrix using quick sort by a given column index in either an ascending or descending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> quickSortByRow(int index, CMatrix<T> matrix, boolean ascending)
	{
		return quickSortByColumn(index, matrix.transpose(), ascending).transpose();
	}
	
	/**
	 * Sorts the contents of a comparable matrix using quick sort by a given row index in an ascending way.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Row index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> quickSortByRow(int index, CMatrix<T> matrix)
	{
		return quickSortByRow(index, matrix, true);
	}
	
	/**
	 * Appends the rows of a generic matrix onto the end of another generic matrix.
	 * @param <T> Type of data that the given matrices contain
	 * @param matrix1 Generic matrix to be appended to
	 * @param matrix2 Generic matrix to append with
	 * @return Generic matrix containing the rows of both given generic matrices
	 */
	@SuppressWarnings("unchecked")
	public static <T> GMatrix<T> mergeVertically(GMatrix<T> matrix1, GMatrix<T> matrix2)
	{
		if (matrix1.isEmpty())
			return matrix2;
		
		if (matrix2.isEmpty())
			return matrix1;
		
		GMatrix<T> mergedMatrix = matrix1.clone();
		
		for (int i = 0; i < matrix2.height(); i++)
		{
			mergedMatrix.addRow((T) null);
			for (int j = 0; j < matrix2.width(); j++)
				mergedMatrix.set(mergedMatrix.height() - 1, j, matrix2.get(i, j));
		}
		
		return mergedMatrix;
	}
	
	/**
	 * Appends the columns of a generic matrix onto the end of another generic matrix.
	 * @param <T> Type of data that the given matrices contain
	 * @param matrix1 Generic matrix to be appended to
	 * @param matrix2 Generic matric to append with
	 * @return Generic matrix containing the columns of both given generic matrices
	 */
	public static <T> GMatrix<T> mergeHorizontally(GMatrix<T> matrix1, GMatrix<T> matrix2)
	{
		return mergeVertically(matrix1.transpose(), matrix2.transpose()).transpose();
	}
	
	/**
	 * Appends the rows of a comparable matrix onto the end of another comparable matrix.
	 * @param <T> Type of data that the given matrices contain
	 * @param matrix1 Comparable matrix to be appended to
	 * @param matrix2 Comparable matrix to append with
	 * @return Comparable matrix containing the rows of both given comparable matrices
	 */
	public static <T extends Comparable<T>> CMatrix<T> mergeVertically(CMatrix<T> matrix1, CMatrix<T> matrix2)
	{
		return ConvertMatrix.toComparable(mergeVertically(ConvertMatrix.toGeneric(matrix1), ConvertMatrix.toGeneric(matrix2)));
	}
	
	/**
	 * Appends the columns of a comparable matrix onto the end of another comparable matrix.
	 * @param <T> Type of data that the given matrices contain
	 * @param matrix1 Comparable matrix to be appended to
	 * @param matrix2 Comparable matrix to append with
	 * @return Comparable matrix containing the columns of both given comparable matrices
	 */
	public static <T extends Comparable<T>> CMatrix<T> mergeHorizontally(CMatrix<T> matrix1, CMatrix<T> matrix2)
	{
		return mergeVertically(matrix1.transpose(), matrix2.transpose()).transpose();
	}
	
	/**
	 * Appends the rows of given double matrices together.
	 * @param matrices Set of double matrices to append together
	 * @return Double matrix containing the rows of all given double matrices
	 */
	private static DMatrix mergeDoubleVertically(DMatrix... matrices)
	{
		DMatrix mergedMatrix = new DMatrix();
		
		for (int i = 0; i < matrices.length; i++)
		{
			DMatrix currentMatrix = matrices[i];
			
			if (currentMatrix.isEmpty())
				continue;
			
			for (int j = 0; j < currentMatrix.height(); j++)
			{
				Double[] row = new Double[currentMatrix.width()];
				for (int k = 0; k < currentMatrix.width(); k++)
					row[k] = currentMatrix.get(j, k);
				mergedMatrix.addRow(row);
			}
		}
		
		return mergedMatrix;
	}
	
	/**
	 * Appends the rows of given double matrices together.
	 * @param matrices Set of double matrices to append together
	 * @return Double matrix containing the rows of all given double matrices
	 */
	public static DMatrix mergeVertically(DMatrix... matrices)
	{
		return mergeDoubleVertically(matrices);
	}
	
	/**
	 * Appends the rows of two given double matrices together.
	 * @param matrix1 Double matrix to be appended to
	 * @param matrix2 Double matrix to append with
	 * @return Double matrix containing the rows of both given double matrices
	 */
	public static DMatrix mergeVertically(DMatrix matrix1, DMatrix matrix2)
	{
		return mergeDoubleVertically(matrix1, matrix2);
	}
	
	/**
	 * Appends the columns of given double matrices together.
	 * @param matrices Set of double matrices to append together
	 * @return Double matrix containing the columns of all given double matrices
	 */
	private static DMatrix mergeDoubleHorizontally(DMatrix... matrices)
	{
		DMatrix[] tMatrices = new DMatrix[matrices.length];
		
		for (int i = 0; i < matrices.length; i++)
			tMatrices[i] = matrices[i].transpose();
		
		return mergeVertically(tMatrices).transpose();
	}
	
	/**
	 * Appends the columns of given double matrices together.
	 * @param matrices Set of double matrices to append together
	 * @return Double matrix containing the columns of all given double matrices
	 */
	public static DMatrix mergeHorizontally(DMatrix... matrices)
	{
		return mergeDoubleHorizontally(matrices);
	}
	
	/**
	 * Appends the columns of two given double matrices together.
	 * @param matrix1 Double matrix to be appended to
	 * @param matrix2 Double matrix to append with
	 * @return Double matrix containing the columns of both given double matrices
	 */
	public static DMatrix mergeHorizontally(DMatrix matrix1, DMatrix matrix2)
	{
		return mergeDoubleHorizontally(matrix1, matrix2);
	}
	
	// !!! Non-Static Abstract Members !!!
	
	/**
	 * Clears the contents of the matrix.
	 */
	public void clear();
	
	/**
	 * Clears the contents of the matrix and replaces it with an empty matrix of a set height and width.
	 * @param height Integer value to set the matrix height to
	 * @param width Integer value to set the matrix width to
	 */
	public void clear(int height, int width);
	
	/**
	 * Gets a specific value from the matrix.
	 * @param row Row index of the value
	 * @param column Column index of the value
	 * @return The specific value at the row and column indexes of the matrix
	 */
	public Object get(int row, int column);
	
	/**
	 * Method returns a set of rows from the matrix as a new matrix.
	 * @param start The row index of the first row of the matrix to be extracted (inclusive)
	 * @param end The row index of the last row of the matrix to be extracted (exclusive)
	 * @return A matrix consisting of the selected rows
	 */
	public Matrix getRows(int start, int end);
	
	/**
	 * Method returns a row from the matrix as a new matrix.
	 * @param row The row index of the row of the matrix to be extracted
	 * @return A matrix consisting of the selected row
	 */
	public Matrix getRows(int row);
	
	/**
	 * Method returns a set of columns from the matrix as a new matrix.
	 * @param start The column index of the first column of the matrix to be extracted (inclusive)
	 * @param end The column index of the last column of the matrix to be extracted (exclusive)
	 * @return A matrix consisting of the selected columns
	 */
	public Matrix getColumns(int start, int end);
	
	/**
	 * Method returns a column from the matrix as a new matrix.
	 * @param column The column index of the column of the matrix to be extracted
	 * @return A matrix consisting of the selected column
	 */
	public Matrix getColumns(int column);
	
	/**
	 * Deletes and removes a defined range of rows from the matrix.
	 * @param start The row index of the first row of the matrix to be deleted (inclusive)
	 * @param end The row index of the last row of the matrix to be deleted (exclusive)
	 */
	public void deleteRows(int start, int end);
	
	/**
	 * Deletes and removes a defined range of columns from the matrix.
	 * @param start The column index of the first column of the matrix to be deleted (inclusive)
	 * @param end The column index of the last column of the matrix to be deleted (exclusive)
	 */
	public void deleteColumns(int start, int end);
	
	/**
	 * Gets the width of the matrix (i.e. the number of columns).
	 * @return The width of the matrix as an integer
	 */
	public int width();
	
	/**
	 * Gets the height of the matrix (i.e. the number of rows).
	 * @return The height of the matrix as an integer
	 */
	public int height();
	
	/**
	 * Returns whether the matrix is empty or not.
	 * @return Boolean value which is {@code true} if the matrix is empty, otherwise {@code false}
	 */
	public boolean isEmpty();
	
	/**
	 * Returns whether the matrix is square (the height and width are equal) or not.
	 * @return Boolean value which is {@code true} if the matrix is square, otherwise {@code false}
	 */
	public boolean isSquare();
	
	/**
	 * Method returns a transpose of the matrix.
	 * @return A transposed version of the matrix
	 */
	public Matrix transpose();
	
	/**
	 * Converts the matrix into a 2D array.
	 * @return A 2D array containing the matrix's values
	 */
	public Object[][] toArray();
	
	/**
	 * Maps a function across all values of the matrix.
	 * @param mapFunction A functional interface which contains the functional method to apply to the matrix values
	 */
	public void map(MapFunction mapFunction);
	
	/**
	 * Generates and returns a duplicated version of the matrix.
	 * @return A matrix consisting of the same values
	 */
	public Matrix clone();
	
	/**
	 * Displays the matrix on the console in a formatted way.
	 */
	public void show();
	
	/**
	 * Generates a new matrix iterator.
	 * @return An iterator for the matrix
	 */
	public Iterator<?> iterator();
}