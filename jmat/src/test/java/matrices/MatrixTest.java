package test.java.matrices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;
import main.java.matrices.Matrix;
import main.java.matrices.matrixConversion.ConvertMatrix;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class MatrixTest 
{
	@Test
	void makeIndentityMatrix() 
	{
		DMatrix identityMatrix = Matrix.makeIdentityMatrix(3);
		
		Double[][] identityArray = {
				{1.0, 0.0, 0.0},
				{0.0, 1.0, 0.0},
				{0.0, 0.0, 1.0}
		};
		
		assertEquals(identityMatrix.height(), identityArray.length);
		assertEquals(identityMatrix.width(), identityArray[0].length);
		
		for (int i = 0; i < identityArray.length; i++)
		{
			for (int j = 0; j < identityArray[i].length; j++)
				assertEquals(identityMatrix.get(i, j), identityArray[i][j]);
		}
	}
	
	@Test
	void sortAscendingCMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.sort(column, cMatrix);
		
		boolean isSorted = true;
		
		for (int i = 1; i < sorted.height(); i++)
		{
			if (sorted.get(i, column) < sorted.get(i - 1, column))
			{
				isSorted = false;
				break;
			}
		}
		
		assertTrue(isSorted);
	}
	
	@Test
	void sortDescendingCMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.sort(column, cMatrix, false);
		
		boolean isSorted = true;
		
		for (int i = 1; i < sorted.height(); i++)
		{
			if (sorted.get(i, column) > sorted.get(i - 1, column))
			{
				isSorted = false;
				break;
			}
		}
		
		assertTrue(isSorted);
	}
	
	@Test
	void sortAscendingDMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix matrix = new DMatrix(height, width);
		matrix.intRandomise(1, 100);
		DMatrix sorted = Matrix.sort(column, matrix);
		
		boolean isSorted = true;
		
		for (int i = 1; i < sorted.height(); i++)
		{
			if (sorted.get(i, column) < sorted.get(i - 1, column))
			{
				isSorted = false;
				break;
			}
		}
		
		assertTrue(isSorted);
	}
	
	@Test
	void sortDescendingDMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix matrix = new DMatrix(height, width);
		matrix.intRandomise(1, 100);
		DMatrix sorted = Matrix.sort(column, matrix, false);
		
		boolean isSorted = true;
		
		for (int i = 1; i < sorted.height(); i++)
		{
			if (sorted.get(i, column) > sorted.get(i - 1, column))
			{
				isSorted = false;
				break;
			}
		}
		
		assertTrue(isSorted);
	}
	
	@Test
	void mergeVerticallyGMatrix()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers2 = {
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		GMatrix<Integer> matrix1 = new GMatrix<Integer>(integers1);
		GMatrix<Integer> matrix2 = new GMatrix<Integer>(integers2);
		GMatrix<Integer> matrix3 = Matrix.mergeVertically(matrix1, matrix2);
		
		assertEquals(matrix3.height(), integers3.length);
		assertEquals(matrix3.width(), integers3[0].length);
		
		for (int i = 0; i < integers3.length; i++)
		{
			for (int j = 0; j < integers3[i].length; j++)
				assertEquals(matrix3.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void mergeHorizontallyGMatrix()
	{
		Integer[][] integers1 = {
				{4, 7, 3},
				{2, 1, 4},
				{1, 3, 6},
				{2, 1, 4},
				{1, 3, 6}
		};
		
		Integer[][] integers2 = {
				{5, 5},
				{4, 2},
				{4, 9},
				{4, 2},
				{4, 9}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		GMatrix<Integer> matrix1 = new GMatrix<Integer>(integers1);
		GMatrix<Integer> matrix2 = new GMatrix<Integer>(integers2);
		GMatrix<Integer> matrix3 = Matrix.mergeHorizontally(matrix1, matrix2);
		
		assertEquals(matrix3.height(), integers3.length);
		assertEquals(matrix3.width(), integers3[0].length);
		
		for (int i = 0; i < integers3.length; i++)
		{
			for (int j = 0; j < integers3[i].length; j++)
				assertEquals(matrix3.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void mergeVerticallyCMatrix()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers2 = {
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		CMatrix<Integer> matrix1 = new CMatrix<Integer>(integers1);
		CMatrix<Integer> matrix2 = new CMatrix<Integer>(integers2);
		CMatrix<Integer> matrix3 = Matrix.mergeVertically(matrix1, matrix2);
		
		assertEquals(matrix3.height(), integers3.length);
		assertEquals(matrix3.width(), integers3[0].length);
		
		for (int i = 0; i < integers3.length; i++)
		{
			for (int j = 0; j < integers3[i].length; j++)
				assertEquals(matrix3.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void mergeHorizontallyCMatrix()
	{
		Integer[][] integers1 = {
				{4, 7, 3},
				{2, 1, 4},
				{1, 3, 6},
				{2, 1, 4},
				{1, 3, 6}
		};
		
		Integer[][] integers2 = {
				{5, 5},
				{4, 2},
				{4, 9},
				{4, 2},
				{4, 9}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		CMatrix<Integer> matrix1 = new CMatrix<Integer>(integers1);
		CMatrix<Integer> matrix2 = new CMatrix<Integer>(integers2);
		CMatrix<Integer> matrix3 = Matrix.mergeHorizontally(matrix1, matrix2);
		
		assertEquals(matrix3.height(), integers3.length);
		assertEquals(matrix3.width(), integers3[0].length);
		
		for (int i = 0; i < integers3.length; i++)
		{
			for (int j = 0; j < integers3[i].length; j++)
				assertEquals(matrix3.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void mergeVerticallyDMatrixTwo()
	{
		Double[][] integers1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] integers2 = {
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] integers3 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(integers1);
		DMatrix matrix2 = new DMatrix(integers2);
		DMatrix matrix3 = Matrix.mergeVertically(matrix1, matrix2);
		
		assertEquals(matrix3.height(), integers3.length);
		assertEquals(matrix3.width(), integers3[0].length);
		
		for (int i = 0; i < integers3.length; i++)
		{
			for (int j = 0; j < integers3[i].length; j++)
				assertEquals(matrix3.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void mergeVerticallyDMatrixThree()
	{
		Double[][] integers1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0}
		};
		
		Double[][] integers2 = {
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] integers3 = {
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] integers4 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(integers1);
		DMatrix matrix2 = new DMatrix(integers2);
		DMatrix matrix3 = new DMatrix(integers3);
		DMatrix matrix4 = Matrix.mergeVertically(matrix1, matrix2, matrix3);
		
		assertEquals(matrix4.height(), integers4.length);
		assertEquals(matrix4.width(), integers4[0].length);
		
		for (int i = 0; i < integers4.length; i++)
		{
			for (int j = 0; j < integers4[i].length; j++)
				assertEquals(matrix4.get(i, j), integers4[i][j]);
		}
	}
	
	@Test
	void mergeHorizontallyDMatrixTwo()
	{
		Double[][] integers1 = {
				{4.0, 7.0, 3.0},
				{2.0, 1.0, 4.0},
				{1.0, 3.0, 6.0},
				{2.0, 1.0, 4.0},
				{1.0, 3.0, 6.0}
		};
		
		Double[][] integers2 = {
				{5.0, 5.0},
				{4.0, 2.0},
				{4.0, 9.0},
				{4.0, 2.0},
				{4.0, 9.0}
		};
		
		Double[][] integers3 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(integers1);
		DMatrix matrix2 = new DMatrix(integers2);
		DMatrix matrix3 = Matrix.mergeHorizontally(matrix1, matrix2);
		
		assertEquals(matrix3.height(), integers3.length);
		assertEquals(matrix3.width(), integers3[0].length);
		
		for (int i = 0; i < integers3.length; i++)
		{
			for (int j = 0; j < integers3[i].length; j++)
				assertEquals(matrix3.get(i, j), integers3[i][j]);
		}
	}
	
	@Test 
	void mergeHorizontallyDMatrixThree()
	{
		Double[][] integers1 = {
				{4.0, 7.0},
				{2.0, 1.0},
				{1.0, 3.0},
				{2.0, 1.0},
				{1.0, 3.0}
		};
		
		Double[][] integers2 = {
				{3.0},
				{4.0},
				{6.0},
				{4.0},
				{6.0}
		};
		
		Double[][] integers3 = {
				{5.0, 5.0},
				{4.0, 2.0},
				{4.0, 9.0},
				{4.0, 2.0},
				{4.0, 9.0}
		};
		
		Double[][] integers4 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(integers1);
		DMatrix matrix2 = new DMatrix(integers2);
		DMatrix matrix3 = new DMatrix(integers3);
		DMatrix matrix4 = Matrix.mergeHorizontally(matrix1, matrix2, matrix3);
		
		assertEquals(matrix4.height(), integers4.length);
		assertEquals(matrix4.width(), integers4[0].length);
		
		for (int i = 0; i < integers4.length; i++)
		{
			for (int j = 0; j < integers4[i].length; j++)
				assertEquals(matrix4.get(i, j), integers4[i][j]);
		}
	}
}