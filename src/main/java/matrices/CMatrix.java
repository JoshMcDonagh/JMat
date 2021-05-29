package main.java.matrices;

import java.util.ArrayList;

import main.java.matrices.utilities.MatrixUtilities;

public class CMatrix<T extends Comparable<T>> extends GMatrix<T>
{
	public CMatrix()
	{
		super();
	}
	
	public CMatrix(int height, int width)
	{
		super(height, width);
	}
	
	public CMatrix(T[][] values)
	{
		super(values);
	}
	
	CMatrix(T newEmptyVal)
	{
		super(newEmptyVal);
	}
	
	CMatrix(T newEmptyVal, int height, int width)
	{
		super(newEmptyVal, height, width);
	}
	
	CMatrix(T newEmptyVal, T[][] values)
	{
		super(newEmptyVal, values);
	}
	
	CMatrix(ArrayList<ArrayList<T>> newMatrixData)
	{
		super(newMatrixData);
	}
	
	CMatrix(T newEmptyVal, ArrayList<ArrayList<T>> newMatrixData)
	{
		super(newEmptyVal, newMatrixData);
	}

	public CMatrix<T> getRows(int start, int end)
	{
		return MatrixUtilities.genericToComparable(super.getRows(start, end));
	}
	
	public CMatrix<T> getColumns(int start, int end)
	{
		return MatrixUtilities.genericToComparable(super.getColumns(start, end));
	}
	
	public CMatrix<T> transpose()
	{
		return MatrixUtilities.genericToComparable(super.transpose());
	}
	
	public CMatrix<T> clone()
	{
		return MatrixUtilities.genericToComparable(super.clone());
	}
	
	public boolean equalTo(CMatrix<T> matrix)
	{
		if (matrix.height() != height() || matrix.width() != width())
			return false;
		
		for (int i = 0 ; i < height(); i++)
		{
			for (int j = 0; j < width(); j++)
			{
				if (get(i, j).compareTo(matrix.get(i, j)) != 0)
					return false;
			}
		}
		
		return true;
	}
	
	public boolean contains(CMatrix<T> matrix)
	{
		if (matrix.height() > height() || matrix.width() > width())
			return false;
		
		for (int i = 0; i < height() - matrix.height() + 1; i++)
		{
			for (int j = 0; j < width() - matrix.width() + 1; j++)
			{
				if (matrix.equalTo(getRows(i, i + matrix.height()).getColumns(j, j + matrix.width())))
					return true;
			}
		}
		
		return false;
	}
	
	public T columnMin(int index)
	{
		return MatrixUtilities.sort(index, this).get(0, index);
	}
	
	public T columnMax(int index)
	{
		return MatrixUtilities.sort(index, this, false).get(0, index);
	}
	
	public T rowMin(int index)
	{
		return MatrixUtilities.sort(index, transpose()).get(0, index);
	}
	
	public T rowMax(int index)
	{
		return MatrixUtilities.sort(index, transpose(), false).get(0, index);
	}
}
