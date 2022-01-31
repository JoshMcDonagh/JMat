package main.java.matrices;

/**
 * Interface which is used to access static methods that provide matrix sorting functionality.
 * 
 * @author Joshua McDonagh
 *
 */
interface MatSorter 
{
	/**
	 * Checks whether a given double matrix is sorted or not based on a column index.
	 * @param index Column index to check
	 * @param matrix Double matrix to check
	 * @param ascending Boolean value which is {@code true} if the matrix should be sorted in an ascending way, otherwise {@code false}
	 * @return Boolean value as to whether the given double matrix is sorted ({@code true}) or unsorted ({@code false})
	 */
	private static boolean isSorted(int index, DMatrix matrix, boolean ascending)
	{
		for (int i = 1; i < matrix.height(); i++)
		{
			if (ascending && matrix.get(i, index) < matrix.get(i-1, index))
				return false;
			else if (!ascending && matrix.get(i, index) > matrix.get(i-1, index))
				return false;
		}
		return true;
	}
	
	/**
	 * Sorts a double matrix using a non-comparison sort derived from the bucket sort and pigeonhole sort.
	 * @param index Column index to base the sort process on
	 * @param matrix Double matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted double matrix
	 */
	static DMatrix recursiveBucketSort(int index, DMatrix matrix, boolean ascending)
	{
		if (matrix.height() < 2)
			return matrix.clone();
		
		int numOfBuckets = matrix.height();
		DMatrix[] buckets = new DMatrix[numOfBuckets];
		
		for (int i = 0; i < numOfBuckets; i++)
			buckets[i] = new DMatrix();
		
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
			
			if (difference == 0)
				bucketIndex = 0;
			else if (ascending)
				bucketIndex = (int)((numOfBuckets - 1.0) * ((value - minimum) / difference));
			else
				bucketIndex = (int)((numOfBuckets - 1.0) * (1.0 - ((value - minimum) / difference)));
			
			buckets[bucketIndex] = Matrix.mergeVertically(buckets[bucketIndex], row);
		}
		
		// If any bucket contains more than one element and is unsorted,
		// run the bucket sort on the bucket
		for (int i = 0; i < numOfBuckets; i++)
		{
			if (buckets[i].height() > 1 && !isSorted(index, buckets[i], ascending))
				buckets[i] = recursiveBucketSort(index, buckets[i], ascending);
		}
		
		// Flattens the list of buckets into a matrix
		DMatrix flattenedMatrix = new DMatrix();
		for (int i = 0; i < numOfBuckets; i++)
			flattenedMatrix = (DMatrix) Matrix.mergeVertically(flattenedMatrix, buckets[i]);
		
		return flattenedMatrix;
	}
	
	/**
	 * Sorts a comparable matrix using the insertion sort.
	 * @param <T> Type of data that the given matrix contains
	 * @param index Column index to base the sort process on
	 * @param matrix Comparable matrix to sort
	 * @param ascending Boolean value which is {@code true} if the matrix is to be sorted in an ascending way, otherwise {@code false}
	 * @return The sorted comparable matrix
	 */
	static <T extends Comparable<T>> CMatrix<T> insertionSort(int index, CMatrix<T> matrix, boolean ascending)
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
	static <T extends Comparable<T>> CMatrix<T> mergeSort(int index, CMatrix<T> matrix, boolean ascending)
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
