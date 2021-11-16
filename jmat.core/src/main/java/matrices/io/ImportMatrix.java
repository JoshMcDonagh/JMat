package main.java.matrices.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
}
