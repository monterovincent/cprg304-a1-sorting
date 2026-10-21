package products;

/**
 * A shorts product.
 */
public class Shorts extends Product
{
	/**
	 * Sets both prices.
	 *
	 * @param originalPrice original price
	 * @param updatedPrice updated price
	 */
	public Shorts(double originalPrice, double updatedPrice)
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
		return 0.3 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}
}