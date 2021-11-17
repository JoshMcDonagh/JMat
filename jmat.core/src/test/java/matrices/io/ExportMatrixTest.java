package test.java.matrices.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	
	private CMatrix<String> importMatrixAsXml(String fileName)
	{
		CMatrix<String> importedMatrix = new CMatrix<String>();
		
		try 
		{
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName));
			NodeList rowList = document.getElementsByTagName("ROW");
			
			for (int i = 0; i < rowList.getLength(); i++)
			{
				Node rowNode = rowList.item(i);
				
				if (rowNode.getNodeType() != Node.ELEMENT_NODE)
					continue;
				
				NodeList colList = rowNode.getChildNodes();
				String[] values = new String[colList.getLength()];
				
				for (int j = 0; j < colList.getLength(); j++)
					values[j] = colList.item(j).getFirstChild().getNodeValue();
				
				importedMatrix.addRow(values);
			}
		}
		catch (SAXException | IOException | ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		
		return importedMatrix;
	}
	
	@Test
	void GMatrixAsFile() 
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "GMatrixAsFileTest.txt";
		String delimiter = ";";
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asFile(fileName, delimiter, matrix);
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
	void CMatrixAsFile()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "CMatrixAsFileTest.txt";
		String delimiter = ";";
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asFile(fileName, delimiter, matrix);
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
	void DMatrixAsFile()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "DMatrixAsFileTest.txt";
		String delimiter = ";";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try 
		{
			ExportMatrix.asFile(fileName, delimiter, matrix);
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
	
	@Test
	void GMatrixAsXml()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "GMatrixAsXmlTest.csv";
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asXml(fileName, matrix);
			CMatrix<String> importedMatrix = importMatrixAsXml(fileName);
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
	void CMatrixAsXml()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "CMatrixAsXmlTest.xml";
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		
		try 
		{
			ExportMatrix.asXml(fileName, matrix);
			CMatrix<String> importedMatrix = importMatrixAsXml(fileName);
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
	void DMatrixAsXml()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "DMatrixAsXmlTest.xml";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try 
		{
			ExportMatrix.asXml(fileName, matrix);
			CMatrix<String> importedMatrix = importMatrixAsXml(fileName);
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
