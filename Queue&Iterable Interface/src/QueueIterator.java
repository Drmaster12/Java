import java.util.Iterator;
import java.util.Vector;


public class QueueIterator<E> implements Iterator<E> {

	Vector<E> elements;
	QueueInterface<E> theQueue;
	int currentElement = 0;

	public QueueIterator(QueueInterface<E> theQueue) {
		this.elements = null;
		this.theQueue = theQueue;
	}
	
	public QueueIterator(Vector<E> elements, QueueInterface<E> theQueue) {
		this.elements = elements;
		this.theQueue = theQueue;
	}
	
	@Override
	public boolean hasNext() {
		return (currentElement < elements.size());
	}

	@Override
	public E next() {
		return elements.elementAt(currentElement++);
	}

	@Override
	public void remove() {
		currentElement--;
		elements.remove(currentElement);
		theQueue.remove(currentElement);
	}

}
