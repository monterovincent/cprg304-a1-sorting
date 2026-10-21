package products;

/**
 * A jeans product.
 */
public class Jeans extends Product
{
	/**
	 * Sets both prices.
	 *
	 * @param originalPrice original price
	 * @param updatedPrice updated price
	 */
	public Jeans(double originalPrice, double updatedPrice)
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
		return 0.5 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}
}