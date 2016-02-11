package threadPool;

import java.util.LinkedList;
import java.util.Queue;

import interfaces.CustomQueue;

public class MyQueue<E> implements CustomQueue<E> {

	// queue backed by a linkedlist
	private Queue<E> queue = new LinkedList<E>();
	private int finished = 0;
	private int running = 0;

	@Override
	public synchronized void enqueue(E e) {
		queue.add(e);
		// Wake up anyone waiting on the queue to put some item.
		notifyAll();
	}

	@Override
	public synchronized E dequeue() {
		E e = null;

		while (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e1) {
				return e;
			}
		}
		e = queue.remove();
		return e;
	}

	public void finishedRun() {
		this.finished += 1;
	}

	public void updateRun() {
		this.running += 1;
	}

	public int getRunning() {
		return running;
	}

	public int getFinished() {
		return finished;
	}
}
