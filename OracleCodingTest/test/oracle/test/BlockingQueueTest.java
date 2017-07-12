package oracle.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import oracle.test.BlockingQueue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Provides sufficient test coverage for oracle.test.BlockingQueue class.
 */
@RunWith(JUnit4.class)
public class BlockingQueueTest {
    // implement code here ...
	int inputData = 0;
	
	@Test
	public void testPushPullWithThreadCount01() throws Exception {
		testPushPull(1);
	}
	
	@Test
	public void testPushPullWithThreadCount08() throws Exception {
		testPushPull(8);
	}
	
	 
	@Test
	public void testPushPullWithThreadCount32() throws Exception {
		testPushPull(32);
	}
	
	
	@Test
	public void testPushWithThreadCount01() throws Exception {
		testPush(1);
	}
	
	@Test
	public void testPushWithThreadCount08() throws Exception {
		testPush(8);
	}
	 
	@Test
	public void testPushWithThreadCount32() throws Exception {
		testPush(32);
	}
	
	
	
	@Test
	public void testPushWithMultiInstanceThreadCount32() throws Exception {
		testPushPullMultiInstance(32);
	}
	 
	private void testPush(final int threadCount) throws Exception {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		BlockingQueue<Integer> integerQueue = new BlockingQueue<>(queue);
		
	    Callable<Integer> task = new Callable<Integer>() {
	        @Override
	        public Integer call() {
	        	integerQueue.push(++inputData);
	            return inputData;
	        }
	    };
	    List<Callable<Integer>> tasks = Collections.nCopies(threadCount, task);
	    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
	    List<Future<Integer>> futures = executorService.invokeAll(tasks);
	    List<Integer> resultList = new ArrayList<Integer>(futures.size());
	    // Check for exceptions
	    for (Future<Integer> future : futures) {
	        // Throws an exception if an exception was thrown by the task.
	        resultList.add(future.get());
	    }
	    // Validate the IDs
	    Assert.assertEquals(threadCount, futures.size());
	    List<Integer> expectedList = new ArrayList<Integer>(threadCount);
	    for (int i = 1; i <= threadCount; i++) {
	        expectedList.add(i);
	    }
	    Collections.sort(resultList);
	    Assert.assertEquals(expectedList, resultList);
	}
	
	
	private void testPushPull(final int threadCount) throws Exception {
		inputData = 0;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		BlockingQueue<Integer> integerQueue = new BlockingQueue<>(queue);
		
	    Callable<Integer> pushTask = new Callable<Integer>() {
	        @Override
	        public Integer call() {
	        	integerQueue.push(++inputData);
	            return inputData;
	        }
	    };
	    
	    Callable<Integer> pullTask = new Callable<Integer>() {
	        @Override
	        public Integer call() {
	            return integerQueue.pull();
	        }
	    };
	    List<Callable<Integer>> pushTasks = Collections.nCopies(threadCount/2, pushTask);
	    List<Callable<Integer>> pullTasks = Collections.nCopies(threadCount/2, pullTask);
	    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
	    List<Future<Integer>> futurePush = executorService.invokeAll(pushTasks);
	    List<Future<Integer>> futurePull = executorService.invokeAll(pullTasks);
	    List<Integer> resultPushList = new ArrayList<Integer>(futurePush.size());
	    List<Integer> resultPullList = new ArrayList<Integer>(futurePull.size());
	    
	    // Check for exceptions

	    
	    for (Future<Integer> future : futurePush) {
	        // Throws an exception if an exception was thrown by the task.
	    	resultPushList.add(future.get());
	    }
	    
	    for (Future<Integer> future : futurePull) {
	        // Throws an exception if an exception was thrown by the task.
	    	resultPullList.add(future.get());
	    }
	    
	    Collections.sort(resultPullList);
	    Collections.sort(resultPushList);
	    assertTrue(resultPullList.size() == resultPushList.size());
	    assertTrue(queue.size() ==0);
	}
	
	private void testPushPullMultiInstance(final int threadCount) throws Exception {
		inputData = 0;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		BlockingQueue<Integer> integerQueue1 = new BlockingQueue<>(queue);
		BlockingQueue<Integer> integerQueue2 = new BlockingQueue<>(queue);
		
	    Callable<Integer> pushTask = new Callable<Integer>() {
	        @Override
	        public Integer call() {
	        	integerQueue1.push(++inputData);
	            return inputData;
	        }
	    };
	    
	    Callable<Integer> pullTask = new Callable<Integer>() {
	        @Override
	        public Integer call() {
	            return integerQueue2.pull();
	        }
	    };
	    List<Callable<Integer>> pushTasks = Collections.nCopies(threadCount/2, pushTask);
	    List<Callable<Integer>> pullTasks = Collections.nCopies(threadCount/2, pullTask);
	    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
	    List<Future<Integer>> futurePush = executorService.invokeAll(pushTasks);
	    List<Future<Integer>> futurePull = executorService.invokeAll(pullTasks);
	    List<Integer> resultPushList = new ArrayList<Integer>(futurePush.size());
	    List<Integer> resultPullList = new ArrayList<Integer>(futurePull.size());
	    
	    // Check for exceptions

	    
	    for (Future<Integer> future : futurePush) {
	        // Throws an exception if an exception was thrown by the task.
	    	resultPushList.add(future.get());
	    }
	    
	    for (Future<Integer> future : futurePull) {
	        // Throws an exception if an exception was thrown by the task.
	    	resultPullList.add(future.get());
	    }
	    
	    Collections.sort(resultPullList);
	    Collections.sort(resultPushList);
	    assertTrue(resultPullList.size() == resultPushList.size());
	    assertTrue(queue.size() ==0);
	}
}
