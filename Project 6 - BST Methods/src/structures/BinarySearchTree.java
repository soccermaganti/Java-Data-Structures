package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int getSize() {
		// TODO
		return treeSize(root);
	}

	private int treeSize(BSTNode<T> node) {
		//helper method that iterates through the left and right nodes and increments the 
		//count each time
		if (node == null){
			return 0;
		} else {
			return 1 + treeSize(node.getLeft()) + treeSize(node.getRight());
		}
	} 

	public boolean contains(T t) {
		// TODO
		//checks if the tree contains the element, if not, returns false.
		if (t == null) throw new NullPointerException();
		return getElement(t) != null;
	}

	public boolean removeElement(T t) {
		// TODO
		BSTNode<T> node = null;
		if (t == null) {
			throw new NullPointerException();
		} else if (root == null){
			return false;
		} 
		else if (t.compareTo(root.getData())== 0) {
			root = getNewPointer(root);
		} else  {
			node = removeElemHelper(root, t);
		}
		if (node == null) {
			return true;
		} else {
			return false;
		}
	}
	//part of remove method
	private BSTNode<T> removeElemHelper(BSTNode<T> node, T object){
		BSTNode<T> result = null, child = null, replacement = null;
		if (node!=null) {
			if (object.compareTo(node.getData()) < 0){
				child = node.getLeft();
				if(child != null && (object.compareTo(child.getData()) == 0)){
					result = child;
					replacement = getNewPointer(child);
					if (replacement == null) {
						node.setLeft(null);
					} else 
						node.setLeft(replacement);
				}
			} else {
				result = removeElemHelper(child, object);
			}
		} else if (object.compareTo(node.getData())>0) {
			child = node.getRight();
			if (object.compareTo(node.getData()) == 0 && child != null){
					result = child;
					replacement = getNewPointer(child);
					if (replacement == null) {
						node.setRight(null);
					} else {
						node.setRight(replacement);
					}
			} else {
				result = removeElemHelper(child, object);
			}
		}
		return result;
	}

	//part of remove method
	private BSTNode<T> getNewPointer(BSTNode<T> node){
		BSTNode<T> result = null;
		if (node.getLeft() == null && node.getRight()== null){
			result = null;
		} else if (node.getLeft() == null && node.getRight() != null){
			result = node.getRight();
		} else if (node.getLeft() != null && node.getRight() == null ){
			result = node.getLeft();
		} else {
			result = inorderPredecessor(node.getLeft(),node);
			result.setLeft(node.getLeft());
			result.setRight(node.getRight());
		}
		return result;
	}

	//part of remove method
	private BSTNode<T> inorderPredecessor(BSTNode<T> node, BSTNode<T> parent){
		BSTNode<T> child = node.getRight();
		if (child == null) {
			if (parent.getLeft() == null){
				return node;
			} else {
				parent.setRight(null);
				return node;
			}
		} else {
			return inorderPredecessor(child, node);
		}
	} 

	public T getHighestValueFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValueFromSubtree(node.getRight());
		}
	}

	public T getLowestValueFromSubtree(BSTNode<T> node) {
		// TODO
		if (node.getLeft() == null) {
			return node.getData();
		} else {
			return getLowestValueFromSubtree(node.getLeft());
		}
	}

	//changed from private
	public BSTNode<T> removeRightmostFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmostFromSubtree(node.getRight()));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	public BSTNode<T> removeLeftmostFromSubtree(BSTNode<T> node) {
		// TODO
		if (node.getLeft() == null) { 
			return node.getRight();
		} else {
			node.setLeft(removeLeftmostFromSubtree(node.getLeft()));
			if (node.getLeft() != null) {
				node.getLeft().setParent(node);
			}
			return node;
		}
	}

	public T getElement(T t) {
		// TODO
		if (t == null) throw new NullPointerException();
		return getHelper(t, root);
	}

	private T getHelper(T t, BSTNode<T> node){
		//using the BST property that the left must be smaller and the right must be greater
		//implemented it by comparing the data and iterating left and right until I reach the 
		//node containing the data. If its not there, it returns null;
		if (node == null) return null;
		T data = node.getData();
		if (t.equals(data)){
			return node.getData();
		} else if (t.compareTo(data) < 0) {
			return getHelper(t, node.getLeft());
		} else return getHelper(t, node.getRight());
	}

	public void addElement(T t) {
		// TODO
		//checks if root is empty and if not, calls the add helper method.
		if (t == null) throw new NullPointerException();
		BSTNode<T> newNode =  new BSTNode<T>(t, null, null);
		if (root == null){
			root = newNode;
			newNode.setLeft(null);
			newNode.setRight(null);
		} else {
		addHelper(root,newNode);
		}
	}

	private void addHelper(BSTNode<T> curNode, BSTNode<T> newNode) {
		//Uses BST property to insert the new node in the correct area
		if (curNode == null) {
			return;
		}
		if (newNode.getData().compareTo(curNode.getData()) < 0){
			if (curNode.getLeft() == null){
				curNode.setLeft(newNode);
			} else {
				addHelper(curNode.getLeft(), newNode);
			}
		}else {
			if (curNode.getRight() == null){
				curNode.setRight(newNode);
			} else {
				addHelper(curNode.getRight(), newNode);
			}
		}
	}
	
	@Override
	public T getMin() {
		// TODO
		if (root == null) return null;
		return getLowestValueFromSubtree(root);
	}

	@Override
	public T getMax() {
		// TODO
		if (root == null) return null;
		return getHighestValueFromSubtree(root);
	}

	@Override
	public int height() {
		// TODO
		if (isEmpty()) return -1;
		return treeHeight(root);
	}

	private int treeHeight(BSTNode<T> node) {
		if (node == null) return -1;
		int leftHeight = treeHeight(node.getLeft());
		int rightHeight = treeHeight(node.getRight());
		return 1 + Math.max(leftHeight,rightHeight);
	}

	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderIteratorHelper(queue,root);
		return queue.iterator();
	}

	private void preorderIteratorHelper(Queue<T> queue,BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderIteratorHelper(queue, node.getLeft());
			preorderIteratorHelper(queue, node.getRight());
		}
	}

	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		//checks if two seperate trees have the same values and structure as each other
		if (other == null) throw new NullPointerException();
		return equalsHelper(root,other.getRoot());
	}

	private boolean equalsHelper(BSTNode<T> one, BSTNode<T> two){
		if (one == null && two == null) {
			return true;
		} else if (one == null || two == null) {
			return false;
		} else {
			if (!one.getData().equals(two.getData())) {
				return false;
			} else {		
				return equalsHelper(one.getLeft(), two.getLeft()) && equalsHelper(one.getRight(), two.getRight());
			}
		}
	}

	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		//checks if two seperate trees contain the same values even if they have different shapes
		if (other == null) throw new NullPointerException();
		Iterator<T> iterator = inorderIterator();
		Iterator<T> iterator2 = other.inorderIterator();
		while (iterator.hasNext() != false && iterator2.hasNext() != false){
			if (iterator.next() != iterator2.next()){
				return false;
			}
		}
		if (iterator.hasNext() == false && iterator2.hasNext() != false){
			return false;
		} else if (iterator.hasNext() != false && iterator2.hasNext() == false){
			return false;
		}
		return true;
	}

	@Override
	public int countRange(T min, T max) {
    	// TODO
		//Returns the number of elements in the tree that 
	 	// has a value larger than min and smaller than max.
		return countRangeHelper(root,min,max);
	
	}

	private int countRangeHelper(BSTNode<T> node, T min, T max){
		int count = 0;
		if (node == null){
			count += 0;
			return count;
		}
		T elem = node.getData();
		if (elem.compareTo(min) > 0 && elem.compareTo(max) < 0){
			count += 1;
			return count + countRangeHelper(node.getLeft(), min, max) + countRangeHelper(node.getRight(), min, max);
		} else if (elem.compareTo(min) <= 0) {
			return countRangeHelper(node.getRight(), min, max);
		} else {
			return countRangeHelper(node.getLeft(),min,max);
		}
	}

	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.addElement(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.removeElement(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.addElement(r);
		}
		System.out.println(tree.getSize());
		System.out.println(tree.height());
		System.out.println(tree.countRange("a", "g"));
		System.out.println(tree.countRange("c", "f"));
	}
}