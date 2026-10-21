package products;

/**
 * A product with two prices.
 */
public abstract class Product implements Comparable<Product>
{
	/** Original price. */
	private double originalPrice;

	/** Updated price. */
	private double updatedPrice;

	/**
	 * Sets both prices.
	 *
	 * @param originalPrice original price
	 * @param updatedPrice updated price
	 */
	public Product(double originalPrice, double updatedPrice)
	{
		this.originalPrice = originalPrice;
		this.updatedPrice = updatedPrice;
	}

	/**
	 * Gets the original price.
	 *
	 * @return original price
	 */
	public double getOriginalPrice()
	{
		return originalPrice;
	}

	/**
	 * Gets the updated price.
	 *
	 * @return updated price
	 */
	public double getUpdatedPrice()
	{
		return updatedPrice;
	}

	/**
	 * Calculates the adjusted difference.
	 *
	 * @return adjusted difference
	 */
	public abstract double calcRealDiff();

	/**
	 * Compares adjusted differences.
	 *
	 * @param other product to compare
	 * @return negative if smaller, zero if equal, positive if larger
	 */
	@Override
	public int compareTo(Product other)
	{
		return Double.compare(this.calcRealDiff(), other.calcRealDiff());
	}
}