package appDomain;

/**
 * Times a sort call. Only the code inside the task is measured.
 */
public class Benchmark
{
	/**
	 * Runs the task once and returns how long it took.
	 *
	 * @param task the sort call to time
	 * @return elapsed time in nanoseconds
	 */
	public static long timeNanos( Runnable task )
	{
		long start = System.nanoTime();
		task.run();
		long end = System.nanoTime();
		return end - start;
	}
}