/**
 * This class represents a node to be used in a Queue
 * 
 * @author Sameh Fakhouri
 *
 */

public class Node<E> {

	private E data;
	private Node<E> next;

	
	/**
	 * Constructor that for the Node class
	 * 
	 * @param	data	This is the data <b>Object</b> held by the LinkedListNode.
	 */
	public Node(E data) {
		super();
		this.data = data;
		this.next = null;
	}
	
	/**
	 * Constructor that for the Node class
	 * 
	 * @param	data	This is the data <b>Object</b> held by the Node.
	 * @param	next	This is the pointer to the next LinkedListNode in the Linked List.
	 */
	public Node(E data, Node<E> next) {
		super();
		this.data = data;
		this.next = next;
	}

	/**
	 * Getter method to obtain a reference to the <b>Object</b> held by the LinkedListNode.
	 * 
	 * @return	The <b>Object</b> held by the Node.
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Setter method to set the reference to the <b>Object</b> held by the LinkedListNode.
	 * 
	 * @param	data	The reference to the <b>Object</b> to be held by the LinkedListNode.
	 */
	public void setData(E data) {
		this.data = data;
	}
	
	/**
	 * Getter method to obtain a reference to the next LinkedListNode in the Linked List.
	 * 
	 * @return	The next LinkedListNode in the Linked List.
	 */
	public Node<E> getNext() {
		return next;
	}
	
	/**
	 * Setter method to set the reference to the next LinkedListNode in the Linked List.
	 * 
	 * @param	next	The reference to the next LinkedListNode in the Linked List.
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}
}
