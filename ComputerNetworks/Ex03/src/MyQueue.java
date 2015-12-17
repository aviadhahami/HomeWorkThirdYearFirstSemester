

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue<E> implements CustomQueue<E> {

	// queue backed by a linkedlist
	private Queue<E> queue = new LinkedList<E>();

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
}
