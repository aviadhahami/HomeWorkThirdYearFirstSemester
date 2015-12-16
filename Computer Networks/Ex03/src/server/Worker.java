package server;

public class Worker implements Runnable {

	private MyQueue<Runnable> myQueue;
	private String name;

	public Worker(MyQueue<Runnable> myQueue, String name) {
		this.myQueue = myQueue;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			Runnable r = myQueue.dequeue();
			// print the dequeued item
			System.out.println(" Taken Item by thread name:" + this.name);
			// run it
			r.run();
			System.out.println(" Task completed of thread:" + this.name);
		}
	}
}
