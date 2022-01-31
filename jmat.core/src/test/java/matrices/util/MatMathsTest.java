package test.java.matrices.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.matrices.DMatrix;
import main.java.matrices.Matrix;
import main.java.matrices.util.MatMaths;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class MatMathsTest 
{
	@Test
	void detOfIdentityMatrix() 
	{
		DMatrix matrix = Matrix.makeIdentityMatrix(3);
		
		assertEquals(MatMaths.determinantOf(matrix), 1);
	}
	
	@Test
	void determinantOf()
	{
		Double[][] doubles = {
				{5.0, 1.0, 10.0},
				{3.0, 9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		DMatrix matrix = new DMatrix(doubles);
		
		assertEquals(MatMaths.determinantOf(matrix), -212);
	}
	
	@Test
	void traceOf() 
	{
		Double[][] doubles = {
				{2.0, 6.0, 7.0},
				{6.0, -6.0, 8.0},
				{0.0, 2.0, 4.0}
		};
		
		double traceVal = 0;
		
		for (int i = 0; i < doubles.length; i++)
			traceVal += doubles[i][i];
		
		DMatrix matrix = new DMatrix(doubles);
		
		assertEquals(MatMaths.traceOf(matrix), traceVal);
	}
	
	@Test
	void minorsOf()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, 10.0},
				{3.0, 9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{59.0, -3.0, -51.0},
				{-3.0, -25.0, -1.0},
				{-86.0, -10.0, 42.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.minorsOf(matrix1).equalTo(matrix2));
	}
	
	@Test
	void cofactorsOf()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, 10.0},
				{3.0, 9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{59.0, 3.0, -51.0},
				{3.0, -25.0, 1.0},
				{-86.0, 10.0, 42.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.cofactorsOf(matrix1).equalTo(matrix2));
	}
	
	@Test
	void adjointOf()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{-67.0, -17.0, -86.0},
				{3.0, 95.0, -50.0},
				{57.0, 1.0, -48.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.adjointOf(matrix1).equalTo(matrix2));
	}
	
	@Test
	void inverseOf()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = MatMaths.mul(1.0 / MatMaths.determinantOf(matrix1), MatMaths.adjointOf(matrix1));
		
		assertTrue(MatMaths.inverseOf(matrix1).equalTo(matrix2));
	}
	
	@Test
	void addMatrices()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{2.0, 10.0, 4.0},
				{-6.0, -6.0, -3.0},
				{10.0, 1.0, 5.0}
		};
		
		Double[][] doubles3 = {
				{7.0, 11.0, -6.0},
				{-3.0, -15.0, 1.0},
				{16.0, 2.0, 12.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = new DMatrix(doubles3);
		
		assertTrue(MatMaths.add(matrix1, matrix2).equalTo(matrix3));
	}
	
	@Test
	void addScalar1()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = doubles1[i][j] + value;
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.add(matrix1, value).equalTo(matrix2));
	}
	
	@Test
	void addScalar2()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = value + doubles1[i][j];
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.add(value, matrix1).equalTo(matrix2));
	}
	
	@Test
	void subMatrices()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{2.0, 10.0, 4.0},
				{-6.0, -6.0, -3.0},
				{10.0, 1.0, 5.0}
		};
		
		Double[][] doubles3 = {
				{3.0, -9.0, -14.0},
				{9.0, -3.0, 7.0},
				{-4.0, 0.0, 2.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = new DMatrix(doubles3);
		
		assertTrue(MatMaths.sub(matrix1, matrix2).equalTo(matrix3));
	}
	
	@Test
	void subScalar1()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = doubles1[i][j] - value;
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.sub(matrix1, value).equalTo(matrix2));
	}
	
	@Test
	void subScalar2()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = value - doubles1[i][j];
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.sub(value, matrix1).equalTo(matrix2));
	}
	
	@Test
	void mulMatrices()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{2.0, 10.0, 4.0},
				{-6.0, -6.0, -3.0},
				{10.0, 1.0, 5.0}
		};
		
		Double[][] doubles3 = {
				{-96.0, 34.0, -33.0},
				{100.0, 88.0, 59.0},
				{76.0, 61.0, 56.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = new DMatrix(doubles3);
		
		assertTrue(MatMaths.mul(matrix1, matrix2).equalTo(matrix3));
	}
	
	@Test
	void mulScalar1()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = doubles1[i][j] * value;
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.mul(matrix1, value).equalTo(matrix2));
	}
	
	@Test
	void mulScalar2()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = value * doubles1[i][j];
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.mul(value, matrix1).equalTo(matrix2));
	}
	
	@Test
	void pow()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		int value = -2;
		
		Double[][] doubles2 = {
				{-(116.0/203401.0), -(281.0/406802.0), (2685.0/203401.0)},
				{-(1383.0/406802.0), (2231.0/203401.0), -(652.0/203401.0)},
				{-(1638.0/203401), -(461.0/406802.0), -(662.0/203401.0)}
		};
		
		DMatrix matrix1 = MatMaths.pow(new DMatrix(doubles1), value);
		
		assertEquals(matrix1.height(), doubles2.length);
		assertEquals(matrix1.width(), doubles2[0].length);
		
		for (int i = 0; i < matrix1.height(); i++)
		{
			for (int j = 0; j < matrix1.width(); j++)
			{
				double decimalPlaces = 100000;
				double value1 = Math.round(matrix1.get(i, j) * decimalPlaces) / decimalPlaces;
				double value2 = Math.round(doubles2[i][j] * decimalPlaces) / decimalPlaces;
				
				assertEquals(value1, value2);
			}
		}
	}
	
	@Test
	void divMatrices()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{2.0, 10.0, 4.0},
				{-6.0, -6.0, -3.0},
				{10.0, 1.0, 5.0}
		};
		
		Double[][] doubles3 = {
				{-(25.0/6.0), -(620.0/81.0), -(88.0/27.0)},
				{(5.0/6.0), (262.0/81.0), (56.0/27.0)},
				{(4.0/3.0), (190.0/81.0), (47.0/27.0)}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = MatMaths.div(matrix1, matrix2);
		
		assertEquals(matrix3.height(), doubles3.length);
		assertEquals(matrix3.width(), doubles3[0].length);
		
		for (int i = 0; i < matrix3.height(); i++)
		{
			for (int j = 0; j < matrix3.width(); j++)
			{
				double decimalPlaces = 100000;
				double value1 = Math.round(matrix3.get(i, j) * decimalPlaces) / decimalPlaces;
				double value2 = Math.round(doubles3[i][j] * decimalPlaces) / decimalPlaces;
				
				assertEquals(value1, value2);
			}
		}
	}
	
	@Test
	void divScalar1()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = doubles1[i][j] / value;
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.div(matrix1, value).equalTo(matrix2));
	}
	
	@Test
	void divScalar2()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		double value = 5;
		
		Double[][] doubles2 = new Double[3][3];
		
		for (int i = 0; i < doubles2.length; i++)
		{
			for (int j = 0; j < doubles2[i].length; j++)
				doubles2[i][j] = value / doubles1[i][j];
		}
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		
		assertTrue(MatMaths.div(value, matrix1).equalTo(matrix2));
	}
	
	@Test
	void rDiv()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{2.0, 10.0, 4.0},
				{-6.0, -6.0, -3.0},
				{10.0, 1.0, 5.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = MatMaths.mul(matrix1, MatMaths.inverseOf(matrix2));
		
		assertTrue(MatMaths.rDiv(matrix1, matrix2).equalTo(matrix3));
	}
	
	@Test
	void lDiv()
	{
		Double[][] doubles1 = {
				{5.0, 1.0, -10.0},
				{3.0, -9.0, 4.0},
				{6.0, 1.0, 7.0}
		};
		
		Double[][] doubles2 = {
				{2.0, 10.0, 4.0},
				{-6.0, -6.0, -3.0},
				{10.0, 1.0, 5.0}
		};
		
		DMatrix matrix1 = new DMatrix(doubles1);
		DMatrix matrix2 = new DMatrix(doubles2);
		DMatrix matrix3 = MatMaths.mul(MatMaths.inverseOf(matrix1), matrix2);
		
		assertTrue(MatMaths.lDiv(matrix1, matrix2).equalTo(matrix3));
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
		DMatrix matrixTranspose = MatMaths.transpose(matrix);
		
		assertEquals(doubles.length, matrixTranspose.width());
		assertEquals(doubles[0].length, matrixTranspose.height());
		
		for (int i = 0; i < doubles.length; i++)
		{
			for (int j = 0; j < doubles[i].length; j++)
			assertEquals(doubles[i][j], matrixTranspose.get(j,  i));
		}
	}
}
