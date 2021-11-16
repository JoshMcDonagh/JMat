package main.java.matrices.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import main.java.matrices.CMatrix;
import main.java.matrices.ConvertMatrix;
import main.java.matrices.DMatrix;
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
	
	/**
	 * Exports a given comparable matrix as a file.
	 * @param <T> The value type of which is contained by the matrix
	 * @param fileName The filename of the file to export the matrix to
	 * @param delimiter The string which separates each row element in the exported file
	 * @param matrix The comparable matrix to export
	 * @throws FileNotFoundException Exception thrown if the file exporting the matrix to is not found
	 */
	public static <T extends Comparable<T>> void asFile(String fileName, String delimiter, CMatrix<T> matrix) throws FileNotFoundException
	{
		asFile(fileName, delimiter, ConvertMatrix.toGeneric(matrix));
	}
	
	/**
	 * Exports a given double matrix as a file.
	 * @param fileName The filename of the file to export the matrix to
	 * @param delimiter The string which separates each row element in the exported file
	 * @param matrix The double matrix to export
	 * @throws FileNotFoundException Exception thrown if the file exporting the matrix to is not found
	 */
	public static void asFile(String fileName, String delimiter, DMatrix matrix) throws FileNotFoundException
	{
		asFile(fileName, delimiter, ConvertMatrix.toGeneric(matrix));
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
	 * Exports a given comparable matrix as a CSV file.
	 * @param <T> The value type of which is contained by the matrix
	 * @param fileName The filename of the file to export the matrix to
	 * @param matrix The comparable matrix to export
	 * @throws FileNotFoundException Exception thrown if the file exporting the matrix to is not found
	 */
	public static <T extends Comparable<T>> void asCsv(String fileName, CMatrix<T> matrix) throws FileNotFoundException
	{
		asCsv(fileName, ConvertMatrix.toGeneric(matrix));
	}
	
	/**
	 * Exports a given double matrix as a CSV file.
	 * @param fileName The filename of the file to export the matrix to
	 * @param matrix The double matrix to export
	 * @throws FileNotFoundException Exception thrown if the file exporting the matrix to is not found
	 */
	public static void asCsv(String fileName, DMatrix matrix) throws FileNotFoundException
	{
		asCsv(fileName, ConvertMatrix.toGeneric(matrix));
	}
}
