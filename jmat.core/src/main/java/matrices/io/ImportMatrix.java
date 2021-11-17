package main.java.matrices.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.java.matrices.CMatrix;
import main.java.matrices.ConvertMatrix;
import main.java.matrices.DMatrix;

/**
 * Interface which is used to access static methods that control the importing of matrices from external files.
 * 
 * @author Joshua McDonagh
 *
 */
public interface ImportMatrix 
{
	/**
	 * Imports data from a file and generates a string matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @param delimiter The string which separates each row element in the imported file
	 * @return The imported string matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static CMatrix<String> fromFileAsString(String fileName, String delimiter) throws FileNotFoundException
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
	
	/**
	 * Imports data from a file and generates a double matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @param delimiter The string which separates each row element in the imported file
	 * @return The imported double matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static DMatrix fromFileAsDouble(String fileName, String delimiter) throws FileNotFoundException
	{
		return ConvertMatrix.stringToDouble(fromFileAsString(fileName, delimiter));
	}
	
	/**
	 * Imports data from a CSV file and generates a string matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @return The imported string matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static CMatrix<String> fromCsvAsString(String fileName) throws FileNotFoundException
	{
		return fromFileAsString(fileName, ",");
	}
	
	/**
	 * Imports data from a CSV file and generates a double matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @return The imported double matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static DMatrix fromCsvAsDouble(String fileName) throws FileNotFoundException
	{
		return ConvertMatrix.stringToDouble(fromCsvAsString(fileName));
	}
	
	/**
	 * Checks if a given file is accessible, if not, a FileNotFoundException is thrown.
	 * @param fileName The filename of the file to check
	 * @throws FileNotFoundException Exception thrown if the file is inaccessible
	 */
	private static void checkIfFileIsAccessible(String fileName) throws FileNotFoundException
	{
		new Scanner(new File(fileName)).close();
	}
	
	/**
	 * Imports data from an XML file and generates a string matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @return The imported string matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static CMatrix<String> fromXmlAsString(String fileName) throws FileNotFoundException
	{
		checkIfFileIsAccessible(fileName);
		
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
	
	/**
	 * Imports data from a XML file and generates a double matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @return The imported double matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static DMatrix fromXmlAsDouble(String fileName) throws FileNotFoundException
	{
		return ConvertMatrix.stringToDouble(fromXmlAsString(fileName));
	}
}
