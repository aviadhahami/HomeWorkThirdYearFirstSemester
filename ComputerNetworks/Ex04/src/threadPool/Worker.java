package threadPool;


public class Worker implements Runnable {

	private MyQueue<Runnable> myQueue;

	public Worker(MyQueue<Runnable> myQueue) {
		this.myQueue = myQueue;
	}

	@Override
	public void run() {
		while (true) {
			Runnable r = myQueue.dequeue();
			myQueue.updateRun();
			r.run();
			myQueue.finishedRun();
		}
	}
}
