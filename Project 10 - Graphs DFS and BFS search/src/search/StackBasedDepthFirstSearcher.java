package search;

import java.beans.Visibility;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
		if (solution != null) {
			return solution;
		}

		Stack<T> path = new Stack<T>();
		T state = searchProblem.getInitialState();
		if (searchProblem.isGoalState(state)){
			path.add(state);
			visitedStates.add(state);
			System.out.println(state);
			return path;
		}

		path.add(state);
		visitedStates.add(state);
		T newNode = null;
		while (!path.isEmpty()){
			newNode = getNextUnvisitedNeighbor(path.peek());
			if (searchProblem.isGoalState(newNode)){
				path.add(newNode);
				visitedStates.add(newNode);
				System.out.println(newNode);
				break;
			}
			if (newNode == null) {
				path.pop();
			} else {
				visitedStates.add(newNode);
				path.add(newNode);
				System.out.println(newNode);
			}
		}

		if (path.size() > 0) {
			if (!isValid(path)) {
				throw new RuntimeException("searcher should never find an invalid solution!");
			}
		}
		return path;
	}

	private T getNextUnvisitedNeighbor(T next){
		//From RecursiveDepthFirstSearch ez
		for (T neighbor : searchProblem.getSuccessors(next)) {
			if (!visitedStates.contains(neighbor)) {
				return neighbor;
			} 
		}
		return null;
	}
}
