package main.java.matrices.mapFunction;

/**
 * Used by the GMatrix class as a functional interface for the map method which maps a function across all values of the matrix.
 * 
 * @author Joshua McDonagh
 *
 */
public interface MapFunction 
{
	/**
	 * Functional method which is called and applied to all values of a matrix.
	 * @param matVal The matrix value to be manipulated
	 * @return The manipulated matrix value to be stored in the matrix
	 */
	public Object eval (Object matVal);
}