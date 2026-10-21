package products;

/**
 * A skirt product.
 */
public class Skirts extends Product
{
	/**
	 * Sets both prices.
	 *
	 * @param originalPrice original price
	 * @param updatedPrice updated price
	 */
	public Skirts(double originalPrice, double updatedPrice)
	{
		super(originalPrice, updatedPrice);
	}

	/**
	 * Calculates the adjusted difference.
	 *
	 * @return adjusted difference
	 */
	@Override
	public double calcRealDiff()
	{
		return 0.1 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}
}