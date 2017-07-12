package oracle.test;

import java.util.Iterator;

/**
 * "Flattens" nested sub-iterators into an iterator in a depth-first manner.
 *
 * Requirements:
 *  - Limit the amount of additional consumed memory to O(1).
 *
 * Example: an iterator {{1,2},{3},{4,5}} which has three sub-iterators with
 * 2, 1 and 2 elements correspondingly, presents values in this order:
 * {1, 2, 3, 4, 5}
 */
public class IteratorFlattener<E> implements Iterator<E> {
    // implement code here ...
	private Iterator<E> current;
    private Iterator<Iterator<E>> root;
    public IteratorFlattener(Iterator<Iterator<E>> root) {
       // implement code here ...
    	if (root == null) throw new NullPointerException("input is is null");
        this.root = root;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     *
     * @return {@code true} if the iteration has more elements
     */
    public boolean hasNext() {
        // implement code here ...
    	initCurrentNode();
        return (current != null && current.hasNext());
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public E next() {
        // implement code here ...
    	initCurrentNode();
    	 return current.next();
    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation). This method can be called
     * only once per call to {@link #next}.
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
     */
    
    public void remove() {
        // impement code here ...
    	if (current != null) {
            current.remove();
        }
    }
    
    
    private Iterator<E> findNext() {
        while (root.hasNext()) {
            current = root.next();
            if (current.hasNext()) return current;
        }
        return null;
    }
    
    private void initCurrentNode(){
    	if (current == null || !current.hasNext()) {
            current = findNext();
        }
    }
    
}

