package appDomain;
import java.util.Locale;
import products.Product;

public class OutputFormatter {
	
	/** Not meant to be created . all methods are static */
	private OutputFormatter() 
	{
	}
	
	/**
	 * Turns the -s letter into the sort's name.
	 *
	 * @param sortType b, s, i, m, q or z
	 * @return the name to show, for example "Bubble"
	 */
	
	public static String sortName(char sortType)
	{
		switch (sortType)
		{
		case 'b': return "Bubble";
		case 's': return "Selection";
		case 'i': return "Insertion";
		case 'm': return "Merge";
		case 'q': return "Quick";
		case 'z': return "Heap";
		default: throw new IllegalArgumentException( "Unknown sort type '" + sortType + "'" );
		}
	}
	
	/**
	 * Prints how long a sort took, for example
	 * "Quick Sort run time was: 27.412 milliseconds".
	 *
	 * @param sortName the sort's name, for example "Quick"
	 * @param nanos the elapsed time in nanoseconds
	 */
	
	public static void printTime( String sortName, long nanos )
	{
		// Locale.US keeps the decimal point a '.' on every computer
		String millis = String.format( Locale.US, "%.3f", nanos / 1_000_000.0 );
		System.out.println( sortName + " Sort run time was: " + millis + " milliseconds" );
	}
	
	/**
	 * Turns a position from 1 to 10 into 1-st, 2-nd, 3-rd, 4-th and so on.
	 *
	 * @param position the 1-based position
	 * @return the position with its ending
	 */
	
	private static String ordinal (int position)
	{
		switch ( position )
		{
			case 1: return "1-st";
			case 2: return "2-nd";
			case 3: return "3-rd";
			default: return position + "-th";
		}
		
	}
	
	/**
	 * Gives the text shown before the value.
	 *
	 * @param compareType o, u or d
	 * @return Original Price, Updated Price or Real Difference
	 */
	
	private static String valueLabel(char compareType)
	{
		switch ( compareType )
		{
			case 'o': return "Original Price";
			case 'u': return "Updated Price";
			case 'd': return "Real Difference";
			default: throw new IllegalArgumentException( "Unknown compare type '" + compareType + "'" );
		}
	}
	
	/**
	 * Gives the number being sorted on.
	 *
	 * @param product the product to read
	 * @param compareType o, u or d
	 * @return the original price, updated price or real difference
	 */
	private static double value( Product product, char compareType )
	{
		switch ( compareType )
		{
			case 'o': return product.getOriginalPrice();
			case 'u': return product.getUpdatedPrice();
			default: return product.calcRealDiff();
		}
	}
	
	/**
	 * Prints one row: label, class name, then the value being sorted on.
	 *
	 * @param label the row label, for example 100-th element:
	 * @param product the product to show
	 * @param compareType o, u or d
	 */
	private static void printLine( String label, Product product, char compareType )
	{
		System.out.printf( "%-20s %16s   %s: %s%n",
				label, product.getClass().getName(), valueLabel( compareType ), value( product, compareType ) );
	}
	
	/**
	 * Prints the sorted products using the assignment's display rule:
	 * 10 or fewer prints all, up to 1000 prints the first, every 100th and
	 * the last, and more than 1000 prints the first, every 1000th and the last.
	 *
	 * @param sorted the products, already sorted
	 * @param compareType o (original price), u (updated price) or d (real difference)
	 */
	
	public static void display( Product[] sorted, char compareType )
	{
		int n = sorted.length;
		if ( n == 0 )
		{
			return;
		}

		if ( n <= 10 )
		{
			for ( int position = 1; position <= n; position++ )
			{
				printLine( ordinal( position ) + " element:", sorted[position - 1], compareType );
			}
			return;
		}

		int step = ( n <= 1000 ) ? 100 : 1000;

		printLine( "First element is:", sorted[0], compareType );
		// pos < n: a last position that is a multiple prints once, as the last element
		for ( int pos = step; pos < n; pos += step )
		{
			printLine( pos + "-th element:", sorted[pos - 1], compareType );
		}
		printLine( "Last element is:", sorted[n - 1], compareType );
	}

}
