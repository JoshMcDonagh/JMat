package main.java.matrices.utilities;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;

public class MatrixUtilities 
{
	public static DMatrix makeIdentityMatrix(int size)
	{
		DMatrix identityMatrix = new DMatrix(size, size);
		for (int i = 0; i < size; i++)
			identityMatrix.set(i, i, 1.0);
		return identityMatrix;
	}
	
	public static <T extends Comparable<T>> GMatrix<T> comparableToGeneric(CMatrix<T> comparable)
	{
		GMatrix<T> generic = new GMatrix<T>(comparable.height(), comparable.width());
		
		for (int i = 0; i < comparable.height(); i++)
		{
			for (int j = 0; j < comparable.width(); j++)
				generic.set(i, j, comparable.get(i, j));
		}
		
		return generic;
	}
	
	public static <T extends Comparable<T>> CMatrix<T> genericToComparable(GMatrix<T> generic)
	{
		CMatrix<T> comparable = new CMatrix<T>(generic.height(), generic.width());
		
		for (int i = 0; i < generic.height(); i++)
		{ 
			for (int j = 0; j < generic.width(); j++)
				comparable.set(i,  j,  generic.get(i,  j));
		}
		
		return comparable;
	}
	
	public static CMatrix<Double> doubleToComparable(DMatrix matrix)
	{
		CMatrix<Double> generic = new CMatrix<Double>(matrix.height(), matrix.width());
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
				generic.set(i, j, matrix.get(i, j));
		}
		
		return generic;
	}
	
	public static DMatrix comparableToDouble(CMatrix<Double> comparable)
	{
		DMatrix dMatrix = new DMatrix(comparable.height(), comparable.width());
		
		for (int i = 0; i < comparable.height(); i++)
		{
			for (int j = 0; j < comparable.width(); j++)
				dMatrix.set(i, j, comparable.get(i, j));
		}
		
		return dMatrix;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> GMatrix<T> mergeGenericVertically(GMatrix<T> matrix1, GMatrix<T> matrix2)
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
	
	public static <T> GMatrix<T> mergeGenericHorizontally(GMatrix<T> matrix1, GMatrix<T> matrix2)
	{
		return mergeGenericVertically(matrix1.transpose(), matrix2.transpose()).transpose();
	}
	
	public static <T extends Comparable<T>> CMatrix<T> mergeComparableVertically(CMatrix<T> matrix1, CMatrix<T> matrix2)
	{
		return genericToComparable(mergeGenericVertically(matrix1, matrix2));
	}
	
	public static <T extends Comparable<T>> CMatrix<T> mergeComparableHorizontally(CMatrix<T> matrix1, CMatrix<T> matrix2)
	{
		return mergeComparableVertically(matrix1.transpose(), matrix2.transpose()).transpose();
	}
	
	public static DMatrix mergeDoubleVertically(DMatrix... matrices)
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
	
	public static DMatrix mergeDoubleHorizontally(DMatrix... matrices)
	{
		DMatrix[] tMatrices = new DMatrix[matrices.length];
		
		for (int i = 0; i < matrices.length; i++)
			tMatrices[i] = matrices[i].transpose();
		
		return mergeDoubleVertically(tMatrices).transpose();
	}
	
	public static <T extends Comparable<T>> CMatrix<T> sort(int index, CMatrix<T> matrix, boolean ascending)
	{
		if (matrix.height() <= 1)
			return matrix;
		
		int split = matrix.height() / 2;
		CMatrix<T> subMatrix1 = sort(index,  matrix.getRows(0, split), ascending);
		CMatrix<T> subMatrix2 = sort(index,  matrix.getRows(split, matrix.height()), ascending);
		
		return merge(index, subMatrix1, subMatrix2, ascending);
	}
	
	public static <T extends Comparable<T>> CMatrix<T> sort(int index, CMatrix<T> matrix)
	{
		return sort(index, matrix, true);
	}
	
	public static DMatrix sort(int index, DMatrix matrix, boolean ascending)
	{
		return comparableToDouble(sort(index, doubleToComparable(matrix), ascending));
	}
	
	public static DMatrix sort(int index, DMatrix matrix)
	{
		return sort(index, matrix, true);
	}
	
	private static <T extends Comparable<T>> CMatrix<T> merge(int index, CMatrix<T> matrix1, CMatrix<T> matrix2, boolean ascending)
	{
		CMatrix<T> mergedMatrix = new CMatrix<T>();
		
		while (!matrix1.isEmpty() || !matrix2.isEmpty())
		{
			if (matrix1.isEmpty())
			{
				mergedMatrix = MatrixUtilities.mergeComparableVertically(mergedMatrix, matrix2);
				matrix2.clear();
				continue;
			}
			
			if (matrix2.isEmpty())
			{
				mergedMatrix = MatrixUtilities.mergeComparableVertically(mergedMatrix, matrix1);
				matrix1.clear();
				continue;
			}
			
			if ((ascending && matrix1.get(0, index).compareTo(matrix2.get(0, index)) <= 0) || (!ascending && matrix1.get(0, index).compareTo(matrix2.get(0, index)) >= 0))
			{
				mergedMatrix = MatrixUtilities.mergeComparableVertically(mergedMatrix, matrix1.getRows(0, 1));
				matrix1.deleteRows(0, 1);
			}
			
			else
			{
				mergedMatrix = MatrixUtilities.mergeComparableVertically(mergedMatrix, matrix2.getRows(0, 1));
				matrix2.deleteRows(0, 1);
			}
		}
		
		return mergedMatrix;
	}
}
