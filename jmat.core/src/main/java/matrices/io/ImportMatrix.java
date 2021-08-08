package main.java.matrices.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.matrixConversion.ConvertMatrix;

/**
 * Interface which is used to access static methods that control the importing of matrices from external files.
 * 
 * @author Joshua McDonagh
 *
 */
public interface ImportMatrix 
{
	/**
	 * Imports data from a text file and generates a string matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @param delimiter The string which separates each row element in the imported text file
	 * @return The imported string matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static CMatrix<String> fromTxtAsString(String fileName, String delimiter) throws FileNotFoundException
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
	 * Imports data from a text file and generates a double matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @param delimiter The string which separates each row element in the imported text file
	 * @return The imported double matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static DMatrix fromTxtAsDouble(String fileName, String delimiter) throws FileNotFoundException
	{
		return ConvertMatrix.stringToDouble(fromTxtAsString(fileName, delimiter));
	}
	
	/**
	 * Imports data from a CSV file and generates a string matrix from it.
	 * @param fileName The filename of the file to import the matrix from
	 * @return The imported string matrix
	 * @throws FileNotFoundException Exception thrown if the file importing the matrix from is not found
	 */
	public static CMatrix<String> fromCsvAsString(String fileName) throws FileNotFoundException
	{
		return fromTxtAsString(fileName, ",");
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
