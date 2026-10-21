package utilities;

import java.util.Comparator;

public class HeapSort {
	private HeapSort()
	{
	}
 
	/**
	 * Sorts the array in place into descending order.
	 */
	public static <T extends Comparable<? super T>> void sort( T[] array, Comparator<? super T> comparator )
	{
		if( array == null || array.length < 2 )
		{
			return;
		}
 
		int n = array.length;
 
		// Build the heap, starting from the last parent and working back to the root.
		for( int i = n / 2 - 1; i >= 0; i-- )
		{
			siftDown( array, i, n, comparator );
		}
 
		// Move the root, that is the item that comes last to the end of the unsorted part.
		for( int end = n - 1; end > 0; end-- )
		{
			T temp = array[0];
			array[0] = array[end];
			array[end] = temp;
			siftDown( array, 0, end, comparator );
		}
	}
 
	/**
	 * Moves the item at index down the heap until neither of its
	 * children comes after it, so the item that comes last rises to the top.
	 */
	private static <T extends Comparable<? super T>> void siftDown( T[] array, int index, int size, Comparator<? super T> comparator )
	{
		while( true )
		{
			int left = 2 * index + 1;
			int right = left + 1;
			int last = index;
 
			if( left < size && Order.comesFirst( array[last], array[left], comparator ) )
			{
				last = left;
			}
			if( right < size && Order.comesFirst( array[last], array[right], comparator ) )
			{
				last = right;
			}
			if( last == index )
			{
				return;
			}
 
			T temp = array[index];
			array[index] = array[last];
			array[last] = temp;
			index = last;
		}
	}
}
