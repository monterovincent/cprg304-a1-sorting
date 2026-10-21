package appDomain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import products.Dresses;
import products.Jackets;
import products.Jeans;
import products.Product;
import products.Shoes;
import products.Shorts;
import products.Skirts;
import products.TShirts;

/**
 * Reads a product file into a plain Product array.
 * <p>
 * File format: line 1 is the number of products (N). Each of the next N
 * lines is a product type, an original price and an updated price, for
 * example {@code Jeans 54.46 63.25}.
 */
public class ProductFileReader
{
	/** The seven valid product type names. */
	private static final String[] TYPE_NAMES = { "Jeans", "TShirts", "Skirts", "Dresses", "Shorts", "Jackets", "Shoes" };

	/** Not meant to be created. All the work is done by the static read method. */
	private ProductFileReader()
	{
	}

	/**
	 * Reads the file and returns the products in the order they appear.
	 *
	 * @param fileName path of the product file (used exactly as given)
	 * @return an array holding one Product per product line
	 * @throws IOException if the file is missing, empty or cannot be read
	 * @throws IllegalArgumentException if the file contents are not valid
	 */
	public static Product[] read( String fileName ) throws IOException
	{
		try ( BufferedReader reader = new BufferedReader( new FileReader( fileName ) ) )
		{
			// Line 1: how many products to expect.
			String firstLine = reader.readLine();
			if ( firstLine == null )
			{
				throw new IOException( "The file is empty: " + fileName );
			}

			int count = parseCount( firstLine );
			Product[] products = new Product[count];

			// Lines 2 .. N+1: one product each. We stop after N lines, so
			// blank lines or extra text at the end of the file are ignored.
			for ( int i = 0; i < count; i++ )
			{
				String line = reader.readLine();
				int lineNumber = i + 2;
				if ( line == null )
				{
					throw new IllegalArgumentException(
							"Expected " + count + " products but the file has only " + i );
				}
				products[i] = parseProduct( line, lineNumber );
			}
			return products;
		}
		catch ( FileNotFoundException e )
		{
			throw new IOException( "File not found: " + fileName );
		}
	}

	/**
	 * Turns line 1 into the product count.
	 *
	 * @param firstLine the first line of the file
	 * @return the number of products the file says it holds
	 */
	private static int parseCount( String firstLine )
	{
		String text = firstLine.trim();
		try
		{
			int count = Integer.parseInt( text );
			if ( count < 0 )
			{
				throw new NumberFormatException();
			}
			return count;
		}
		catch ( NumberFormatException e )
		{
			throw new IllegalArgumentException(
					"Line 1 must be the number of products, found '" + text + "'" );
		}
	}

	/**
	 * Turns one product line into a Product object.
	 *
	 * @param line the text of the line
	 * @param lineNumber the line's number in the file (line 1 is the count)
	 * @return the matching Product subclass object
	 */
	private static Product parseProduct( String line, int lineNumber )
	{
		String[] parts = line.trim().split( "\\s+" );
		if ( parts.length != 3 )
		{
			throw new IllegalArgumentException(
					"Line " + lineNumber + ": expected a product type and two prices" );
		}

		// Check the type first, so a line with a bad type AND a bad price
		// reports the type problem (the order the user would fix them in).
		String type = parts[0];
		if ( !isKnownType( type ) )
		{
			throw new IllegalArgumentException(
					"Line " + lineNumber + ": unknown product type '" + type + "'" );
		}

		double original = parsePrice( parts[1], lineNumber );
		double updated = parsePrice( parts[2], lineNumber );

		return create( type, original, updated );
	}

	/**
	 * Checks a type name against the seven valid names, ignoring capitalisation.
	 *
	 * @param type the type name from the file
	 * @return true if it is one of the seven product types
	 */
	private static boolean isKnownType( String type )
	{
		for ( String name : TYPE_NAMES )
		{
			if ( name.equalsIgnoreCase( type ) )
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Builds the subclass that matches an already-checked type name.
	 *
	 * @param type a valid product type name
	 * @param original the original price
	 * @param updated the updated price
	 * @return a new Product of that type
	 */
	private static Product create( String type, double original, double updated )
	{
		if ( type.equalsIgnoreCase( "Jeans" ) ) return new Jeans( original, updated );
		if ( type.equalsIgnoreCase( "TShirts" ) ) return new TShirts( original, updated );
		if ( type.equalsIgnoreCase( "Skirts" ) ) return new Skirts( original, updated );
		if ( type.equalsIgnoreCase( "Dresses" ) ) return new Dresses( original, updated );
		if ( type.equalsIgnoreCase( "Shorts" ) ) return new Shorts( original, updated );
		if ( type.equalsIgnoreCase( "Jackets" ) ) return new Jackets( original, updated );
		return new Shoes( original, updated ); // the only name left
	}

	/**
	 * Turns the text of one price into a double.
	 *
	 * @param text the price as written in the file
	 * @param lineNumber the line it came from, used in the error message
	 * @return the price as a number
	 */
	private static double parsePrice( String text, int lineNumber )
	{
		try
		{
			return Double.parseDouble( text );
		}
		catch ( NumberFormatException e )
		{
			throw new IllegalArgumentException(
					"Line " + lineNumber + ": price '" + text + "' is not a number" );
		}
	}
}