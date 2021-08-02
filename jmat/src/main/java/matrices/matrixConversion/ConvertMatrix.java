package main.java.matrices.matrixConversion;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.GMatrix;

/**
 * Interface which is used to access static methods that provide functionality to convert matrices between matrix types.
 * 
 * @author Joshua McDonagh
 *
 */
public interface ConvertMatrix 
{	
	/**
	 * Converts a comparable matrix into a generic matrix.
	 * @param <T> Type of data that the given matrix contains
	 * @param comparable Comparable matrix to be converted
	 * @return Generic matrix produced by converting from the comparable matrix
	 */
	public static <T extends Comparable<T>> GMatrix<T> toGeneric(CMatrix<T> comparable)
	{
		GMatrix<T> generic = new GMatrix<T>(comparable.height(), comparable.width());
		
		for (int i = 0; i < comparable.height(); i++)
		{
			for (int j = 0; j < comparable.width(); j++)
				generic.set(i, j, comparable.get(i, j));
		}
		
		return generic;
	}
	
	/**
	 * Converts a double matrix into a generic matrix.
	 * @param dMatrix Double matrix to be converted
	 * @return Generic matrix produced by converting from the double matrix
	 */
	public static GMatrix<Double> toGeneric(DMatrix dMatrix)
	{
		GMatrix<Double> generic = new GMatrix<Double>(dMatrix.height(), dMatrix.width());
		
		for (int i = 0; i < dMatrix.height(); i++)
		{
			for (int j = 0; j < dMatrix.width(); j++)
				generic.set(i, j, dMatrix.get(i, j));
		}
		
		return generic;
	}
	
	/**
	 * Converts a generic matrix into a comparable matrix.
	 * @param <T> Type of data that the given matrix contains
	 * @param generic Generic matrix to be converted
	 * @return Comparable matrix produced by converting from the generic matrix
	 */
	public static <T extends Comparable<T>> CMatrix<T> toComparable(GMatrix<T> generic)
	{
		CMatrix<T> comparable = new CMatrix<T>(generic.height(), generic.width());
		
		for (int i = 0; i < generic.height(); i++)
		{ 
			for (int j = 0; j < generic.width(); j++)
				comparable.set(i, j, generic.get(i,  j));
		}
		
		return comparable;
	}
	
	/**
	 * Converts a double matrix into a comparable matrix.
	 * @param dMatrix Double matrix to be converted
	 * @return Comparable matrix produced by converting from the double matrix
	 */
	public static CMatrix<Double> toComparable(DMatrix dMatrix)
	{
		CMatrix<Double> comparable = new CMatrix<Double>(dMatrix.height(), dMatrix.width());
		
		for (int i = 0; i < dMatrix.height(); i++)
		{
			for (int j = 0; j < dMatrix.width(); j++)
				comparable.set(i, j, dMatrix.get(i, j));
		}
		
		return comparable;
	}
	
	/**
	 * Converts a generic matrix into a double matrix.
	 * @param generic Generic matrix to be converted
	 * @return Double matrix produced by converting from the generic matrix
	 */
	public static DMatrix toDouble(GMatrix<Double> generic)
	{
		DMatrix dMatrix = new DMatrix(generic.height(), generic.width());
		
		for (int i = 0; i < generic.height(); i++)
		{
			for (int j = 0; j < generic.width(); j++)
				dMatrix.set(i, j, generic.get(i, j));
		}
		
		return dMatrix;
	}
	
	/**
	 * Converts a comparable matrix into a double matrix
	 * @param comparable Comparable matrix to be converted
	 * @return Double matrix produced by converting from the comparable matrix
	 */
	public static DMatrix toDouble(CMatrix<Double> comparable)
	{
		DMatrix dMatrix = new DMatrix(comparable.height(), comparable.width());
		
		for (int i = 0; i < comparable.height(); i++)
		{
			for (int j = 0; j < comparable.width(); j++)
				dMatrix.set(i, j, comparable.get(i, j));
		}
		
		return dMatrix;
	}
	
	/**
	 * Converts a string matrix into a double matrix
	 * @param string String matrix to be converted
	 * @return Double matrix produced by converting from the string matrix
	 */
	public static DMatrix stringToDouble(CMatrix<String> string)
	{
		DMatrix dMatrix = new DMatrix(string.height(), string.width());
		
		for (int i = 0; i < string.height(); i++)
		{
			for (int j = 0; j < string.width(); j++)
				dMatrix.set(i, j, Double.parseDouble(string.get(i, j)));
		}
		
		return dMatrix;
	}
	
	/**
	 * Converts a double matrix into a string matrix
	 * @param dMatrix Double matrix to be converted
	 * @return String matrix produced by converting from the double matrix
	 */
	public static CMatrix<String> doubleToString(DMatrix dMatrix)
	{
		CMatrix<String> string = new CMatrix<String>(dMatrix.height(), dMatrix.width());
		
		for (int i = 0; i < dMatrix.height(); i++)
		{
			for (int j = 0; j < dMatrix.width(); j++)
				string.set(i,  j, String.valueOf(dMatrix.get(i, j)));
		}
		
		return string;
	}
}
