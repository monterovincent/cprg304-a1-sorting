package products;

/**
 * A shoes product.
 */
public class Shoes extends Product
{
	/**
	 * Sets both prices.
	 *
	 * @param originalPrice original price
	 * @param updatedPrice updated price
	 */
	public Shoes(double originalPrice, double updatedPrice)
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
		return 0.4 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}
}