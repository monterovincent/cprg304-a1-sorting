package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Bubble sort for products.
 */
public class BubbleSort
{
	/**
	 * Sorts from largest to smallest.
	 *
	 * @param array products to sort
	 * @param comparator comparison rule, or null for compareTo
	 */
	public static void sort(Product[] array, Comparator<Product> comparator)
	{
		for (int end = array.length - 1; end > 0; end--)
		{
			boolean swapped = false;

			for (int i = 0; i < end; i++)
			{
				if (Order.comesFirst(array[i + 1], array[i], comparator))
				{
					// Swap the two products.
					Product temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;

					swapped = true;
				}
			}

			// Stop if no products moved.
			if (!swapped)
			{
				break;
			}
		}
	}
}