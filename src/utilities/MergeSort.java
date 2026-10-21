package utilities;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {
	private MergeSort()
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
		T[] helper = Arrays.copyOf( array, array.length );
		mergeSort( array, helper, 0, array.length - 1, comparator );
	}
 
	/**
	 * Sorts the range from low to  high, both inclusive.
	 */
	private static <T extends Comparable<? super T>> void mergeSort( T[] array, T[] helper, int low, int high, Comparator<? super T> comparator )
	{
		if( low >= high )
		{
			return;
		}
		int middle = low + ( high - low ) / 2;
		mergeSort( array, helper, low, middle, comparator );
		mergeSort( array, helper, middle + 1, high, comparator );
		merge( array, helper, low, middle, high, comparator );
	}
 
	/**
	 * Merges the sorted ranges low..middle and middle+1..high
	 * into one sorted range. When two items tie, the one from the left half is
	 * taken first, which keeps the sort stable.
	 */
	private static <T extends Comparable<? super T>> void merge( T[] array, T[] helper, int low, int middle, int high, Comparator<? super T> comparator )
	{
		System.arraycopy( array, low, helper, low, high - low + 1 );
 
		int left = low;
		int right = middle + 1;
		int target = low;
 
		while( left <= middle && right <= high )
		{
			if( Order.comesFirst( helper[right], helper[left], comparator ) )
			{
				array[target] = helper[right];
				right++;
			}
			else
			{
				array[target] = helper[left];
				left++;
			}
			target++;
		}
 
		while( left <= middle )
		{
			array[target] = helper[left];
			left++;
			target++;
		}
	}
}
