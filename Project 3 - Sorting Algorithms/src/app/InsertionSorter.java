package app;

import java.util.Comparator;


public class InsertionSorter<T> extends AbstractSorter<T> {

	public InsertionSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO sort
		//Tests if list.size is smaller or equal to 1 and then just returns the list
		if (list.size() <= 1) {
			return list;
		}
		int i = 0;
		int j = 0;
		//for loop to iterate through list
		for (i = 1; i < list.size(); i++) {
			j = i;
			//while loop to compare j-1 and j and then swap them and then decrement j.
			while ( j > 0 && list.compare(j-1,j, comparator) > 0)	{
				list.swap(j-1,j);
				--j;
			}
		}
	//returns the list		
	return list;
	}
}
