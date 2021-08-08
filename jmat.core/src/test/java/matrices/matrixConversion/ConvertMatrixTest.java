package test.java.matrices.matrixConversion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;
import main.java.matrices.matrixConversion.ConvertMatrix;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class ConvertMatrixTest 
{
	@Test
	void comparableToGeneric() 
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		CMatrix<Double> cMatrix = new CMatrix<Double>(doubles);
		GMatrix<Double> gMatrix = ConvertMatrix.toGeneric(cMatrix);
		
		assertEquals(cMatrix.height(), gMatrix.height());
		assertEquals(cMatrix.width(), gMatrix.width());
		
		for (int i = 0; i < cMatrix.height(); i++)
		{
			for (int j = 0; j < cMatrix.width(); j++)
				assertEquals(cMatrix.get(i, j), gMatrix.get(i, j));
		}
	}
	
	@Test
	void doubleToGeneric()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		DMatrix dMatrix = new DMatrix(doubles);
		GMatrix<Double> gMatrix = ConvertMatrix.toGeneric(dMatrix);
		
		assertEquals(dMatrix.height(), gMatrix.height());
		assertEquals(dMatrix.width(), gMatrix.width());
		
		for (int i = 0; i < dMatrix.height(); i++)
		{
			for (int j = 0; j < dMatrix.width(); j++)
				assertEquals(dMatrix.get(i, j), gMatrix.get(i, j));
		}
	}
	
	@Test
	void genericToComparable()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		GMatrix<Double> gMatrix = new GMatrix<Double>(doubles);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(gMatrix);
		
		assertEquals(gMatrix.height(), cMatrix.height());
		assertEquals(gMatrix.width(), cMatrix.width());
		
		for (int i = 0; i < gMatrix.height(); i++)
		{
			for (int j = 0; j < gMatrix.width(); j++)
				assertEquals(gMatrix.get(i, j), cMatrix.get(i, j));
		}
	}
	
	@Test
	void doubleToComparable()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		DMatrix dMatrix = new DMatrix(doubles);
		CMatrix<Double> cMatrix = ConvertMatrix.toComparable(dMatrix);
		
		assertEquals(dMatrix.height(), cMatrix.height());
		assertEquals(dMatrix.width(), cMatrix.width());
		
		for (int i = 0; i < dMatrix.height(); i++)
		{
			for (int j = 0; j < dMatrix.width(); j++)
				assertEquals(dMatrix.get(i, j), cMatrix.get(i, j));
		}
	}
	
	@Test
	void genericToDouble()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		GMatrix<Double> gMatrix = new GMatrix<Double>(doubles);
		DMatrix dMatrix = ConvertMatrix.toDouble(gMatrix);
		
		assertEquals(gMatrix.height(), dMatrix.height());
		assertEquals(gMatrix.width(), dMatrix.width());
		
		for (int i = 0; i < gMatrix.height(); i++)
		{
			for (int j = 0; j < gMatrix.width(); j++)
				assertEquals(gMatrix.get(i, j), dMatrix.get(i, j));
		}
	}
	
	@Test
	void comparableToDouble()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		CMatrix<Double> cMatrix = new CMatrix<Double>(doubles);
		DMatrix dMatrix = ConvertMatrix.toDouble(cMatrix);
		
		assertEquals(cMatrix.height(), dMatrix.height());
		assertEquals(cMatrix.width(), dMatrix.width());
		
		for (int i = 0; i < cMatrix.height(); i++)
		{
			for (int j = 0; j < cMatrix.width(); j++)
				assertEquals(cMatrix.get(i, j), dMatrix.get(i, j));
		}
	}
	
	@Test
	void stringToDouble()
	{
		String[][] strings = {
				{"4.0", "7.0", "3.0", "5.0"},
				{"2.0", "1.0", "4.0", "4.0"},
				{"1.0", "3.0", "6.0", "4.0"}
		};
		
		CMatrix<String> sMatrix = new CMatrix<String>(strings);
		DMatrix dMatrix = ConvertMatrix.stringToDouble(sMatrix);
		
		assertEquals(sMatrix.height(), dMatrix.height());
		assertEquals(sMatrix.width(), dMatrix.width());
		
		for (int i = 0; i < sMatrix.height(); i++)
		{
			for (int j = 0; j < sMatrix.width(); j++)
				assertEquals(sMatrix.get(i, j), "" + dMatrix.get(i, j));
		}
	}
	
	@Test
	void doubleToString()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		DMatrix dMatrix = new DMatrix(doubles);
		CMatrix<String> sMatrix = ConvertMatrix.doubleToString(dMatrix);
		
		assertEquals(dMatrix.height(), sMatrix.height());
		assertEquals(dMatrix.width(), sMatrix.width());
		
		for (int i = 0; i < dMatrix.height(); i++)
		{
			for (int j = 0; j < dMatrix.width(); j++)
				assertEquals("" + dMatrix.get(i, j), sMatrix.get(i, j));
		}
	}
}
