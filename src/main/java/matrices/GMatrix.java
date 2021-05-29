package main.java.matrices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import main.java.matrices.mapFunction.MapFunction;

public class GMatrix<T> implements Iterable<T>
{
	private ArrayList<ArrayList<T>> matrixData;
	private final T emptyVal;
	
	public GMatrix()
	{
		emptyVal = null;
		clear();
	}
	
	public GMatrix(int height, int width)
	{
		emptyVal = null;
		clear(height, width);
	}
	
	public GMatrix(T[][] values)
	{
		emptyVal = null;
		clear();
		convertArrayToMatrix(values);
	}
	
	GMatrix(T newEmptyVal)
	{
		emptyVal = newEmptyVal;
		clear();
	}
	
	GMatrix(T newEmptyVal, int height, int width)
	{
		emptyVal = newEmptyVal;
		clear(height, width);
	}
	
	GMatrix(T newEmptyVal, T[][] values)
	{
		emptyVal = newEmptyVal;
		clear();
		convertArrayToMatrix(values);
	}
	
	GMatrix(ArrayList<ArrayList<T>> newMatrixData)
	{
		emptyVal = null;
		matrixData = newMatrixData;
	}
	
	GMatrix(T newEmptyVal, ArrayList<ArrayList<T>> newMatrixData)
	{
		emptyVal = newEmptyVal;
		matrixData = newMatrixData;
	}
	
	private void convertArrayToMatrix(T[][] values)
	{
		for (int i = 0; i < values.length; i++)
			matrixData.add(new ArrayList<>(Arrays.asList(values[i])));
		normalise();
	}
	
	public void clear()
	{
		matrixData = new ArrayList<ArrayList<T>>();
	}
	
	public void clear(int height, int width)
	{
		clear();
		for (int i = 0; i < height; i++)
		{
			matrixData.add(new ArrayList<T>());
			for (int j = 0; j < width; j++)
				matrixData.get(i).add(emptyVal);
		}
	}
	
	public T get(int row, int column)
	{
		return matrixData.get(row).get(column);
	}
	
	public void set(int row, int column, T value)
	{
		matrixData.get(row).set(column, value);
	}
	
	public GMatrix<T> getRows(int start, int end)
	{
		GMatrix<T> newMatrix = new GMatrix<T>();
		
		int count = end - start;
		for (int i = 0; i < count; i++)
			newMatrix.addRow(matrixData.get(start + i));
		
		return newMatrix;
	}
	
	public GMatrix<T> getColumns(int start, int end)
	{
		return transpose().getRows(start, end).transpose();
	}
	
	private void addRow(ArrayList<T> values)
	{
		matrixData.add(values);
		normalise();
	}
	
	public void addRow(@SuppressWarnings("unchecked") T... values)
	{
		matrixData.add(new ArrayList<>(Arrays.asList(values)));
		normalise();
	}
	
	public void addColumn(@SuppressWarnings("unchecked") T... values)
	{
		GMatrix<T> transposed = transpose();
		transposed.matrixData.add(new ArrayList<>(Arrays.asList(values)));
		matrixData = transposed.transpose().matrixData;
		normalise();
	}
	
	public void deleteRows(int start, int end)
	{
		int count = end - start;
		for (int i = 0; i < count; i++)
			matrixData.remove(start);
	}
	
	public void deleteColumns(int start, int end)
	{
		GMatrix<T> transposed = transpose();
		transposed.deleteRows(start, end);
		matrixData = transposed.transpose().matrixData;
	}
	
	public int width()
	{
		if (matrixData.size() == 0)
			return 0;
		
		return matrixData.get(0).size();
	}
	
	public int height()
	{
		return matrixData.size();
	}
	
	public boolean isEmpty()
	{
		return height() == 0;
	}
	
	public boolean isSquare()
	{
		return height() == width();
	}
	
	public GMatrix<T> transpose()
	{
		ArrayList<ArrayList<T>> newMatrixData = new ArrayList<ArrayList<T>>();
		
		for (int i = 0; i < width(); i++)
		{
			newMatrixData.add(new ArrayList<T>());
			for (int j = 0; j < height(); j++)
				newMatrixData.get(i).add(matrixData.get(j).get(i));
		}
		
		return new GMatrix<T>(newMatrixData);
	}
	
	@SuppressWarnings("unchecked")
	public T[][] toArray()
	{
		Object[][] array = new Object[matrixData.size()][];
		
		for (int i = 0; i < matrixData.size(); i++)
			array[i] = (matrixData.get(i).toArray());
		
		return (T[][]) array;
	}
	
	@SuppressWarnings("unchecked")
	public void map(MapFunction mapFunction)
	{
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
			{
				T matVal = matrixData.get(i).get(j);
				matrixData.get(i).set(j, (T) mapFunction.eval(matVal));
			}
		}
	}
	
	public GMatrix<T> clone()
	{
		GMatrix<T> newMatrix = new GMatrix<T>(height(),width());
		
		for (int i = 0; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
				newMatrix.set(i, j, matrixData.get(i).get(j));
		}
		
		return newMatrix;
	}
	
	public void show()
	{
		ArrayList<ArrayList<String>> stringMatrixData = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> colLengths = new ArrayList<Integer>();
		final String delimiter = "   ";
		
		for (int i = 0; i < height(); i++)
		{
			stringMatrixData.add(new ArrayList<String>());
			
			for (int j = 0; j < width(); j++)
			{
				if (matrixData.get(i).get(j) == null)
					stringMatrixData.get(i).add("NULL");
				else				
					stringMatrixData.get(i).add(matrixData.get(i).get(j).toString());
			}
		}
		
		for (int i = 0; i < width(); i++)
		{
			int maxLength = 0;
			
			for (int j = 0; j < height(); j++)
			{
				int length = stringMatrixData.get(j).get(i).length();
				if (length > maxLength)
					maxLength = length;
			}
			
			colLengths.add(maxLength);
		}
		
		for (int i = 0; i < height(); i++)
		{
			String printLn = "";
			for (int j = 0; j < width(); j++)
			{
				String strVal = stringMatrixData.get(i).get(j);
				printLn += strVal + new String(new char[colLengths.get(j) - strVal.length()]).replace("\0", " ") + delimiter;
			}
			System.out.println(printLn);
		}
	}
	
	private void normalise()
	{
		int finalWidth = 0;
		
		for (int i = 0; i < matrixData.size(); i++)
		{
			int currentWidth = matrixData.get(i).size();
			if (currentWidth > finalWidth)
				finalWidth = currentWidth;
		}
		
		for (int i = 0; i < matrixData.size(); i++)
		{
			ArrayList<T> currentRow = matrixData.get(i);
			while (finalWidth > currentRow.size())
				currentRow.add(emptyVal);
		}
	}

	public Iterator<T> iterator() 
	{
		return new MatrixIterator<T>(this);
	}
}

class MatrixIterator<T> implements Iterator<T>
{
	private GMatrix<T> matrix;
	int currentRow = 0;
	int currentCol = 0;
	
	MatrixIterator(GMatrix<T> matrix)
	{
		this.matrix = matrix;
	}
	
	public boolean hasNext() 
	{
		if (currentCol == matrix.width())
		{
			currentRow++;
			currentCol = 0;
		}
		
		if (currentRow == matrix.height())
			return false;
		
		return true;
	}

	public T next() 
	{
		T value = matrix.get(currentRow, currentCol);
		currentCol++;
		
		return value;
	}
}
