package test.java.matrices.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;
import main.java.matrices.io.ImportMatrix;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class ImportMatrixTest 
{
	private <T> void exportMatrix(String fileName, String delimiter, GMatrix<T> matrix) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File(fileName));
		StringBuilder txtContent = new StringBuilder();
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
			{
				txtContent.append(matrix.get(i, j));
				if (j < matrix.width() - 1)
					txtContent.append(delimiter);
			}
			txtContent.append("\n");
		}
		
		writer.write(txtContent.toString());
		writer.close();
	}
	
	@Test
	void fromTxtAsString() 
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "FromTxtAsStringTest.txt";
		String delimiter = ";";
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		try 
		{
			exportMatrix(fileName, delimiter, matrix);
			CMatrix<String> importedMatrix = ImportMatrix.fromTxtAsString(fileName, delimiter);
			new File(fileName).delete();
			
			assertEquals(matrix.height(), importedMatrix.height());
			assertEquals(matrix.width(), importedMatrix.width());
			
			for (int i = 0; i < matrix.height(); i++)
			{
				for (int j = 0; j < matrix.width(); j++)
					assertEquals(String.valueOf(matrix.get(i, j)), importedMatrix.get(i, j));
			}
		} 
		catch (FileNotFoundException e) 
		{
			fail(e.toString());
		}
	}
	
	@Test
	void fromTxtAsDouble()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "FromTxtAsDoubleTest.txt";
		String delimiter = ";";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try 
		{
			exportMatrix(fileName, delimiter, matrix);
			DMatrix importedMatrix = ImportMatrix.fromTxtAsDouble(fileName, delimiter);
			new File(fileName).delete();
			
			assertEquals(matrix.height(), importedMatrix.height());
			assertEquals(matrix.width(), importedMatrix.width());
			
			for (int i = 0; i < matrix.height(); i++)
			{
				for (int j = 0; j < matrix.width(); j++)
					assertEquals(matrix.get(i, j), importedMatrix.get(i, j));
			}
		} 
		catch (FileNotFoundException e) 
		{
			fail("FileNotFoundException occured");
		}
	}
	
	@Test
	void fromCsvAsString()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "FromCsvAsStringTest.csv";
		String delimiter = ",";
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		try 
		{
			exportMatrix(fileName, delimiter, matrix);
			CMatrix<String> importedMatrix = ImportMatrix.fromCsvAsString(fileName);
			new File(fileName).delete();
			
			assertEquals(matrix.height(), importedMatrix.height());
			assertEquals(matrix.width(), importedMatrix.width());
			
			for (int i = 0; i < matrix.height(); i++)
			{
				for (int j = 0; j < matrix.width(); j++)
					assertEquals(String.valueOf(matrix.get(i, j)), importedMatrix.get(i, j));
			}
		} 
		catch (FileNotFoundException e) 
		{
			fail(e.toString());
		}
	}
	
	@Test
	void fromCsvAsDouble()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "FromCsvAsDoubleTest.csv";
		String delimiter = ",";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try 
		{
			exportMatrix(fileName, delimiter, matrix);
			DMatrix importedMatrix = ImportMatrix.fromCsvAsDouble(fileName);
			new File(fileName).delete();
			
			assertEquals(matrix.height(), importedMatrix.height());
			assertEquals(matrix.width(), importedMatrix.width());
			
			for (int i = 0; i < matrix.height(); i++)
			{
				for (int j = 0; j < matrix.width(); j++)
					assertEquals(matrix.get(i, j), importedMatrix.get(i, j));
			}
		} 
		catch (FileNotFoundException e) 
		{
			fail("FileNotFoundException occured");
		}
	}
}
