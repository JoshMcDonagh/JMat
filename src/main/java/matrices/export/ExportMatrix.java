package main.java.matrices.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;
import main.java.matrices.utilities.MatrixUtilities;

public class ExportMatrix 
{
	public static <T> void asTxt(String fileName, String delimiter, GMatrix<T> matrix) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File(fileName));
		StringBuilder csvContent = new StringBuilder();
		
		for (int i = 0; i < matrix.height(); i++)
		{
			for (int j = 0; j < matrix.width(); j++)
			{
				csvContent.append(matrix.get(i, j));
				if (j < matrix.width() - 1)
					csvContent.append(delimiter);
			}
			csvContent.append("\n");
		}
		
		writer.write(csvContent.toString());
	}
	
	public static <T extends Comparable<T>> void asTxt(String fileName, String delimiter, CMatrix<T> matrix) throws FileNotFoundException
	{
		asTxt(fileName, delimiter, MatrixUtilities.comparableToGeneric(matrix));
	}
	
	public static void asTxt(String fileName, String delimiter, DMatrix matrix) throws FileNotFoundException
	{
		asTxt(fileName, delimiter, MatrixUtilities.doubleToComparable(matrix));
	}
	
	public static <T> void asCSV(String fileName, GMatrix<T> matrix) throws FileNotFoundException
	{
		asTxt(fileName, ",", matrix);
	}
	
	public static <T extends Comparable<T>> void asCSV(String fileName, CMatrix<T> matrix) throws FileNotFoundException
	{
		asCSV(fileName, MatrixUtilities.comparableToGeneric(matrix));
	}
	
	public static void asCSV(String fileName, DMatrix matrix) throws FileNotFoundException
	{
		asCSV(fileName, MatrixUtilities.doubleToComparable(matrix));
	}
}
