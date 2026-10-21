package utilities;

import java.util.Comparator;

public class SelectionSort {
	private SelectionSort()
	{
	}

	/**
	 * Sorts the array in place into descending order.
	 *
	 * @param <T>        the type of item in the array
	 * @param array      the array to sort; it is re-arranged in place
	 * @param comparator the comparator to use, or null to use compareTo
	 */
	public static <T extends Comparable<? super T>> void sort( T[] array, Comparator<? super T> comparator )
	{
		if( array == null )
		{
			return;
		}

		int n = array.length;
		for( int i = 0; i < n - 1; i++ )
		{
			int firstIndex = i;
			for( int j = i + 1; j < n; j++ )
			{
				if( Order.comesFirst( array[j], array[firstIndex], comparator ) )
				{
					firstIndex = j;
				}
			}

			if( firstIndex != i )
			{
				T temp = array[i];
				array[i] = array[firstIndex];
				array[firstIndex] = temp;
			}
		}
	}
}
