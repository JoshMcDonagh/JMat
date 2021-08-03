package test.java.matrices;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.java.matrices.CMatrix;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class CMatrixTest 
{
	@Test
	void constructEmptyMatrix() 
	{
		CMatrix<Integer> matrix = new CMatrix<Integer>();
		assertEquals(matrix.width(), matrix.height(), 0);
	}
	
	@Test
	void constructMatrixOfHeight()
	{
		int height = 5;
		CMatrix<Integer> matrix = new CMatrix<Integer>(height, 0);
		assertEquals(matrix.height(), height);
	}
	
	@Test
	void constructMatrixOfWidth()
	{
		int width = 7;
		CMatrix<Integer> matrix = new CMatrix<Integer>(0, width);
		assertEquals(matrix.width(), width);
	}
	
	@Test
	void constructMatrixOf2DArray()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		
		assertEquals(matrix.height(), integers.length);
		assertEquals(matrix.width(), integers[0].length);
		
		for (int i = 0; i < integers.length; i++)
		{
			for (int j = 0; j < integers[i].length; j++)
				assertEquals(integers[i][j], matrix.get(i, j));
		}
	}
	
	@Test
	void getMultipleRows()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		int start = 1;
		int end = 3;
		
		Integer[][] subIntegers = Arrays.copyOfRange(integers, start, end);
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		CMatrix<Integer> subMatrix = matrix.getRows(start, end);
		
		assertEquals(subIntegers.length, subMatrix.height());
		assertEquals(subIntegers[0].length, subMatrix.width());
		
		for (int i = 0; i < subIntegers.length; i++)
		{
			for (int j = 0; j < subIntegers[i].length; j++)
				assertEquals(subIntegers[i][j], subMatrix.get(i, j));
		}
	}
	
	@Test
	void getSingleRow()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		int start = 1;
		int end = start + 1;
		
		Integer[][] subIntegers = Arrays.copyOfRange(integers, start, end);
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		CMatrix<Integer> subMatrix = matrix.getRows(start);
		
		assertEquals(subIntegers.length, subMatrix.height());
		assertEquals(subIntegers[0].length, subMatrix.width());
		
		for (int i = 0; i < subIntegers.length; i++)
		{
			for (int j = 0; j < subIntegers[i].length; j++)
				assertEquals(subIntegers[i][j], subMatrix.get(i, j));
		}
	}
	
	private Integer[] getArrayColumn(int index, Integer[][] integers)
	{
		Integer[] column = new Integer[integers.length];
		for (int i = 0; i < integers.length; i++)
			column[i] = integers[i][index];
		return column;
	}
	
	@Test
	void getMultipleColumns()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		int start = 1;
		int end = start + 2;
		
		Integer[][] subIntegers = {
				getArrayColumn(start, integers),
				getArrayColumn(end - 1, integers)
		};
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		CMatrix<Integer> subMatrix = matrix.getColumns(start, end);
		
		assertEquals(subIntegers.length, subMatrix.width());
		assertEquals(subIntegers[0].length, subMatrix.height());
		
		for (int i = 0; i < subIntegers.length; i++)
		{
			for (int j = 0; j < subIntegers[i].length; j++)
				assertEquals(subIntegers[i][j], subMatrix.get(j, i));
		}
	}
	
	@Test
	void getSingleColumn()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		int start = 1;
		
		Integer[][] subIntegers = {
				getArrayColumn(start, integers)
		};
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		CMatrix<Integer> subMatrix = matrix.getColumns(start);
		
		assertEquals(subIntegers.length, subMatrix.width());
		assertEquals(subIntegers[0].length, subMatrix.height());
		
		for (int i = 0; i < subIntegers.length; i++)
		{
			for (int j = 0; j < subIntegers[i].length; j++)
				assertEquals(subIntegers[i][j], subMatrix.get(j, i));
		}
	}
	
	@Test
	void transpose()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		CMatrix<Integer> matrixTranspose = matrix.transpose();
		
		assertEquals(integers.length, matrixTranspose.width());
		assertEquals(integers[0].length, matrixTranspose.height());
		
		for (int i = 0; i < integers.length; i++)
		{
			for (int j = 0; j < integers[i].length; j++)
			assertEquals(integers[i][j], matrixTranspose.get(j,  i));
		}
	}
	
	@Test
	void matrixClone()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		CMatrix<Integer> cloneMatrix = matrix.clone();
		
		assertEquals(matrix.height(), cloneMatrix.height());
		assertEquals(matrix.width(), cloneMatrix.width());
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
			assertEquals(matrix.get(i, j), cloneMatrix.get(i, j));
		}
	}
	
	@Test
	void trueEqualTo()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers2 = integers1;
		
		CMatrix<Integer> matrix1 = new CMatrix<Integer>(integers1);
		CMatrix<Integer> matrix2 = new CMatrix<Integer>(integers2);
		
		assertEquals(matrix1.equalTo(matrix2), true);
	}
	
	@Test
	void falseEqualTo()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers2 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 5, 2},
				{1, 3, 6, 4, 9}
		};
		
		CMatrix<Integer> matrix1 = new CMatrix<Integer>(integers1);
		CMatrix<Integer> matrix2 = new CMatrix<Integer>(integers2);
		
		assertEquals(matrix1.equalTo(matrix2), false);
	}
	
	@Test
	void trueContains()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers2 = {
				{7, 3},
				{1, 4},
		};
		
		CMatrix<Integer> matrix1 = new CMatrix<Integer>(integers1);
		CMatrix<Integer> matrix2 = new CMatrix<Integer>(integers2);
		
		assertTrue(matrix1.contains(matrix2));
	}
	
	@Test
	void falseContains()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		Integer[][] integers2 = {
				{4, 3},
				{1, 4},
		};
		
		CMatrix<Integer> matrix1 = new CMatrix<Integer>(integers1);
		CMatrix<Integer> matrix2 = new CMatrix<Integer>(integers2);
		
		assertFalse(matrix1.contains(matrix2));
	}
	
	@Test
	void columnMin()
	{
		Integer[][] integers = {
				{4},
				{2},
				{1},
				{7},
				{3}
		};
		
		int min = integers[0][0];
		for (int i = 0; i < integers.length; i++)
		{
			if (integers[i][0] < min)
				min = integers[i][0];
		}
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		int matMin = matrix.columnMin(0);
		
		assertEquals(matMin, min);
	}
	
	@Test
	void columnMax()
	{
		Integer[][] integers = {
				{4},
				{2},
				{1},
				{7},
				{3}
		};
		
		int max = integers[0][0];
		for (int i = 0; i < integers.length; i++)
		{
			if (integers[i][0] > max)
				max = integers[i][0];
		}
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		int matMax = matrix.columnMax(0);
		
		assertEquals(matMax, max);
	}
}
