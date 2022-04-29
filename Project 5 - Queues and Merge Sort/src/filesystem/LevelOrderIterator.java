package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;
//import java.util.Queue;



/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem.
 */
public class LevelOrderIterator extends FileIterator<File> {
	Queue<File> newQueue = new Queue<File>();
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
			//Checks if rootnode exists or is equal to null then throws the exception
			if (!rootNode.exists() || rootNode == null) throw new FileNotFoundException();
			//enqueues rootNode onto newQueue 
			newQueue.enqueue(rootNode);
			
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
			//Checks if newQueue root nodes children are not empty
			return !newQueue.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
			//checks is newQueue is empty
			if (newQueue.isEmpty()) throw new NoSuchElementException();
			//creates a new File instance that is created from items dequeued from newQueue
			File iterator = newQueue.dequeue();
			// Tests whether the file is a directory.
			if (iterator.isDirectory()){
				//creates a file Array from the files in the iterator
				File[] directoryFiles = iterator.listFiles();
				//Sorts directory files in a level order traversal fashion
				Arrays.sort(directoryFiles);
				for (File files1 : directoryFiles){
					newQueue.enqueue(files1);
				}
			}
			return iterator;
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
