package test.java.matrices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.java.matrices.DMatrix;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class DMatrixTest 
{
	@Test
	void constructEmptyMatrix() 
	{
		DMatrix matrix = new DMatrix();
		assertEquals(matrix.width(), matrix.height(), 0);
	}
	
	@Test
	void constructMatrixOfHeight()
	{
		int height = 5;
		DMatrix matrix = new DMatrix(height, 0);
		assertEquals(matrix.height(), height);
	}
	
	@Test
	void constructMatrixOfWidth()
	{
		int width = 7;
		DMatrix matrix = new DMatrix(0, width);
		assertEquals(matrix.width(), width);
	}
	
	@Test
	void constructMatrixOf2DArray()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0},
				{2.0, 1.0, 4.0}
		};
		
		DMatrix matrix = new DMatrix(doubles);
		
		assertEquals(matrix.height(), doubles.length);
		assertEquals(matrix.width(), doubles[0].length);
		
		for (int i = 0; i < doubles.length; i++)
		{
			for (int j = 0; j < doubles[i].length; j++)
				assertEquals(doubles[i][j], matrix.get(i, j));
		}
	}
	
	@Test
	void set()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0},
				{2.0, 1.0, 4.0}
		};
		
		DMatrix matrix = new DMatrix(doubles);
		
		double value = 9;
		int row = 1;
		int column = 1;
		matrix.set(row, column, value);
		
		assertEquals(matrix.get(row, column), value);
	}
	
	@Test
	void getMultipleRows()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		int start = 1;
		int end = 3;
		
		Double[][] subIntegers = Arrays.copyOfRange(doubles, start, end);
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix subMatrix = matrix.getRows(start, end);
		
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
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		int start = 1;
		int end = start + 1;
		
		Double[][] subDoubles = Arrays.copyOfRange(doubles, start, end);
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix subMatrix = matrix.getRows(start);
		
		assertEquals(subDoubles.length, subMatrix.height());
		assertEquals(subDoubles[0].length, subMatrix.width());
		
		for (int i = 0; i < subDoubles.length; i++)
		{
			for (int j = 0; j < subDoubles[i].length; j++)
				assertEquals(subDoubles[i][j], subMatrix.get(i, j));
		}
	}
	
	private Double[] getArrayColumn(int index, Double[][] doubles)
	{
		Double[] column = new Double[doubles.length];
		for (int i = 0; i < doubles.length; i++)
			column[i] = doubles[i][index];
		return column;
	}
	
	@Test
	void getMultipleColumns()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		int start = 1;
		int end = start + 2;
		
		Double[][] subDoubles = {
				getArrayColumn(start, doubles),
				getArrayColumn(end - 1, doubles)
		};
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix subMatrix = matrix.getColumns(start, end);
		
		assertEquals(subDoubles.length, subMatrix.width());
		assertEquals(subDoubles[0].length, subMatrix.height());
		
		for (int i = 0; i < subDoubles.length; i++)
		{
			for (int j = 0; j < subDoubles[i].length; j++)
				assertEquals(subDoubles[i][j], subMatrix.get(j, i));
		}
	}
	
	@Test
	void getSingleColumn()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		int start = 1;
		
		Double[][] subDoubles = {
				getArrayColumn(start, doubles)
		};
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix subMatrix = matrix.getColumns(start);
		
		assertEquals(subDoubles.length, subMatrix.width());
		assertEquals(subDoubles[0].length, subMatrix.height());
		
		for (int i = 0; i < subDoubles.length; i++)
		{
			for (int j = 0; j < subDoubles[i].length; j++)
				assertEquals(subDoubles[i][j], subMatrix.get(j, i));
		}
	}
	
	@Test
	void addRow()
	{
		int[] integers = {4, 7, 3, 5, 5};
		
		DMatrix matrix = new DMatrix();
		matrix.addRow(integers);
		
		assertEquals(integers.length, matrix.width());
		assertEquals(matrix.height(), 1);
		
		for (int i = 0; i < integers.length; i++)
			assertEquals(matrix.get(0, i), integers[i]);
	}
	
	@Test
	void addColumn()
	{
		int[] integers = {4, 7, 3, 5, 5};
		
		DMatrix matrix = new DMatrix();
		matrix.addColumn(integers);
		
		assertEquals(integers.length, matrix.height());
		assertEquals(matrix.width(), 1);
		
		for (int i = 0; i < integers.length; i++)
			assertEquals(matrix.get(i, 0), integers[i]);
	}
	
	@Test
	void doubleRandomise()
	{
		int height = 20;
		int width = 10;
		
		double min = 0.5;
		double max = 5.2;
		
		DMatrix matrix = new DMatrix(height, width);
		matrix.doubleRandomise(min, max);
		
		for (int i = 0; i < matrix.width(); i++)
		{
			assertTrue(matrix.columnMin(i) >= min);
			assertTrue(matrix.columnMax(i) <= max);
		}
	}
	
	@Test
	void intRandomise()
	{
		int height = 20;
		int width = 10;
		
		int min = 0;
		int max = 5;
		
		DMatrix matrix = new DMatrix(height, width);
		matrix.intRandomise(min, max);
		
		for (int i = 0; i < matrix.width(); i++)
		{
			assertTrue(matrix.columnMin(i) >= (double) min);
			assertTrue(matrix.columnMax(i) <= (double) max);
		}
	}
	
	@Test
	void transpose()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix matrixTranspose = matrix.transpose();
		
		assertEquals(doubles.length, matrixTranspose.width());
		assertEquals(doubles[0].length, matrixTranspose.height());
		
		for (int i = 0; i < doubles.length; i++)
		{
			for (int j = 0; j < doubles[i].length; j++)
			assertEquals(doubles[i][j], matrixTranspose.get(j,  i));
		}
	}
	
	@Test
	void flattenToRow()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		ArrayList<Double> flattenedDoubles = new ArrayList<Double>();
		
		for (Double[] doubleList : doubles)
		{
			for (double value : doubleList)
				flattenedDoubles.add(value);
		}
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix flattenedMatrix = matrix.flattenToRow();
		
		assertEquals(flattenedMatrix.height(), 1);
		assertEquals(flattenedMatrix.width(), flattenedDoubles.size());
		
		for (int i = 0; i < flattenedMatrix.width(); i++)
			assertEquals(flattenedMatrix.get(0, i), flattenedDoubles.get(i));
	}
	
	@Test
	void flattenToColumn()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		ArrayList<Double> flattenedDoubles = new ArrayList<Double>();
		
		for (Double[] doubleList : doubles)
		{
			for (double value : doubleList)
				flattenedDoubles.add(value);
		}
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix flattenedMatrix = matrix.flattenToColumn();
		
		assertEquals(flattenedMatrix.height(), flattenedDoubles.size());
		assertEquals(flattenedMatrix.width(), 1);
		
		for (int i = 0; i < flattenedMatrix.height(); i++)
			assertEquals(flattenedMatrix.get(i, 0), flattenedDoubles.get(i));
	}
	
	@Test
	void trueEqualTo()
	{
		Double[][] doubles1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles2 = doubles1;
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertEquals(matrix1.equalTo(matrix2), true);
	}
	
	@Test
	void falseEqualTo()
	{
		Double[][] doubles1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles2 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 5.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertEquals(matrix1.equalTo(matrix2), false);
	}
	
	@Test
	void trueContains()
	{
		Double[][] doubles1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles2 = {
				{7.0, 3.0},
				{1.0, 4.0},
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertEquals(matrix1.contains(matrix2), true);
	}
	
	@Test
	void falseContains()
	{
		Double[][] doubles1 = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		Double[][] doubles2 = {
				{4.0, 3.0},
				{1.0, 4.0},
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertEquals(matrix1.contains(matrix2), false);
	}
	
	@Test
	void matrixClone()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0, 5.0},
				{2.0, 1.0, 4.0, 4.0, 2.0},
				{1.0, 3.0, 6.0, 4.0, 9.0}
		};
		
		DMatrix matrix = new DMatrix(doubles);
		DMatrix cloneMatrix = matrix.clone();
		
		assertEquals(matrix.height(), cloneMatrix.height());
		assertEquals(matrix.width(), cloneMatrix.width());
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
			assertEquals(matrix.get(i, j), cloneMatrix.get(i, j));
		}
	}
}
