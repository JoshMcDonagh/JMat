package main.java.matrices.maths;

import java.util.ArrayList;

import main.java.matrices.DMatrix;
import main.java.matrices.utilities.MatrixUtilities;

public class MatMaths 
{
	public static double det(DMatrix matrix)
	{
		if (!matrix.isSquare())
			throw new IllegalArgumentException("To find the determinant, the matrix must be square.");
		
		if (matrix.width() == 0)
			throw new IllegalArgumentException("To find the determinant, the matrix must not be empty.");
		
		if (matrix.width() == 1)
			return matrix.get(0, 0);
		
		if (matrix.width() == 2)
			return matrix.get(0, 0) * matrix.get(1, 1) - matrix.get(0, 1) * matrix.get(1, 0);
		
		ArrayList<Double> values = new ArrayList<Double>();
		
		for (int i = 0; i < matrix.width(); i++)
		{
			double value = matrix.get(0, i);
			
			DMatrix subMatrix = matrix.getRows(1, matrix.height());
			
			if (i == 0)
				subMatrix = subMatrix.getColumns(1, subMatrix.width());
			
			else if (i == matrix.width() - 1)
				subMatrix = subMatrix.getColumns(0, subMatrix.width() - 1);
			
			else
			{
				DMatrix subMatrix1 = subMatrix.getColumns(0, i);
				DMatrix subMatrix2 = subMatrix.getColumns(i + 1, subMatrix.width());
				subMatrix = MatrixUtilities.mergeDoubleHorizontally(subMatrix1, subMatrix2);
			}
			
			values.add(value * det(subMatrix));
		}
		
		double finalValue = 0;
		
		for (int i = 0; i < values.size(); i++)
		{
			if (i % 2 == 0)
				finalValue += values.get(i);
			else
				finalValue -= values.get(i);
		}
		
		return finalValue;
	}
	
	// https://www.dcode.fr/matrix-minors
	public static DMatrix minorOf(DMatrix matrix)
	{
		if (!matrix.isSquare())
			throw new IllegalArgumentException("To find the minors, the matrix must be square.");
		
		if (matrix.width() == 0)
			throw new IllegalArgumentException("To find the minors, the matrix must not be empty.");
		
		DMatrix minorMatrix = new DMatrix();
		
		if (matrix.width() == 2)
		{
			minorMatrix = new DMatrix();
			minorMatrix.addRow(matrix.get(1, 1), matrix.get(1, 0));
			minorMatrix.addRow(matrix.get(0, 1), matrix.get(0, 0));
			return minorMatrix;
		}
		else
			minorMatrix = new DMatrix(matrix.height(), matrix.width());
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
			{
				DMatrix subMatrix;
				
				if (i == 0)
					subMatrix = matrix.getRows(1, matrix.height());
				else if (i == matrix.height() - 1)
					subMatrix = matrix.getRows(0, matrix.height() - 1);
				else
				{
					DMatrix matrix1 = matrix.getRows(0, i);
					DMatrix matrix2 = matrix.getRows(i + 1, matrix.height());
					subMatrix = MatrixUtilities.mergeDoubleVertically(matrix1, matrix2);
				}
				
				if (j == 0)
					subMatrix = subMatrix.getColumns(1, matrix.width());
				else if (j == matrix.width() - 1)
					subMatrix = subMatrix.getColumns(0, matrix.width() - 1);
				else
				{
					DMatrix matrix1 = subMatrix.getColumns(0, j);
					DMatrix matrix2 = subMatrix.getColumns(j + 1, matrix.width());
					subMatrix = MatrixUtilities.mergeDoubleHorizontally(matrix1, matrix2);
				}
				
				minorMatrix.set(i, j, det(subMatrix));
			}
		}
		
