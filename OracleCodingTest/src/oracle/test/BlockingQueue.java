package oracle.test;

import java.util.Queue;

/**
 * Provides waiting thread-safe access to a java.util.Queue instance.
 *
 * Requirements:
 * - Usage of API from java.util.concurrent packageis prohibited.
 * - Limit the amount of additional consumed memory to O(1).
 * - The implementation is supposed to be acceptable for usage in a highly
 * multi-thread environment.
 *
 * Useful tips a.k.a. common pitfalls:
 * - Please note that you do not need to implement java.util.Queue.
 * - Readiness to accept or provide elements is solely dependent on the
 * underlying queue. Any additional queue capacity limitations break contract
 * defined in the javadoc.
 */
public class BlockingQueue<E> {
    // implement code here ...
    /**
     * @param queue The underlying "wrapped" queue.
     */
	private Queue<E> queue;
    public BlockingQueue(Queue<E> queue) {
        // implement code here ...
    	this.queue = queue;
    }

    /**
     * Inserts the specified element into the underlying queue, waiting if
     * necessary for the underlying queue to be ready to accept new elements.
     * @param e the element to insert.
     */
    public void push(E e) {
        // implement code here ...
    	synchronized (queue) {
    		queue.add(e);
		}
    }

    /**
     * Retrieves and removes the head of the underlying queue, waiting if
     * necessary until it is capable of providing an element.
     * @return the retrieved element
     */
    public E pull() {
        // implement code here ...
    	synchronized (queue) {
    		return queue.poll();
		}
    }
}
