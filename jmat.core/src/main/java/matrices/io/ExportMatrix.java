package main.java.matrices.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.java.matrices.GMatrix;

/**
 * Interface which is used to access static methods that control the exporting of matrices to external files.
 * 
 * @author Joshua McDonagh
 *
 */
public interface ExportMatrix 
{
	/**
	 * Exports a given generic matrix as a file.
	 * @param <T> The value type of which is contained by the matrix
	 * @param fileName The filename of the file to export the matrix to
	 * @param delimiter The string which separates each row element in the exported file
	 * @param matrix The generic matrix to export
	 * @throws FileNotFoundException Exception thrown if the file exporting the matrix to is not found
	 */
	public static <T> void asFile(String fileName, String delimiter, GMatrix<T> matrix) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File(fileName));
		StringBuilder fileContent = new StringBuilder();
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
			{
				fileContent.append(matrix.get(i, j));
				if (j < matrix.width() - 1)
					fileContent.append(delimiter);
			}
			fileContent.append("\n");
		}
		
		writer.write(fileContent.toString());
		writer.close();
	}
	
	/**
	 * Exports a given generic matrix as a CSV file.
	 * @param <T> The value type of which is contained by the matrix
	 * @param fileName The filename of the file to export the matrix to
	 * @param matrix The generic matrix to export
	 * @throws FileNotFoundException Exception thrown if the file exporting the matrix to is not found
	 */
	public static <T> void asCsv(String fileName, GMatrix<T> matrix) throws FileNotFoundException
	{
		asFile(fileName, ",", matrix);
	}
	
	/**
	 * Checks if a given file is accessible, if not, a FileNotFoundException is thrown.
	 * @param fileName The filename of the file to check
	 * @throws FileNotFoundException Exception thrown if the file is inaccessible
	 */
	private static void checkIfFileIsAccessible(String fileName) throws FileNotFoundException
	{
		new PrintWriter(new File(fileName)).close();
	}
	
	/**
	 * Exports a given generic matrix as an XML file.
	 * @param <T> The value type of which is contained by the matrix
	 * @param fileName The filename of the file to export the matrix to
	 * @param matrix The generic matrix to export
	 * @throws FileNotFoundException Exception thrown if the file exporting the matrix to is not found
	 */
	public static <T> void asXml(String fileName, GMatrix<T> matrix) throws FileNotFoundException
	{
		checkIfFileIsAccessible(fileName);
		
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
}
