package threadPool;

public class ThreadPoolManager {

	private final int THREADPOOL_CAPACITY;
	private MyQueue<Runnable> myQueue = new MyQueue<Runnable>();

	public ThreadPoolManager(int capacity) {
		this.THREADPOOL_CAPACITY = capacity;
		initAllConsumers();
	}

	private void initAllConsumers() {
		for (Integer i = 0; i < THREADPOOL_CAPACITY; i++) {
			Thread thread = new Thread(new Worker(myQueue));
			thread.start();
		}
	}

	public void submitTask(Runnable r) {
		myQueue.enqueue(r);
	}

	public boolean isEmpty() {
		return myQueue.getFinished() == myQueue.getRunning();
	}

	public boolean hasTasks() {
		return myQueue.hasTasks();
	}

}