	package structures;

	public class AVLTree <T extends Comparable<T>> implements BSTInterface<T> {
		protected BSTNode<T> root;
		private int size;

		public AVLTree() {
			root = null;
			size = 0;
		}

		public boolean isEmpty() {
			// DO NOT MODIFY
			return root == null;
		}

		public int size() {
			// DO NOT MODIFY
			return size;
		}

		public BSTNode<T> getRoot() {
			// DO NOT MODIFY
			return root;
		}
		
		public void printTree() {
			System.out.println("------------------------");
			if (root != null) root.printSubtree(0);
		}

		public boolean remove(T element) {
			// Do not need to implement this method.
			return false;
		}

		public T get(T element) {
			// Do not need to implement this method.
			return null;
		}

		public int height() {
			return height(this.root);
		}

		public int height(BSTNode<T> node) {
			// return the node height
			return node != null ? node.getHeight() : -1;
		}
		
		public void updateHeight(BSTNode<T> node) {
			// TODO: update node height to 1 + the maximal height between left and right subtree
		int leftHeight = -1;
		if (node.getLeft() != null) {
			leftHeight = node.getLeft().getHeight();
		}
		int rightHeight = -1;
		if (node.getRight() != null){
			rightHeight = node.getRight().getHeight();
		}
		node.setHeight(Math.max(leftHeight, rightHeight) + 1);
		}
		
		//Zybooks
		public int balanceFactor(BSTNode<T> node) {
			// TODO: compute the balance factor by substracting right subtree height by left subtree height
			int leftHeight = -1;
			if (node.getLeft() != null){
				leftHeight = node.getLeft().getHeight();
			}
			int rightHeight = -1;
			if (node.getRight() != null){
				rightHeight = node.getRight().getHeight();
			}
			return rightHeight - leftHeight;
		}

		//Zybooks
		public BSTNode<T> rotateLeft(BSTNode<T> node) {
			// TODO: implement left rotation algorithm
			BSTNode<T> rightLeftChild = node.getRight().getLeft(); 
			if (node.getParent() != null)
			   AVLTreeReplaceChild(node.getParent(), node, node.getRight());
			else {
			   root = node.getRight(); 
			   root.setParent(null);
			}
			AVLTreeSetChild(node.getRight(), "left", node);
			AVLTreeSetChild(node, "right", rightLeftChild);
			//Campus wire goated for this
			return node.getParent();
		}
		
		//Zybooks
		public BSTNode<T> rotateRight(BSTNode<T> node) {
			// TODO: implement right rotation algorithm
			BSTNode<T> leftRightChild = node.getLeft().getRight();
			if (node.getParent() != null) {
				AVLTreeReplaceChild(node.getParent(),node,node.getLeft());
			} else {
				root = node.getLeft();
				root.setParent(null);
			}
			AVLTreeSetChild(node.getLeft(),"right", node);
			AVLTreeSetChild(node,"left", leftRightChild);
			//Thx campus wire
			return node.getParent();
		}

		//Zybooks
		private boolean AVLTreeSetChild(BSTNode<T> parent, String whichChild, BSTNode<T> child){
			if (whichChild != "left" && whichChild != "right"){
				return false;
			} 
			if (whichChild == "left"){
				parent.setLeft(child);
			} else {
				parent.setRight(child);
			}
			if (child != null){
				child.setParent(parent);
			}
			updateHeight(parent);
			return true;
		}

		//Zybooks
		private boolean AVLTreeReplaceChild(BSTNode<T> parent, BSTNode<T> currentChild, BSTNode<T>newChild){
			if (parent.getLeft() == currentChild){
				return AVLTreeSetChild(parent, "left", newChild);
			} else if (parent.getRight() == currentChild){
				return AVLTreeSetChild(parent, "right", newChild);
			}
			return false;
		}

		// When inserting a new node, updating the height of each node in the path from root to the new node.
		// Check the balance factor of each updated height and run rebalance algorithm if the balance factor
		// is less than -1 or larger than 1 with following algorithm
		// 1. if the balance factor is less than -1
		//    1a. if the balance factor of left child is less than or equal to 0, apply right rotation
	    //    1b. if the balance factor of left child is larger than 0, apply left rotation on the left child,
		//        then apply right rotation
		// 2. if the balance factor is larger than 1
		//    2a. if the balance factor of right child is larger than or equal to 0, apply left rotation
	    //    2b. if the balance factor of right child is less than 0, apply right rotation on the right child,
		//        then apply left rotation
		

		//Created helper method using zybooks code essentially
		public void add(T t) {
			// TODO
			BSTNode<T> newNode = new BSTNode<T>(t, null, null);
			AVLTreeInsert(newNode);
		}

		private void AVLTreeInsert(BSTNode<T> node){
			if (root == null){
				size++;
				root = node;
				node.setParent(null);
				return;
			}

			BSTNode<T> curr = root;
			while (curr != null){
				if (node.getData().compareTo(curr.getData()) < 0){
					if (curr.getLeft() == null){
						curr.setLeft(node);
						node.setParent(curr);
						//curr.getLeft().setParent(curr);
						curr = null;
					} 
					else {
						curr = curr.getLeft();
					}
				} 
				else {
					if (curr.getRight() == null){
						curr.setRight(node);
						node.setParent(curr);
						//curr.getLeft().setParent(curr);
						curr = null;
					} 
					else {
						curr = curr.getRight();
					}
				}
			}
			size++;
			node = node.getParent();
			while (node != null){
				AVLTreeRebalance(node);
				node = node.getParent();
			}
		}
		
// When inserting a new node, updating the height of each node in the path from root to the new node.
		// Check the balance factor of each updated height and run rebalance algorithm if the balance factor
		// is less than -1 or larger than 1 with following algorithm
		// 1. if the balance factor is less than -1
		//    1a. if the balance factor of left child is less than or equal to 0, apply right rotation
	    //    1b. if the balance factor of left child is larger than 0, apply left rotation on the left child,
		//        then apply right rotation
		// 2. if the balance factor is larger than 1
		//    2a. if the balance factor of right child is larger than or equal to 0, apply left rotation
	    //    2b. if the balance factor of right child is less than 0, apply right rotation on the right child,
		//        then apply left rotation
	
	// Used above algorithm which was very easy to understand
	private BSTNode<T> AVLTreeRebalance(BSTNode<T> node) {
		updateHeight(node);
		if (balanceFactor(node) < -1){
			if (balanceFactor(node.getLeft()) <= 0){
				rotateRight(node);
			} else if (balanceFactor(node.getLeft()) > 0){
				rotateLeft(node.getLeft());
				rotateRight(node);
			}
		} else if (balanceFactor(node) > 1){
			if (balanceFactor(node.getRight()) >= 0){
				rotateLeft(node);
			} else if (balanceFactor(node.getRight()) < 0 ){
				rotateRight(node.getRight());
				rotateLeft(node);
			}
		}
		return node;
	}
}
