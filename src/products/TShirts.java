package products;

/**
 * A T-shirt product.
 */
public class TShirts extends Product
{
	/**
	 * Sets both prices.
	 *
	 * @param originalPrice original price
	 * @param updatedPrice updated price
	 */
	public TShirts(double originalPrice, double updatedPrice)
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
		return 0.2 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}
}