		return minorMatrix;
	}
	
	public static DMatrix cofactorOf(DMatrix matrix)
	{
		if (!matrix.isSquare())
			throw new IllegalArgumentException("To find the cofactors, the matrix must be square.");
		
		if (matrix.width() == 0)
			throw new IllegalArgumentException("To find the cofactors, the matrix must not be empty.");
		
		DMatrix cofactorMatrix = minorOf(matrix);
		
		for (int i = 0; i < cofactorMatrix.height(); i++)
		{
			for (int j = 0; j < cofactorMatrix.width(); j++)
			{
				if (i % 2 != j % 2)
				{
					double val = cofactorMatrix.get(i, j) * -1;
					cofactorMatrix.set(i, j, val);
				}
			}
		}
		
		return cofactorMatrix;
	}
	
	public static DMatrix adj(DMatrix matrix)
	{
		return cofactorOf(matrix).transpose();
	}
	
	public static DMatrix inv(DMatrix matrix)
	{
		return mul((1 / det(matrix)), adj(matrix));
	}
	
	// Addition methods
	
	public static DMatrix add(DMatrix matrix1, DMatrix matrix2)
	{
		if (matrix1.height() != matrix2.height() || matrix1.width() != matrix2.width())
			throw new IllegalArgumentException("For addition, the dimensions of the two matrices must be equal.");
		
		DMatrix newMatrix = new DMatrix(matrix1.height(), matrix1.width());
		
		for (int i = 0; i < newMatrix.height(); i++)
		{
			for (int j = 0; j < newMatrix.width(); j++)
				newMatrix.set(i,  j, matrix1.get(i,  j) + matrix2.get(i,  j));
		}
		
		return newMatrix;
	}
	
	public static DMatrix add(DMatrix matrix, double scalar)
	{
		DMatrix newMatrix = matrix.clone();
		newMatrix.map(val -> (double) val + scalar);
		return newMatrix;
	}
	
	public static DMatrix add(double scalar, DMatrix matrix)
	{
		return add(matrix, scalar);
	}
	
	// Subtraction methods
	
	public static DMatrix sub(DMatrix matrix1, DMatrix matrix2)
	{
		if (matrix1.height() != matrix2.height() || matrix1.width() != matrix2.width())
			throw new IllegalArgumentException("For subtraction, the dimensions of the two matrices must be equal.");
		
		DMatrix newMatrix = new DMatrix(matrix1.height(), matrix1.width());
		
		for (int i = 0; i < newMatrix.height(); i++)
		{
			for (int j = 0; j < newMatrix.width(); j++)
				newMatrix.set(i,  j, matrix1.get(i,  j) - matrix2.get(i,  j));
		}
		
		return newMatrix;
	}
	
	public static DMatrix sub(DMatrix matrix, double scalar)
	{
		DMatrix newMatrix = matrix.clone();
		newMatrix.map(val -> (double) val - scalar);
		return newMatrix;
	}
	
	public static DMatrix sub(double scalar, DMatrix matrix)
	{
		DMatrix newMatrix = matrix.clone();
		newMatrix.map(val -> scalar - (double) val);
		return newMatrix;
	}
	
	// Multiplication methods
	
	public static DMatrix mul(DMatrix matrix1, DMatrix matrix2)
	{
		if (matrix1.width() != matrix2.height())
			throw new IllegalArgumentException("For multiplication, the height/width of the first matrix and the width/height of the second matrix must be the same.");
		
		DMatrix newMatrix = new DMatrix(matrix1.height(), matrix2.width());
		
		for (int i = 0; i < matrix1.height(); i++)
		{
			for (int j = 0; j < matrix2.width(); j++)
			{
				double sum = 0;
				
				for (int k = 0; k < matrix1.width(); k++)
					sum += matrix1.get(i, k) * matrix2.get(k, j);
				
				newMatrix.set(i, j, sum);
			}
		}
		
		return newMatrix;
	}
	
	public static DMatrix mul(DMatrix matrix, double scalar)
	{
		DMatrix newMatrix = matrix.clone();
		newMatrix.map(val -> (double) val * scalar);
		return newMatrix;
	}
	
	public static DMatrix mul(double scalar, DMatrix dMatrix)
	{
		return mul(dMatrix, scalar);
	}
	
	// Power method
	
	public static DMatrix pow(DMatrix matrix, int power)
	{
		if (!matrix.isSquare())
			throw new IllegalArgumentException("For power of, the matrix must be square.");
		
		if (power == 0)
			return MatrixUtilities.makeIdentityMatrix(matrix.height());
		
		if (power < 0)
		{
			matrix = inv(matrix);
			power = -power;
		}
		
		DMatrix newMatrix = matrix.clone();
		
		for (int i = 0; i < power - 1; i++)
			newMatrix = mul(newMatrix, matrix);
		
		return newMatrix;
	}
	
	// Matrix right divide methods
	
	public static DMatrix rDiv(DMatrix matrix1, DMatrix matrix2)
	{
		if (!matrix1.isSquare() || !matrix2.isSquare())
			throw new IllegalArgumentException("For right division, both matrices must be square.");
		
		return mul(matrix1, inv(matrix2));
	}
	
	// Matrix left divide methods
	
	public static DMatrix lDiv(DMatrix matrix1, DMatrix matrix2)
	{
		if (!matrix1.isSquare() || !matrix2.isSquare())
			throw new IllegalArgumentException("For left division, both matrices must be square.");
		
		return mul(inv(matrix1), matrix2);
	}
}
