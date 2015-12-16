package server;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue<E> implements CustomQueue<E>{
	 
    // queue backed by a linkedlist
    private Queue<E> queue = new LinkedList<E>();
     
     
    /**
     * Enqueue will add an object to this queue, and will notify any waiting
     * threads that now there is an object available.
     * 
     * In enqueue method we just adding the elements not caring of size,
     * we can even introduce some check of size here also.
     */
    @Override
    public synchronized void enqueue(E e) {
        queue.add(e);
        // Wake up anyone waiting on the queue to put some item.
        notifyAll();
    }
 
    /**
    * Make a blocking call so that we will only return when the queue has
    * something on it, otherwise wait until something is put on it.
    */
    @Override
    public synchronized E dequeue(){
        E e = null;
         
        while(queue.isEmpty()){
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
