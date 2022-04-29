package priorityqueue;

import java.io.UncheckedIOException;
import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;

  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
      //TODO: Implement this method.
      numElements = 0;
      this.comparator = comparator;
      this.isMaxHeap = isMaxHeap;
      heap = (T[]) new Object[INIT_SIZE];
  }

  //zybooks
  private int getLeftChildOf(int parentIndex){
    return 2 * parentIndex + 1;
  }
  //zybooks
  private int getRightChildOf(int parentIndex){
    return 2 * parentIndex + 2;
  }
  //zybooks
  private int getParentOf(int childIndex){
    return (childIndex -  1) / 2;
  }

  private void swapIndices(int index1, int index2){
    T temp = heap[index1];
    heap[index1] = heap[index2];
    heap[index2] = temp;
  }

  private void expandCapacity(){
    //creates a new array with an 1 extra size and then sets that array equal to old array
    if (numElements == heap.length) {
      T[] newHeap = (T[]) new Object[numElements+1];
      int i = 0;
      while (i < numElements){
        newHeap[i] = heap[i];
        i++;
      }
      heap = newHeap;
    }
  }
  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleUp(int index) {
      //TODO: Implement this method.
    if (index > numElements) throw new IndexOutOfBoundsException();
    int parentIndex = getParentOf(index);

    //compares the current index with its parent and if its larger, it will swap 
    //with its parent and recursivly move up the heap until the base case is met
    if (compareElements(heap[index], heap[parentIndex]) <= 0){
      return;
    } else {
      swapIndices(index, parentIndex);
      bubbleUp(parentIndex);
    }
  }


  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleDown(int index) {
    //TODO: Implement this method.
    if (index > numElements) throw new IndexOutOfBoundsException();
    int leftChildIndex = getLeftChildOf(index);
    int rightChildIndex = getRightChildOf(index);

    if (leftChildIndex > numElements - 1){
      return;
    }
    //essentially checks which child is bigger and sets that as the largrest child
    //You then compare them with the parent of the children and it moves down the tree that.
    int largerChild;
    if (rightChildIndex > numElements - 1) {
      largerChild = leftChildIndex;
    } else if (compareElements(heap[leftChildIndex], heap[rightChildIndex]) < 0){
      largerChild = rightChildIndex;
    } else {
      largerChild = leftChildIndex;
    }

    if (compareElements(heap[index], heap[largerChild]) > 0) {
      return;
    } else {
      swapIndices(index, largerChild);
      bubbleDown(largerChild);
    }
  }      

  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    //boolean isEmpty = false;
      //TODO: Implement this method 
    return numElements == 0;
  }

  //Tests if the queue is a minHeap
  public boolean verifyMinHeap(T[] heap) {
    return verifyHelper(heap,0);
  }

  private boolean verifyHelper(T[] heap, int index){
    if (index > heap.length) {
      return true;
    }
    //check this again
    if (index > 0 && compareElements(heap[getParentOf(index)], heap[index]) <= 0){
      return false;
    } else {
      return verifyHelper(heap, index*2+1) && verifyHelper(heap, index*2+2);
    }
  }


  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int getSize() {
    //int size = -100;
      //TODO: Implement this method.
      return numElements;
  }

  /**
   * Compare method to implement max/min heap behavior. It changes the value of a variable, compareSign, 
   * based on the state of the boolean variable isMaxHeap. It then calls the compare method from the 
   * comparator object and multiplies its output by compareSign.
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * negative int otherwise (if isMaxHeap),
   * return negative int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * positive int otherwise (if ! isMinHeap).
   */
  public int compareElements(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
      //TODO: Implement this method.
      if (isEmpty()) throw new QueueUnderflowException();
      return heap[0];
  }
    

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeueElement() throws QueueUnderflowException{
      //TODO: Implement this method.
    if (isEmpty()) throw new QueueUnderflowException();
    T data = peek();
    heap[0] = heap[numElements-1];
    numElements--;
    bubbleDown(0);
    return data;
    
  }

  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueueElement(T newElement) {
      //TODO: Implement this method.
    if (newElement == null) {
      return;
    }
    expandCapacity();
    heap[numElements] = newElement;
    bubbleUp(numElements);
    numElements++;
  }
}



