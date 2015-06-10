import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;


public class LinkedQueue<E> implements QueueInterface<E> {

	Node<E> front, back;
	int	numElements = 0;
	
	
	@Override
	public Iterator<E> iterator() {
		QueueIterator<E> queueIterator = null;
		Vector<E> elements = new Vector<E>();
		Node<E> curNode;

		if (!isEmpty()) {
			for ( curNode = front ; curNode != null ; curNode = curNode.getNext() ) {
				elements.add(curNode.getData());
			}
		}
		queueIterator = new QueueIterator<E>(elements, this);
	
		return queueIterator;
	}

	@Override
	public boolean isEmpty() {
		return (numElements == 0);
	}

	@Override
	public int size() {
		return numElements;
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
		
		Node<E> newNode = new Node<E>(e);
		
		// if the list is empty, the new Node becomes the head
		if (isEmpty()) {
			front = newNode;
		} else {
			back.setNext(newNode);
		}
		back = newNode;
		numElements++;
		
		return true;
	}

	@Override
	public E element() throws NoSuchElementException {
		
		if (isEmpty()) {
			NoSuchElementException nsee = new NoSuchElementException("The queue is empty. No element to return");
			throw nsee;
		}
		
		return front.getData();
	}

	@Override
	public E remove() throws NoSuchElementException {
		E element = null;
		
		if (isEmpty()) {
			NoSuchElementException nsee = new NoSuchElementException("The queue is empty. No element to remove");
			throw nsee;
		}

		// get the element from the front node
		element = front.getData();
		
		// remove the front node and update item count
		front = front.getNext();

		// if the queue is empty, update the back as well
		if (front == null) {
			back = null;
		}

		numElements--;
		
		return element;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {

		E element = null;
		Node<E> curNode = front;
		Node<E> prevNode = back;
		
		if (isEmpty()) {
			NoSuchElementException nsee = new NoSuchElementException("The queue is empty. No element to remove");
			throw nsee;
		} else if (index >= size()) {
			NoSuchElementException nsee = new NoSuchElementException("Specified index = " + index + " is beyond the current queue length = " + numElements);
			throw nsee;
		}
		
		if (index == 0) {
			element = remove();
		} else {
			// find the node to remove
			for ( int i = 0 ; i < index ; i++ ) {
				prevNode = curNode;
				curNode = curNode.getNext();
			}
			// remove it
			element = curNode.getData();
			prevNode.setNext(curNode.getNext());

			if (curNode == back) {
				back = prevNode;
			}
			numElements--;
		}			
		
		return element;
	}

	@Override
	public void removeAll() {
		front = null;
		back = null;
		numElements = 0;
	}

}
