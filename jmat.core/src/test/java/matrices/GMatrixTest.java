package test.java.matrices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.java.matrices.GMatrix;
import main.java.matrices.MapFunction;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class GMatrixTest 
{
	@Test
	void constructEmptyMatrix() 
	{
		GMatrix<Integer> matrix = new GMatrix<Integer>();
		assertEquals(matrix.width(), matrix.height(), 0);
	}
	
	@Test
	void constructMatrixOfHeight()
	{
		int height = 5;
		GMatrix<Integer> matrix = new GMatrix<Integer>(height, 0);
		assertEquals(matrix.height(), height);
	}
	
	@Test
	void constructMatrixOfWidth()
	{
		int width = 7;
		GMatrix<Integer> matrix = new GMatrix<Integer>(0, width);
		assertEquals(matrix.width(), width);
	}
	
	@Test
	void constructMatrixOf2DArray()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		assertEquals(matrix.height(), integers.length);
		assertEquals(matrix.width(), integers[0].length);
		
		for (int i = 0; i < integers.length; i++)
		{
			for (int j = 0; j < integers[i].length; j++)
				assertEquals(integers[i][j], matrix.get(i, j));
		}
	}
	
	@Test
	void clear()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		matrix.clear();
		
		assertEquals(matrix.width(), matrix.height(), 0);
	}
	
	@Test
	void clearWithHeightAndWidth()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		int height = 9;
		int width = 5;
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		matrix.clear(height, width);
		
		assertEquals(matrix.height(), height);
		assertEquals(matrix.width(), width);
	}
	
	@Test
	void get()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
				assertEquals(matrix.get(i, j), integers[i][j]);
		}
	}
	
	@Test
	void set()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		int row = 1;
		int column = 2;
		int value = 6;
		
		matrix.set(row, column, value);
		assertEquals(matrix.get(row, column), value);
	}
	
	@Test
	void appendToRow()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		int row = 1;
		int column = 3;
		int value = 7;
		
		matrix.appendToRow(row, value);
		assertEquals(matrix.height(), integers.length);
		assertEquals(matrix.width(), integers[0].length + 1);
		assertEquals(matrix.get(row, column), value);
	}
	
	@Test
	void appendToColumn()
	{
		Integer[][] integers = {
				{4, 7, 3},
				{2, 1, 4}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		int row = 2;
		int column = 2;
		int value = 16;
		
		matrix.appendToColumn(column, value);
		assertEquals(matrix.height(), integers.length + 1);
		assertEquals(matrix.width(), integers[0].length);
		assertEquals(matrix.get(row, column), value);
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
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> subMatrix = matrix.getRows(start, end);
		
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
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> subMatrix = matrix.getRows(start);
		
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
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> subMatrix = matrix.getColumns(start, end);
		
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
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> subMatrix = matrix.getColumns(start);
		
		assertEquals(subIntegers.length, subMatrix.width());
		assertEquals(subIntegers[0].length, subMatrix.height());
		
		for (int i = 0; i < subIntegers.length; i++)
		{
			for (int j = 0; j < subIntegers[i].length; j++)
				assertEquals(subIntegers[i][j], subMatrix.get(j, i));
		}
	}
	
	@Test
	void addRow()
	{
		Integer[] integers = {4, 7, 3, 5, 5};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>();
		matrix.addRow(integers);
		
		assertEquals(integers.length, matrix.width());
		assertEquals(matrix.height(), 1);
		
		for (int i = 0; i < integers.length; i++)
			assertEquals(matrix.get(0, i), integers[i]);
	}
	
	@Test
	void addColumn()
	{
		Integer[] integers = {4, 7, 3, 5, 5};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>();
		matrix.addColumn(integers);
		
		assertEquals(integers.length, matrix.height());
		assertEquals(matrix.width(), 1);
		
		for (int i = 0; i < integers.length; i++)
			assertEquals(matrix.get(i, 0), integers[i]);
	}
	
	@Test
	void deleteRows()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3},
				{5, 2, 1, 7, 6}
		};
		
		Integer[][] integers2 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{5, 2, 1, 7, 6}
		};
		
		int start = 2;
		int end = 4;
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers1);
		matrix.deleteRows(start, end);
		
		assertEquals(matrix.height(), integers2.length);
		assertEquals(matrix.width(), integers2[0].length);
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
				assertEquals(matrix.get(i, j), integers2[i][j]);
		}
	}
	
	@Test
	void deleteColumns()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3},
				{5, 2, 1, 7, 6}
		};
		
		Integer[][] integers2 = {
				{4, 7, 5},
				{2, 1, 2},
				{1, 3, 9},
				{3, 2, 3},
				{5, 2, 6}
		};
		
		int start = 2;
		int end = 4;
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers1);
		matrix.deleteColumns(start, end);
		
		assertEquals(matrix.height(), integers2.length);
		assertEquals(matrix.width(), integers2[0].length);
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
				assertEquals(matrix.get(i, j), integers2[i][j]);
		}
	}
	
	@Test
	void insertRows()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{5, 2, 1, 7, 6}
		};
		
		Integer[][] integers2 = {
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix1 = new GMatrix<Integer>(integers1);
		GMatrix<Integer> matrix2 = new GMatrix<Integer>(integers2);
		
		int row = 2;
		matrix1.insertRows(row, matrix2);
		
		assertEquals(matrix1.height(), integers3.length);
		assertEquals(matrix1.width(), integers3[0].length);
		
		for (int i = 0; i < matrix1.height(); i++)
		{
			for (int j = 0; j < matrix1.width(); j++)
				assertEquals(matrix1.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void insertColumns()
	{
		Integer[][] integers1 = {
				{4, 7, 5},
				{2, 1, 2},
				{1, 3, 9},
				{3, 2, 3},
				{5, 2, 6}
		};
		
		Integer[][] integers2 = {
				{3, 5},
				{4, 4},
				{6, 4},
				{1, 5},
				{1, 7}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix1 = new GMatrix<Integer>(integers1);
		GMatrix<Integer> matrix2 = new GMatrix<Integer>(integers2);
		
		int column = 2;
		matrix1.insertColumns(column, matrix2);

		assertEquals(matrix1.height(), integers3.length);
		assertEquals(matrix1.width(), integers3[0].length);
		
		for (int i = 0; i < matrix1.height(); i++)
		{
			for (int j = 0; j < matrix1.width(); j++)
				assertEquals(matrix1.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void replaceRows()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{5, 2, 1, 7, 6}
		};
		
		Integer[][] integers2 = {
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix1 = new GMatrix<Integer>(integers1);
		GMatrix<Integer> matrix2 = new GMatrix<Integer>(integers2);
		
		int row = 2;
		matrix1.replaceRows(row, matrix2);
		
		assertEquals(matrix1.height(), integers3.length);
		assertEquals(matrix1.width(), integers3[0].length);
		
		for (int i = 0; i < matrix1.height(); i++)
		{
			for (int j = 0; j < matrix1.width(); j++)
				assertEquals(matrix1.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void replaceColumns()
	{
		Integer[][] integers1 = {
				{4, 7, 7, 3, 5},
				{2, 1, 1, 4, 2},
				{1, 3, 3, 6, 9},
				{3, 2, 2, 1, 3},
				{5, 2, 2, 1, 6}
		};
		
		Integer[][] integers2 = {
				{3, 5},
				{4, 4},
				{6, 4},
				{1, 5},
				{1, 7}
		};
		
		Integer[][] integers3 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9},
				{3, 2, 1, 5, 3},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix1 = new GMatrix<Integer>(integers1);
		GMatrix<Integer> matrix2 = new GMatrix<Integer>(integers2);
		
		int column = 2;
		matrix1.replaceColumns(column, matrix2);

		assertEquals(matrix1.height(), integers3.length);
		assertEquals(matrix1.width(), integers3[0].length);
		
		for (int i = 0; i < matrix1.height(); i++)
		{
			for (int j = 0; j < matrix1.width(); j++)
				assertEquals(matrix1.get(i, j), integers3[i][j]);
		}
	}
	
	@Test
	void setEmptyValue()
	{
		Integer[][] integers = {
				{1, 3, 6, 4},
				{3, 2, 1, 5, 3}
		};
		
		int emptyValue = 10;
		
		GMatrix<Integer> matrix = new GMatrix<Integer>();
		matrix.setEmptyValue(emptyValue);
		matrix.addRow(integers[0]);
		matrix.addRow(integers[1]);
		
		assertEquals(matrix.get(0, 4), emptyValue);
	}
	
	@Test
	void width()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		assertEquals(matrix.width(), integers[0].length);
	}
	
	@Test
	void height()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		assertEquals(matrix.height(), integers.length);
	}
	
	@Test
	void trueIsEmpty()
	{
		GMatrix<Integer> matrix = new GMatrix<Integer>();
		
		assertTrue(matrix.isEmpty());
	}
	
	@Test
	void falseIsEmpty()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		assertFalse(matrix.isEmpty());
	}
	
	@Test
	void trueIsSquare()
	{
		Integer[][] integers = {
				{4, 7, 7, 3, 5},
				{2, 1, 1, 4, 2},
				{1, 3, 3, 6, 9},
				{3, 2, 2, 1, 3},
				{5, 2, 2, 1, 6}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		assertTrue(matrix.isSquare());
	}
	
	@Test
	void falseIsSquare()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{5, 2, 1, 7, 6}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		assertFalse(matrix.isSquare());
	}
	
	@Test
	void transpose()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> matrixTranspose = matrix.transpose();
		
		assertEquals(integers.length, matrixTranspose.width());
		assertEquals(integers[0].length, matrixTranspose.height());
		
		for (int i = 0; i < integers.length; i++)
		{
			for (int j = 0; j < integers[i].length; j++)
			assertEquals(integers[i][j], matrixTranspose.get(j,  i));
		}
	}
	
	@Test
	void flattenToRow()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		ArrayList<Integer> flattenedIntegers = new ArrayList<Integer>();
		
		for (Integer[] intList : integers)
		{
			for (int value : intList)
				flattenedIntegers.add(value);
		}
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> flattenedMatrix = matrix.flattenToRow();
		
		assertEquals(flattenedMatrix.height(), 1);
		assertEquals(flattenedMatrix.width(), flattenedIntegers.size());
		
		for (int i = 0; i < flattenedMatrix.width(); i++)
			assertEquals(flattenedMatrix.get(0, i), flattenedIntegers.get(i));
	}
	
	@Test
	void flattenToColumn()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		ArrayList<Integer> flattenedIntegers = new ArrayList<Integer>();
		
		for (Integer[] intList : integers)
		{
			for (int value : intList)
				flattenedIntegers.add(value);
		}
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> flattenedMatrix = matrix.flattenToColumn();
		
		assertEquals(flattenedMatrix.height(), flattenedIntegers.size());
		assertEquals(flattenedMatrix.width(), 1);
		
		for (int i = 0; i < flattenedMatrix.height(); i++)
			assertEquals(flattenedMatrix.get(i, 0), flattenedIntegers.get(i));
	}
	
	@Test
	void toArray()
	{
		Integer[][] integers1 = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers1);
		Object[][] integers2 = matrix.toArray();
		
		assertEquals(integers1.length, integers2.length);
		assertEquals(integers1[0].length, integers2[0].length);
		
		for (int i = 0; i < integers1.length; i++)
		{
			for (int j = 0; j < integers1[i].length; j++)
				assertEquals(integers1[i][j], (int) integers2[i][j]);
		}
	}
	
	@Test
	void map()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		MapFunction mapFunction = x -> (int)x + 10;
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		matrix.map(mapFunction);
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
				assertEquals(matrix.get(i, j), mapFunction.eval(integers[i][j]));
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
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		GMatrix<Integer> cloneMatrix = matrix.clone();
		
		assertEquals(matrix.height(), cloneMatrix.height());
		assertEquals(matrix.width(), cloneMatrix.width());
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
			assertEquals(matrix.get(i, j), cloneMatrix.get(i, j));
		}
	}
	
	@Test
	void iterator()
	{
		Integer[][] integers = {
				{4, 7, 3, 5, 5},
				{2, 1, 4, 4, 2},
				{1, 3, 6, 4, 9}
		};
		
		int elementCount = integers.length * integers[0].length;
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		int count = 0;
		int row = 0;
		for (int value : matrix)
		{
			int column =  count % integers[0].length;
			assertEquals(value, integers[row][column]);
			
			if (count % integers[0].length == integers[0].length - 1)
				row++;
			
			count++;
		}
		
		assertEquals(count, elementCount);
	}
}
