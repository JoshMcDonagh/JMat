package main.java.matrices;

import java.util.ArrayList;

import main.java.matrices.matrixConversion.ConvertMatrix;

/**
 * Interface which is used to access static methods that provide matrix sorting functionality.
 * 
 * @author Joshua McDonagh
 *
 */
interface MatSorter 
{
	/**
	 * Sorts a double matrix using an assisted version of the insertion sort.
	 * @param index Column index to base the sort process on
	 * @param matrix Double matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted double matrix
	 */
	public static DMatrix assistedInsertionSort(int index, DMatrix matrix, boolean ascending)
	{
		int numOfBuckets = matrix.height();
		ArrayList<DMatrix> buckets = new ArrayList<DMatrix>(numOfBuckets);
		
		// Generates the buckets
		for (int i = 0; i < numOfBuckets; i++)
			buckets.add(new DMatrix());
		
		double maximum = 0;
		double minimum = 0;
		boolean isNotInitialised = true;
		
		// Finds the minimum and maximum values
		for (int i = 0; i < matrix.height(); i++)
		{
			double value = matrix.get(i, index);
			
			if (isNotInitialised || value > maximum)
				maximum = value;
			if (isNotInitialised || value < minimum)
				minimum = value;
			
			if (isNotInitialised)
				isNotInitialised = false;
		}
		
		double difference = maximum - minimum;
		
		// Adds rows to the buckets
		for (int i = 0; i < matrix.height(); i++)
		{
			DMatrix row = matrix.getRows(i);
			double value = row.get(0, index);
			int bucketIndex;
			if (ascending)
				bucketIndex = (int)((numOfBuckets - 1) * ((value - minimum) / difference));
			else
				bucketIndex = (int)((numOfBuckets - 1) * (1 - (value - minimum) / difference));
			buckets.set(bucketIndex, Matrix.mergeVertically(buckets.get(bucketIndex), row));
		}
		
		DMatrix flattenedMatrix = new DMatrix();
		
		// Flattens the list of buckets into a matrix
		for (int i = 0; i < numOfBuckets; i++)
			flattenedMatrix = (DMatrix) Matrix.mergeVertically(flattenedMatrix, buckets.get(i));
		
		return ConvertMatrix.toDouble(insertionSort(index, flattenedMatrix, ascending));
	}
	
	/**
	 * Sorts a comparable matrix using the insertion sort.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	private static <T extends Comparable<T>> CMatrix<T> insertionSort(int index, CMatrix<T> matrix, boolean ascending)
	{
		for (int i = 0; i < matrix.height(); i++)
		{
			CMatrix<T> key = matrix.getRows(i);
			int j;
			if (ascending)
			{
				for (j = i - 1; j >= 0 && matrix.get(j,  index).compareTo(key.get(0, index)) > 0; j--)
					matrix.replaceRows(j + 1, matrix.getRows(j));
			}
			else
			{
				for (j = i - 1; j >= 0 && matrix.get(j,  index).compareTo(key.get(0, index)) < 0; j--)
					matrix.replaceRows(j + 1, matrix.getRows(j));
			}
			matrix.replaceRows(j + 1, key);
		}
		return matrix;
	}
	
	/**
	 * Sorts a comparable matrix using the merge sort.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> mergeSort(int index, CMatrix<T> matrix, boolean ascending)
	{
		if (matrix.height() <= 1)
			return matrix;
		
		int split = matrix.height() / 2;
		CMatrix<T> subMatrix1 = mergeSort(index,  matrix.getRows(0, split), ascending);
		CMatrix<T> subMatrix2 = mergeSort(index,  matrix.getRows(split, matrix.height()), ascending);
		
		return merge(index, subMatrix1, subMatrix2, ascending);
	}
	
	/**
	 * Merges two comparable matrices together into a single sorted comparable matrix.
	 * @param <T> Type of data that the given matrices contains
	 * @param index Column index to base the merge process on
	 * @param matrix1 The first comparable matrix to merge
	 * @param matrix2 The second comparable matrix to merge
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return Sorted comparable matrix formed from merging the two given comparable matrices
	 */
	private static <T extends Comparable<T>> CMatrix<T> merge(int index, CMatrix<T> matrix1, CMatrix<T> matrix2, boolean ascending)
	{
		CMatrix<T> mergedMatrix = new CMatrix<T>();
		
		while (!matrix1.isEmpty() || !matrix2.isEmpty())
		{
			if (matrix1.isEmpty())
			{
				mergedMatrix = Matrix.mergeVertically(mergedMatrix, matrix2);
				matrix2.clear();
				continue;
			}
			
			if (matrix2.isEmpty())
			{
				mergedMatrix = Matrix.mergeVertically(mergedMatrix, matrix1);
				matrix1.clear();
				continue;
			}
			
			if ((ascending && matrix1.get(0, index).compareTo(matrix2.get(0, index)) <= 0) || (!ascending && matrix1.get(0, index).compareTo(matrix2.get(0, index)) >= 0))
			{
				mergedMatrix = Matrix.mergeVertically(mergedMatrix, matrix1.getRows(0, 1));
				matrix1.deleteRows(0, 1);
			}
			
			else
			{
				mergedMatrix = Matrix.mergeVertically(mergedMatrix, matrix2.getRows(0, 1));
				matrix2.deleteRows(0, 1);
			}
		}
		
		return mergedMatrix;
	}
}
