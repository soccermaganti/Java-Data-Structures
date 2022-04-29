package app;

import java.util.Comparator;
import java.util.ArrayList;


public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO sort
		if (list.size() <= 1) {
			return list;
		}  

		Quicksort(0, list.size() - 1);
		return list;
	}

		public void Quicksort(int lowIndex, int highIndex) {
			if ( lowIndex >= highIndex) {
				return;
			}
			
			int lowEndIndex = Partition(lowIndex, highIndex);
			Quicksort(lowIndex, lowEndIndex);
			Quicksort(lowEndIndex + 1, highIndex);
		}
	

		private int Partition(int lowIndex, int highIndex) {
			int pivot = lowIndex + (highIndex - lowIndex) / 2;
			// int pivot = list.compare(lowIndex, highIndex, comparator);
			boolean done = false;
			while (done != true) {
			   while (lowIndex != pivot && list.compare(lowIndex, pivot, comparator) < 0) {
				   lowIndex += 1;
			   }
   
			   while (highIndex != pivot && list.compare(highIndex,pivot, comparator) > 0) {
					highIndex -= 1;
			   }
   
			    if (lowIndex >= highIndex) {
					done = true;
					if (highIndex >= pivot) {
						list.swap(highIndex, pivot);
					} else if (lowIndex <= pivot ){
						list.swap(lowIndex, pivot);
						highIndex = lowIndex;   
					}
				} else {
					list.swap(lowIndex, highIndex);

					 if (lowIndex == pivot) {
						pivot = highIndex;
					} else if (highIndex == pivot) {
						pivot = lowIndex;
					} 

					lowIndex += 1;
					highIndex -= 1;
				}
			}
			return highIndex;
		}
}

