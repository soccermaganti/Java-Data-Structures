package structures;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	//initializes the nodes
	Node<T> front;
	Node<T> rear;
	int size;
	
	public Queue() {		
            // TODO 

    }
	
	public Queue(Queue<T> other) {
            // TODO 2
			//deep copy constructor that allows you to create a new copy of the constructor
			if (other == null) {
				return;
			} 
			Node<T> curr = other.front;
			while (curr != null) {
				this.enqueue(curr.data);
				curr = curr.next;
			} 
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
        return front == null;
	}

	@Override
	public int getSize() {
            // TODO 4
            return size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
		//enqueues an object by creating a new node and adding it as either the first node if
		//queue is empty or at the end by setting rear as the next.
		if (element == null) {
			return;
		}		
		if (front == null || rear == null){
			rear = new Node<T>(element);
			front = rear;
		} else {
			rear.next = new Node<T>(element);
			rear = rear.next;
		}
		size++;
	}
	

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
			//takes the front data and then returns it and then removes the node
			if (isEmpty()) throw new NoSuchElementException();
			T elem = front.data;
			front = front.next;
			size--;
			return elem;
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
		//returns front data but doesn't remove
		if (isEmpty()) throw new NoSuchElementException();
		return front.data;
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO 8

		Node<T> curr = front;
		//Creates a new Queue called newList
		Queue<T> newList = new Queue<T>();
		//sets curr to front and then loops through til curr is null
		while (curr != null) {
			//enqueues the data in the front essentially reversing it.
			newList.enqueue(curr.data);
			curr = curr.next;
		}
	return newList; 
	} 
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

