package test.java.matrices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.matrices.CMatrix;
import main.java.matrices.ConvertMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;
import main.java.matrices.Matrix;

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
	void sortByColumnAscendingCMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.sortByColumn(column, cMatrix);
		
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
	void sortByColumnDescendingCMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.sortByColumn(column, cMatrix, false);
		
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
	void sortByColumnAscendingDMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix matrix = new DMatrix(height, width);
		matrix.intRandomise(1, 100);
		DMatrix sorted = Matrix.sortByColumn(column, matrix);
		
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
	void sortByColumnDescendingDMatrix()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix matrix = new DMatrix(height, width);
		matrix.intRandomise(1, 100);
		DMatrix sorted = Matrix.sortByColumn(column, matrix, false);
		
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
	void sortByColumnAscendingCMatrixMergeSort()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.mergeSortByColumn(column, cMatrix);
		
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
	void sortByColumnDescendingCMatrixMergeSort()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.mergeSortByColumn(column, cMatrix, false);
		
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
	void sortByColumnAscendingCMatrixInsertionSort()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.insertionSortByColumn(column, cMatrix);
		
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
	void sortByColumnDescendingCMatrixInsertionSort()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.insertionSortByColumn(column, cMatrix, false);
		
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
	void sortByColumnAscendingCMatrixQuickSort()
	{
		int column = 0;
		int height = 5;
		int width = 1;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.quickSortByColumn(column, cMatrix);
		
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
	void sortByColumnDescendingCMatrixQuickSort()
	{
		int column = 5;
		int height = 20;
		int width = 10;
		
		DMatrix dMatrix = new DMatrix(height, width);
		dMatrix.intRandomise(1, 100);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		CMatrix<Double> sorted = Matrix.quickSortByColumn(column, cMatrix, false);
		
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
		Double[][] doubles1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles2 = {
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles3 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = Matrix.mergeVertically(matrix1, matrix2);
		
		assertEquals(matrix3.height(), doubles3.length);
		assertEquals(matrix3.width(), doubles3[0].length);
		
		for (int i = 0; i < doubles3.length; i++)
		{
			for (int j = 0; j < doubles3[i].length; j++)
				assertEquals(matrix3.get(i, j), doubles3[i][j]);
		}
	}
	
	@Test
	void mergeVerticallyDMatrixThree()
	{
		Double[][] doubles1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0}
		};
		
		Double[][] doubles2 = {
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles3 = {
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles4 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = new DMatrix(doubles3);
		DMatrix matrix4 = Matrix.mergeVertically(matrix1, matrix2, matrix3);
		
		assertEquals(matrix4.height(), doubles4.length);
		assertEquals(matrix4.width(), doubles4[0].length);
		
		for (int i = 0; i < doubles4.length; i++)
		{
			for (int j = 0; j < doubles4[i].length; j++)
				assertEquals(matrix4.get(i, j), doubles4[i][j]);
		}
	}
	
	@Test
	void mergeHorizontallyDMatrixTwo()
	{
		Double[][] doubles1 = {
				{4.0, 7.0, 3.0},
				{2.0, 1.0, 4.0},
				{1.0, 3.0, 6.0},
				{2.0, 1.0, 4.0},
				{1.0, 3.0, 6.0}
		};
		
		Double[][] doubles2 = {
				{5.0, 5.0},
				{4.0, 2.0},
				{4.0, 9.0},
				{4.0, 2.0},
				{4.0, 9.0}
		};
		
		Double[][] doubles3 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = Matrix.mergeHorizontally(matrix1, matrix2);
		
		assertEquals(matrix3.height(), doubles3.length);
		assertEquals(matrix3.width(), doubles3[0].length);
		
		for (int i = 0; i < doubles3.length; i++)
		{
			for (int j = 0; j < doubles3[i].length; j++)
				assertEquals(matrix3.get(i, j), doubles3[i][j]);
		}
	}
	
	@Test 
	void mergeHorizontallyDMatrixThree()
	{				
		Double[][] doubles1 = {
				{4.0, 7.0},
				{2.0, 1.0},
				{1.0, 3.0},
				{2.0, 1.0},
				{1.0, 3.0}
		};
		
		Double[][] doubles2 = {
				{3.0},
				{4.0},
				{6.0},
				{4.0},
				{6.0}
		};
		
		Double[][] doubles3 = {
				{5.0, 5.0},
				{4.0, 2.0},
				{4.0, 9.0},
				{4.0, 2.0},
				{4.0, 9.0}
		};
		
		Double[][] doubles4 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = new DMatrix(doubles3);
		DMatrix matrix4 = Matrix.mergeHorizontally(matrix1, matrix2, matrix3);
		
		assertEquals(matrix4.height(), doubles4.length);
		assertEquals(matrix4.width(), doubles4[0].length);
		
		for (int i = 0; i < doubles4.length; i++)
		{
			for (int j = 0; j < doubles4[i].length; j++)
				assertEquals(matrix4.get(i, j), doubles4[i][j]);
		}
	}
}
