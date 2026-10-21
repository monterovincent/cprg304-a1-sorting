package utilities;

import java.util.Comparator;

/**
 * Sorts items from largest to smallest.
 */
public class InsertionSort
{
	/**
	 * Sorts the array using insertion sort.
	 *
	 * @param <T> item type
	 * @param array items to sort
	 * @param comparator comparison to use, or null for compareTo
	 */
	public static <T extends Comparable<? super T>> void sort(
			T[] array, Comparator<? super T> comparator)
	{
		for (int i = 1; i < array.length; i++)
		{
			T current = array[i];
			int j = i - 1;

			while (j >= 0 && Order.comesFirst(current, array[j], comparator))
			{
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = current;
		}
	}
}