package interfaces;


public interface CustomQueue<E> {

	public void enqueue(E e);

	public E dequeue();

}