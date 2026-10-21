package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Compares updated prices.
 */
public class UpdatedPriceComparator implements Comparator<Product>
{
	/**
	 * Compares two products.
	 *
	 * @param first first product
	 * @param second second product
	 * @return negative if smaller, zero if equal, positive if larger
	 */
	@Override
	public int compare(Product first, Product second)
	{
		return Double.compare(first.getUpdatedPrice(), second.getUpdatedPrice());
	}
}