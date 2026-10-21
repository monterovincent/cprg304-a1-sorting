package utilities;

import java.util.Comparator;

public class Order {
	private Order()
	{
	}
 
	/**
	 * Decides whether a belongs before b in descending order.
	 * When a comparator is given it is used; when the comparator is null the
	 * items' natural order is used instead.
	 */
	public static <T extends Comparable<? super T>> boolean comesFirst( T a, T b, Comparator<? super T> comparator )
	{
		int result;
		if( comparator == null )
		{
			result = a.compareTo( b );
		}
		else
		{
			result = comparator.compare( a, b );
		}
		return result > 0;
	}
}
