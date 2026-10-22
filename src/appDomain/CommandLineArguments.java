package appDomain;

/**
 * Reads and checks the command-line arguments:
 * -f&lt;file_name&gt; -t&lt;o|u|d&gt; -s&lt;b|s|i|m|q|z&gt;.
 * Flags can come in any order and in any letter case.
 */
public class CommandLineArguments
{
	/** Shown after every command-line error. */
	public static final String USAGE =
			"Usage: java -jar Sort.jar -f<file_name> -t<o|u|d> -s<b|s|i|m|q|z>";

	private final String fileName;
	private final char compareType;
	private final char sortType;

	private CommandLineArguments( String fileName, char compareType, char sortType )
	{
		this.fileName = fileName;
		this.compareType = compareType;
		this.sortType = sortType;
	}

	/**
	 * Checks the arguments and returns them in a ready-to-use form.
	 *
	 * @param args the arguments exactly as Java received them
	 * @return the file name, compare type and sort type (letters in lower case)
	 * @throws IllegalArgumentException with the message to show if anything is wrong
	 */
	public static CommandLineArguments parse( String[] args )
	{
		if( args.length == 0 )
		{
			throw new IllegalArgumentException( "No arguments were given." );
		}

		String fileName = null;
		String compare = null;
		String sort = null;

		for( String arg : args )
		{
			// An option starts with - (or the en dash the assignment's example uses) and a flag letter
			boolean looksLikeOption = arg.length() >= 2
					&& ( arg.charAt( 0 ) == '-' || arg.charAt( 0 ) == '\u2013' );
			if( !looksLikeOption )
			{
				throw new IllegalArgumentException( "Unknown option '" + arg + "'" );
			}

			char flag = Character.toLowerCase( arg.charAt( 1 ) );
			String value = arg.substring( 2 );

			switch( flag )
			{
				case 'f':
					if( fileName != null )
					{
						throw new IllegalArgumentException( "-f was given more than once" );
					}
					if( value.isEmpty() )
					{
						throw new IllegalArgumentException(
								"-f needs a file name, for example -fres\\products1.txt" );
					}
					fileName = value;
					break;
				case 't':
					if( compare != null )
					{
						throw new IllegalArgumentException( "-t was given more than once" );
					}
					compare = value.toLowerCase();
					break;
				case 's':
					if( sort != null )
					{
						throw new IllegalArgumentException( "-s was given more than once" );
					}
					sort = value.toLowerCase();
					break;
				default:
					throw new IllegalArgumentException( "Unknown option '" + arg.substring( 0, 2 ) + "'" );
			}
		}

		if( fileName == null )
		{
			throw new IllegalArgumentException( "Missing -f (file name)" );
		}
		if( compare == null )
		{
			throw new IllegalArgumentException( "Missing -t (compare type: o, u or d)" );
		}
		if( sort == null )
		{
			throw new IllegalArgumentException( "Missing -s (sort type: b, s, i, m, q or z)" );
		}
		if( !compare.matches( "[oud]" ) )
		{
			throw new IllegalArgumentException( "-t must be o, u or d, found '" + compare + "'" );
		}
		if( !sort.matches( "[bsimqz]" ) )
		{
			throw new IllegalArgumentException( "-s must be b, s, i, m, q or z, found '" + sort + "'" );
		}

		return new CommandLineArguments( fileName, compare.charAt( 0 ), sort.charAt( 0 ) );
	}

	/** @return the data file name or path */
	public String getFileName()
	{
		return fileName;
	}

	/** @return o, u or d */
	public char getCompareType()
	{
		return compareType;
	}

	/** @return b, s, i, m, q or z */
	public char getSortType()
	{
		return sortType;
	}
}