package app;
 
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
 
public class RecursiveList<T> implements ListInterface<T> {
 
 private int size;
 private Node<T> head = null;
 private Node<T> tail = null;
 
 public RecursiveList() {
   this.head = null;
   this.size = 0;
   this.tail = null;
 }
 
 public RecursiveList(Node<T> first) {
   this.head = first;
   this.size = 1;
   this.tail = first;
 }
 
 @Override
 public int size() {
   return size;
 }
 
 @Override
 public void insertAtBeginning(T elem) {
     //TODO: Implement this method.
   if (elem == null) throw new NullPointerException();
   Node<T> newNode = new Node(elem,head);
   if (head == null && tail == null) {
     head = newNode;
     tail = newNode;
   }
   head = newNode;
   size++; 
 }
 
 @Override
 public void insertAtEnd(T elem) {
     //TODO: Implement this method.
   if (elem == null) throw new NullPointerException();
   Node<T> newNode = new Node(elem,tail);
   if (tail == null && head == null) {
     head = newNode;
     tail = newNode;
   }
   else {
    tail.setNext(newNode);
    tail = newNode;
   }
   size++;
 }
 
 @Override
 public void insertAt(int index, T elem) {
   //TODO: Implement this method.
   //Node<T> newNode = new Node(elem,newNode);
   if (elem == null){ 
     throw new NullPointerException();
   }
   if (index < 0 || index > size) {
     throw new IndexOutOfBoundsException();
   }
   if (index == 0) {
     insertAtBeginning(elem);
   }
   else if (index == size()) {
     insertAtEnd(elem);
   } else {
   Node<T> curNode = new Node<T>(elem,findNode(index, head));
   Node<T> prevNode = findNode(index-1, head);
   prevNode.setNext(curNode);
   curNode.setNext(curNode.getNext());
   size++;
   }
 }
 
 @Override
 public T removeFirst() {
   T removedItem = null;
     //TODO: Implement this method
    
     if (isEmpty()) throw new IllegalStateException();
     size--;
     Node<T> curr = head;
     Node<T> sucNode = head.getNext();
     removedItem = curr.getData();
     head = sucNode;
 
     if(sucNode == null){
       tail = null;
     }
     return removedItem;
 }
 
 @Override
 public T removeLast() {
   T removedItem = null;
     //TODO: Implement this method.
   if(isEmpty()) throw new IllegalStateException();
   removedItem = tail.getData();
   if (size <= 1){
     head = null;
     tail = null;
   } else {
      tail = findNode(size-2,head);
   }
   size--;
   return removedItem;
 }
 
 @Override
 public T removeAt(int i) {
   T removedItem = null;
     //TODO: Implement this method. 
     if (isEmpty()) throw new IndexOutOfBoundsException();
     if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
     if (size <= 1){
       head = null;
       tail = null;
     } else {
     Node<T> curr = findNode(i, head);
     Node<T> prevNode = findNode(i-1,head);
     removedItem = curr.getData();
     prevNode.setNext(curr.getNext());
   }
   size--;
   return removedItem;
 }
 
 @Override
 public void removeElement(T elem) {
     //TODO: Implement this method.
   if (elem == null) throw new NullPointerException();
   if (indexOf(elem) == -1) {
     throw new ItemNotFoundException();
   } else {
     removeAt(indexOf(elem));
     size--;
   }
 }
 
 @Override
 public T getFirst() {
   T item = null;
     //TODO: Implement this method.
   if (isEmpty()) throw new IllegalStateException();
   item = head.getData();
   return item;
 }
 
 @Override
 public T getLast() {
   T item = null;
     //TODO: Implement this method.
   if (isEmpty()) throw new IllegalStateException();
   item = tail.getData();
   return item;
 }
 
 @Override
 public T getAt(int i) {
   T item = null;
     //TODO: Implement this method.
   if (isEmpty()) throw new IllegalStateException();
   if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
   if (i == 0) {
     return getFirst();
   }
   if (i == size) {
     return getLast();
   }
   Node<T> curr = findNode(i, head);
   item = curr.getData();
   return item;
 }
 
 private final Node<T> findNode(int distance, Node<T> curr) {
   if (distance == 0) {
     return curr;
   } else {
     return findNode(distance - 1, curr.getNext());
   }
 }
 
 @Override
 public int indexOf(T elem) {
     //TODO: Implement this method.
   if (elem == null) throw new NullPointerException();
   return indexOfHelper(elem, 0, head);
 }
 
 //if the node contains the element, it will return the index of it
 private int indexOfHelper(T elem, int index, Node<T> node) {
   if (node == null) {
     return -1;
   } else if (node.getData().equals(elem)){
     return index;
   } else {
   //if not if will recursively call itself til it does
    return indexOfHelper(elem, index + 1, node.getNext());
   }
 }
 
 @Override
 public boolean isEmpty() {
   boolean empty = false;
     //TODO: Implement this method.
     if (head == null && tail == null ){
       empty = true;
     }
   return empty;
 }
 
 public Iterator<T> iterator() {
   Iterator<T> iter = null;
     //TODO: Implement this method.
     iter = new LinkedNodeIterator<>(head);
     return iter;
 }
}
 

