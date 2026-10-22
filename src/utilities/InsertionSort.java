package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Insertion sort for products.
 */
public class InsertionSort
{
	/**
	 * Sorts from largest to smallest.
	 *
	 * @param array products to sort
	 * @param comparator comparison rule, or null for compareTo
	 */
	public static void sort(Product[] array, Comparator<Product> comparator)
	{
		for (int i = 1; i < array.length; i++)
		{
			Product current = array[i];
			int j = i - 1;

			while (j >= 0 && Order.comesFirst(current, array[j], comparator))
			{
				// Move the smaller product right.
				array[j + 1] = array[j];
				j--;
			}

			// Insert the saved product.
			array[j + 1] = current;
		}
	}
}