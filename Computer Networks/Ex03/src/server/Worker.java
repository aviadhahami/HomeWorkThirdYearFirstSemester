package server;

public class Worker implements Runnable {

	private MyQueue<Runnable> myQueue;

	public Worker(MyQueue<Runnable> myQueue) {
		this.myQueue = myQueue;
	}

	@Override
	public void run() {
		while (true) {
			Runnable r = myQueue.dequeue();
			r.run();
		}
	}
}
