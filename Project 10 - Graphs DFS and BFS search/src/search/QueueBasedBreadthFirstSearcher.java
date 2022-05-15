package search;

import java.util.List;
import java.util.ArrayList;


/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() { // beware of duplicates
        // TODO

		if (solution != null) return solution;

		List<T> path = new ArrayList<T>();
		List<T> visited = new ArrayList<T>();

		boolean found = false;

		T start = searchProblem.getInitialState();

		List<T> q = new ArrayList<T>();

		visited.add(start);
		q.add(start);

		List<Node<T>> nodes = new ArrayList<Node<T>>();
		nodes.add(new Node<T>(start, null));

		while ( !q.isEmpty()) {

			T temp = q.remove(0);

			if ( searchProblem.isGoalState(temp)){
				
				int i = nodes.size() -1;

				while (i >= 0) {
					if (nodes.get(i).val.equals(temp)) break;
					i--;
				}

				path.add(0, temp);
				T parent = nodes.get(i).parent;
				for (int j = i-1; parent!= null && j >= 0; j--) {
					if (nodes.get(j).val.equals(parent)) {
						path.add(0, nodes.get(j).val);
						parent = nodes.get(j).parent;
					}
				}
				found = true;
				break;

				
			} else {

				List<T> succ = searchProblem.getSuccessors(temp);
				for (T x: succ) {
					if (!visited.contains(x)) {
						q.add(x);
						visited.add(x);
						nodes.add(new Node<T>(x, temp));
					}
				}
			}

		}

        if (!found) return null;
		if (isValid(path)) return path;
		return null;
	}



public class Node<T> {

	public T val;
	public T parent;

	public Node(T val, T parent) {
		this.val = val;
		this.parent = parent;
	}

}


	/*private class Node<T> {

		private T elem;
		private Node<T> parent;
		private List<T> next = new ArrayList<T>();

		public Node (T elem, Node<T> parent) {
			this.elem = elem;
			this.parent = parent;
		}

		public void addChild (T child) {
			next.add(child);
		}

		public T getElem (){ return elem;}
		public Node<T> getParent (){return parent;}
		public List<T> getChildren(){return next;}

	}
	*/

}
