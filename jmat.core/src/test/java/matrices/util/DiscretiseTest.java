package test.java.matrices.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.util.Discretise;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class DiscretiseTest 
{
	@Test
	void byFrequency()
	{
		Double[][] doubles = {
				{14.0, 90.0},
				{18.0, 40.0},
				{30.0, 20.0},
				{41.0, 60.0},
				{57.0, 30.0},
				{64.0, 70.0},
				{79.0, 100.0},
				{82.0, 10.0},
				{92.0, 80.0},
				{94.0, 50.0}
		};
		
		String[][] strings = {
				{"0", "4"},
				{"0", "1"},
				{"1", "0"},
				{"1", "2"},
				{"2", "1"},
				{"2", "3"},
				{"3", "4"},
				{"3", "0"},
				{"4", "3"},
				{"4", "2"}
		};
		
		int numOfBins = 5;
		
		DMatrix matrix = new DMatrix(doubles);
		CMatrix<String> discMatrix = Discretise.byFrequency(matrix, numOfBins);
		
		assertEquals(discMatrix.height(), strings.length);
		assertEquals(discMatrix.width(), strings[0].length);
		
		for (int i = 0; i < discMatrix.height(); i++)
		{
			for (int j = 0; j < discMatrix.width(); j++)
				assertEquals(discMatrix.get(i, j), strings[i][j]);
		}
	}
	
	@Test
	void byWidth() 
	{
		Double[][] doubles = {
				{1.0, 9.0},
				{2.0, 4.0},
				{3.0, 2.0},
				{4.0, 6.0},
				{5.0, 3.0},
				{6.0, 7.0},
				{7.0, 10.0},
				{8.0, 1.0},
				{8.0, 8.0},
				{10.0, 7.0}
		};
		
		String[][] strings = {
				{"0", "4"},
				{"0", "1"},
				{"1", "0"},
				{"1", "2"},
				{"2", "1"},
				{"2", "3"},
				{"3", "4"},
				{"3", "0"},
				{"3", "3"},
				{"4", "3"}
		};
		
		int numOfBins = 5;
		
		DMatrix matrix = new DMatrix(doubles);
		CMatrix<String> discMatrix = Discretise.byWidth(matrix, numOfBins);
		
		assertEquals(discMatrix.height(), strings.length);
		assertEquals(discMatrix.width(), strings[0].length);
		
		for (int i = 0; i < discMatrix.height(); i++)
		{
			for (int j = 0; j < discMatrix.width(); j++)
				assertEquals(discMatrix.get(i, j), strings[i][j]);
		}
	}
}
