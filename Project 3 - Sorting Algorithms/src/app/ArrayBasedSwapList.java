package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class ArrayBasedSwapList<T> implements SwapList<T> {
	private final ArrayList<T> arrayList;
	private int swaps = 0;
	private int comparisons = 0;

	public ArrayBasedSwapList(T[] array) {
		arrayList = new ArrayList<T>(Arrays.asList(array));
	}

	public ArrayBasedSwapList(Collection<T> coll) {
		arrayList = new ArrayList<T>(coll);
	}

	@Override
	public int compare(int index1, int index2, Comparator<T> comparator) {
		comparisons++;
		return comparator.compare(arrayList.get(index1), arrayList.get(index2));
	}

	@Override
	public void swap(int index1, int index2) {
		swaps++;
		T temp = arrayList.get(index1);
		arrayList.set(index1, arrayList.get(index2));
		arrayList.set(index2, temp);
	}

	@Override
	public int size() {
		return arrayList.size();
	}

	// Returns true iff the list is sorted in ascending order according to the given comparator.
	@Override
	public boolean isSorted(Comparator<T> comparator) {
		// TODO isSorted
		//Tests the arrayList size if they are 1 or zero and automatically returns true
		if (arrayList.size() == 1 || arrayList.size() == 0) {
			return true;
		}
		// for loop to iterate through the arrayList size
		for (int i = 1; i < arrayList.size(); i++) {
			//if statement tests if i-1 if less than i and if it is, it'll return false
			if (comparator.compare(arrayList.get(i-1),(arrayList.get(i))) > 0) {
				return false;
			}
		}
		return true;
	}
 
	public int getSwaps() {
		return swaps;
	}

	public int getComparisons() {
		return comparisons;
	}

	// Returns a whole-number percentage of elements sorted. Traverses the list once and tallys all
	// correct ordered consecutive pairs (ex: [1, 2] is correct but [2, 1] is not).  Then divides 
	// this by the number of comparisons completed.
	// Ex: [1, 2, 3, 4] returns 100 (3 correctly sorted pairs ( [1,2], [2,3], [3,4] ) divided by 3 total comparisons)
	// Ex: [4, 3, 2, 1] returns 0
	// Ex: [4, 1, 2, 3] returns 75
	@Override
	public int scoreList(Comparator<T> comparator) {
		// TODO scoreList
		// Tests arrayLists size to see if they are 0 or 1 and automatically give it a 100%
		if (arrayList.size() == 1 || arrayList.size() == 0) {
			return 100;
		}
		int count = 0;
		// for loop to iterate through arrayList 
		for (int i = 1; i < arrayList.size(); i++) {
			//compares i -1 and i and if its true, the count will be incremented by 1 
			if (comparator.compare(arrayList.get(i-1), arrayList.get(i)) < 0) {
				count++;
			}
		}
		// returns the percentage in a int value using a type converter (int)
		return (int)(((count * 1.0) / (arrayList.size() - 1)) * 100);
	}
	
	@Override
	public String toString() {
		return arrayList.toString();
	}
}
