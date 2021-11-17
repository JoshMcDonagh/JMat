package test.java.matrices.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	
	private <T> void exportMatrixAsXml(String fileName, GMatrix<T> matrix)
	{
		try 
		{
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element rootElement = document.createElement("MATRIX");
			document.appendChild(rootElement);
			
			for (int i = 0; i < matrix.height(); i++)
			{
				Element rowElement = document.createElement("ROW");
				for (int j = 0; j < matrix.width(); j++)
				{
					Element colElement = document.createElement("COL");
					StringBuilder xmlContent = new StringBuilder();
					xmlContent.append(matrix.get(i, j));
					colElement.appendChild(document.createTextNode(xmlContent.toString()));
					rowElement.appendChild(colElement);
				}
				rootElement.appendChild(rowElement);
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);
		} 
		
		catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void fromFileAsString() 
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "FromFileAsStringTest.txt";
		String delimiter = ";";
		
		GMatrix<Integer> matrix = new GMatrix<Integer>(integers);
		
		try 
		{
			exportMatrix(fileName, delimiter, matrix);
			CMatrix<String> importedMatrix = ImportMatrix.fromFileAsString(fileName, delimiter);
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
	void fromFileAsDouble()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "FromFileAsDoubleTest.txt";
		String delimiter = ";";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try 
		{
			exportMatrix(fileName, delimiter, matrix);
			DMatrix importedMatrix = ImportMatrix.fromFileAsDouble(fileName, delimiter);
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
	
	@Test
	void fromXmlAsString()
	{
		Integer[][] integers = {
				{4, 7, 3, 5},
				{2, 1, 4, 4},
				{1, 3, 6, 4}
		};
		
		String fileName = "FromXmlAsStringTest.xml";
		
		CMatrix<Integer> matrix = new CMatrix<Integer>(integers);
		
		try
		{
			exportMatrixAsXml(fileName, matrix);
			CMatrix<String> importedMatrix = ImportMatrix.fromXmlAsString(fileName);
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
	void fromXmlAsDouble()
	{
		Double[][] doubles = {
				{4.0, 7.0, 3.0, 5.0},
				{2.0, 1.0, 4.0, 4.0},
				{1.0, 3.0, 6.0, 4.0}
		};
		
		String fileName = "FromXmlAsDoubleTest.xml";
		
		DMatrix matrix = new DMatrix(doubles);
		
		try
		{
			exportMatrixAsXml(fileName, matrix);
			DMatrix importedMatrix = ImportMatrix.fromXmlAsDouble(fileName);
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
