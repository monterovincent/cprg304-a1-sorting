package utilities;

import java.util.Comparator;

/**
 * Sorts items from largest to smallest.
 */
public class BubbleSort
{
	/**
	 * Sorts the array using bubble sort.
	 *
	 * @param <T> item type
	 * @param array items to sort
	 * @param comparator comparison to use, or null for compareTo
	 */
	public static <T extends Comparable<? super T>> void sort(
			T[] array, Comparator<? super T> comparator)
	{
		for (int end = array.length - 1; end > 0; end--)
		{
			boolean swapped = false;

			for (int i = 0; i < end; i++)
			{
				if (Order.comesFirst(array[i + 1], array[i], comparator))
				{
					T temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;

					swapped = true;
				}
			}

			if (!swapped)
			{
				break;
			}
		}
	}
}