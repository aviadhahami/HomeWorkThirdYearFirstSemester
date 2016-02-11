package crawler;

/**
 * This is where threads will register themselves...or something
 * 
 * @author aviad
 *
 */
public class ThreadsStone {

	private static int threadCount = 0;

	private ThreadsStone() {
		// TODO Auto-generated constructor stub
	}

	public static boolean hasThreads() {
		return threadCount > 0;
	}

	public static void register() {
		threadCount += 1;
	}

	public static void unregister() {
		threadCount -= 1;
	}

}
