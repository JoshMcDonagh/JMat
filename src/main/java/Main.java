package main.java;

import main.java.matrices.CMatrix;
import main.java.matrices.DMatrix;
import main.java.matrices.discretisation.Discretise;
import main.java.matrices.utilities.MatrixUtilities;

public class Main 
{
	public static void main(String[] args) 
	{
		DMatrix matrix1 = new DMatrix(100, 3);
		matrix1.intRandomise(1, 100);
		matrix1 = MatrixUtilities.sort(0, matrix1, true);
		matrix1.show();
		CMatrix<String> matrix2 = Discretise.byFrequency(matrix1, 50);
		matrix2.show();
	}
}