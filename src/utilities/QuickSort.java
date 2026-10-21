package utilities;

import java.util.Comparator;

public class QuickSort {
	private QuickSort()
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
		if( array == null || array.length < 2 )
		{
			return;
		}
		quickSort( array, 0, array.length - 1, comparator );
	}
 
	/**
	 * Sorts the range from {@code low} to {@code high}, both inclusive.
	 *
	 * @param <T>        the type of item in the array
	 * @param array      the array being sorted
	 * @param low        the first index of the range
	 * @param high       the last index of the range
	 * @param comparator the comparator to use, or null to use compareTo
	 */
	private static <T extends Comparable<? super T>> void quickSort( T[] array, int low, int high, Comparator<? super T> comparator )
	{
		while( low < high )
		{
			int split = partition( array, low, high, comparator );
 
			if( split - low < high - split )
			{
				quickSort( array, low, split, comparator );
				low = split + 1;
			}
			else
			{
				quickSort( array, split + 1, high, comparator );
				high = split;
			}
		}
	}
 
	/**
	 * Partitions the range around a median-of-three pivot. Afterwards every item
	 * from {@code low} to the returned index comes before or ties with every item
	 * after it.
	 *
	 * @param <T>        the type of item in the array
	 * @param array      the array being sorted
	 * @param low        the first index of the range
	 * @param high       the last index of the range
	 * @param comparator the comparator to use, or null to use compareTo
	 * @return the last index of the left part, between {@code low} and
	 *         {@code high - 1}
	 */
	private static <T extends Comparable<? super T>> int partition( T[] array, int low, int high, Comparator<? super T> comparator )
	{
		int middle = low + ( high - low ) / 2;
		T pivot = medianOfThree( array, low, middle, high, comparator );
 
		int i = low - 1;
		int j = high + 1;
		while( true )
		{
			do
			{
				i++;
			}
			while( Order.comesFirst( array[i], pivot, comparator ) );
 
			do
			{
				j--;
			}
			while( Order.comesFirst( pivot, array[j], comparator ) );
 
			if( i >= j )
			{
				return j;
			}
			swap( array, i, j );
		}
	}
 
	/**
	 * Orders the first, middle and last items among themselves so that the
	 * median ends up in the middle position, and returns that median.
	 *
	 * @param <T>        the type of item in the array
	 * @param array      the array being sorted
	 * @param low        the index of the first item
	 * @param middle     the index of the middle item
	 * @param high       the index of the last item
	 * @param comparator the comparator to use, or null to use compareTo
	 * @return the median of the three items, now stored at {@code middle}
	 */
	private static <T extends Comparable<? super T>> T medianOfThree( T[] array, int low, int middle, int high, Comparator<? super T> comparator )
	{
		if( Order.comesFirst( array[middle], array[low], comparator ) )
		{
			swap( array, low, middle );
		}
		if( Order.comesFirst( array[high], array[low], comparator ) )
		{
			swap( array, low, high );
		}
		if( Order.comesFirst( array[high], array[middle], comparator ) )
		{
			swap( array, middle, high );
		}
		return array[middle];
	}
 
	/**
	 * Swaps two items in the array.
	 *
	 * @param <T>   the type of item in the array
	 * @param array the array holding the items
	 * @param i     the index of the first item
	 * @param j     the index of the second item
	 */
	private static <T> void swap( T[] array, int i, int j )
	{
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
