import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;


public class ArrayQueue<E> implements QueueInterface<E> {

	Vector<E> arrayQueue = new Vector<E>();
	
	@Override
	public Iterator<E> iterator() {
		Vector<E> elements = (Vector<E>) arrayQueue.clone();
		QueueIterator<E> queueIterator = new QueueIterator<E>(elements, this);
		return queueIterator;
	}

	@Override
	public boolean isEmpty() {
		return arrayQueue.isEmpty();
	}

	@Override
	public int size() {
		return arrayQueue.size();
	}

	@Override
	public boolean add(E e) throws IllegalStateException, 
								   ClassCastException,
								   NullPointerException {

		// check for a null element being added to the queue
		if (e == null) {
			NullPointerException npe = new NullPointerException("Adding a \"null\" element to the Queue is invalid");
			throw npe;
		} 
	
		arrayQueue.addElement(e);
		return true;
	}

	@Override
	public E element() throws NoSuchElementException {
		
		if (isEmpty()) {
			NoSuchElementException nsee = new NoSuchElementException("The queue is empty. No element to return");
			throw nsee;
		}

		return arrayQueue.elementAt(0);
	}

	@Override
	public E remove() throws NoSuchElementException {
		
		if (isEmpty()) {
			NoSuchElementException nsee = new NoSuchElementException("The queue is empty. No element to return");
			throw nsee;
		}

		E element = arrayQueue.get(0);
		arrayQueue.remove(0);
		return element;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
		
		if (isEmpty()) {
			NoSuchElementException nsee = new NoSuchElementException("The queue is empty. No element to return");
			throw nsee;
		}

		E element = arrayQueue.get(index);
		arrayQueue.remove(index);
		return element;
	}

	@Override
	public void removeAll() {
		arrayQueue.removeAllElements();
		
	}

}
