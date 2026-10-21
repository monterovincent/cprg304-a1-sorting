package appDomain;

import java.io.IOException;
import java.util.Comparator;

import products.Product;
import utilities.BubbleSort;
import utilities.HeapSort;
import utilities.InsertionSort;
import utilities.MergeSort;
import utilities.OriginalPriceComparator;
import utilities.QuickSort;
import utilities.SelectionSort;
import utilities.UpdatedPriceComparator;

/**
 * Runs the sorting application:
 * java -jar Sort.jar -f&lt;file&gt; -t&lt;o|u|d&gt; -s&lt;b|s|i|m|q|z&gt;
 * <p>
 * Read the arguments, load the products, time the chosen sort, then show
 * the result. Only the sort itself is timed.
 */
public class AppDriver
{
	/** Not meant to be created. The program starts at main. */
	private AppDriver()
	{
	}
	
	/**
	 * Picks the comparator for the -t letter.
	 *
	 * @param compareType o, u or d
	 * @return the matching comparator, or null for d (the sort then uses compareTo)
	 */
	private static Comparator<Product> chooseComparator( char compareType )
	{
		switch ( compareType )
		{
			case 'o': return new OriginalPriceComparator();
			case 'u': return new UpdatedPriceComparator();
			default: return null;
		}
	}
	
	/**
	 * Calls the sort for the -s letter. The array is sorted in place.
	 *
	 * @param sortType b, s, i, m, q or z
	 * @param products the array to sort
	 * @param comparator the comparator, or null to use compareTo
	 */
	private static void runSort( char sortType, Product[] products, Comparator<Product> comparator )
	{
		switch ( sortType )
		{
			case 'b': BubbleSort.sort( products, comparator ); break;
			case 's': SelectionSort.sort( products, comparator ); break;
			case 'i': InsertionSort.sort( products, comparator ); break;
			case 'm': MergeSort.sort( products, comparator ); break;
			case 'q': QuickSort.sort( products, comparator ); break;
			default: HeapSort.sort( products, comparator ); break;
		}
	}
	
	/**
	 * Program entry point.
	 *
	 * @param args the command-line arguments
	 */
	public static void main( String[] args )
	{
		// 1. Command-line arguments
		CommandLineArguments options;
		try
		{
			options = CommandLineArguments.parse( args );
		}
		catch ( IllegalArgumentException e )
		{
			System.err.println( "ERROR: " + e.getMessage() );
			System.err.println( CommandLineArguments.USAGE );
			return;
		}
		
		// 2. Read the product file (not timed)
		Product[] products;
		try
		{
			products = ProductFileReader.read( options.getFileName() );
		}
		catch ( IOException | IllegalArgumentException e )
		{
			System.err.println( "ERROR: " + e.getMessage() );
			return;
		}
		
		// 3. Sort, timing only the sort call
		char sortType = options.getSortType();
		char compareType = options.getCompareType();
		Comparator<Product> comparator = chooseComparator( compareType );
		long nanos = Benchmark.timeNanos( () -> runSort( sortType, products, comparator ) );
		
		// 4. Show the result (not timed)
		OutputFormatter.display( products, compareType );
		OutputFormatter.printTime( OutputFormatter.sortName( sortType ), nanos );
	}

}