package test.java.matrices.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;
import main.java.matrices.io.ExportMatrix;

/**
 * 
 * @author Joshua McDonagh
 *
 */
class ExportMatrixTest 
{
	private CMatrix<String> importMatrix(String fileName, String delimiter) throws FileNotFoundException
	{
		CMatrix<String> importedMatrix = new CMatrix<String>();
		Scanner reader = new Scanner(new File(fileName));
		while (reader.hasNextLine())
		{
			String rowData = reader.nextLine();
			importedMatrix.addRow(rowData.split(delimiter));
		}
		reader.close();
		return importedMatrix;
	}
	
	@Test
	void GMatrixAsTxt() 
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "GMatrixAsTxtTest.txt";
		String delimiter = ";";
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asTxt(fileName, delimiter, matrix);
			CMatrix<String> importedMatrix = importMatrix(fileName, delimiter);
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
			fail("FileNotFoundException occured");
		}
	}
	
	@Test
	void CMatrixAsTxt()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "CMatrixAsTxtTest.txt";
		String delimiter = ";";
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asTxt(fileName, delimiter, matrix);
			CMatrix<String> importedMatrix = importMatrix(fileName, delimiter);
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
			fail("FileNotFoundException occured");
		}
	}
	
	@Test
	void DMatrixAsTxt()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "DMatrixAsTxtTest.txt";
		String delimiter = ";";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try 
		{
			ExportMatrix.asTxt(fileName, delimiter, matrix);
			CMatrix<String> importedMatrix = importMatrix(fileName, delimiter);
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
			fail("FileNotFoundException occured");
		}
	}
	
	@Test
	void GMatrixAsCsv() 
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "GMatrixAsCsvTest.csv";
		String delimiter = ",";
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asCsv(fileName, matrix);
			CMatrix<String> importedMatrix = importMatrix(fileName, delimiter);
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
			fail("FileNotFoundException occured");
		}
	}
	
	@Test
	void CMatrixAsCsv()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "CMatrixAsCsvTest.csv";
		String delimiter = ",";
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asCsv(fileName, matrix);
			CMatrix<String> importedMatrix = importMatrix(fileName, delimiter);
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
			fail("FileNotFoundException occured");
		}
	}
	
	@Test
	void DMatrixAsCsv()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "DMatrixAsCsvTest.csv";
		String delimiter = ",";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try 
		{
			ExportMatrix.asCsv(fileName, matrix);
			CMatrix<String> importedMatrix = importMatrix(fileName, delimiter);
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
			fail("FileNotFoundException occured");
		}
	}
}
