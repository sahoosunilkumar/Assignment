package oracle.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides sufficient test coverage for oracle.test.IteratorFlattener class.
 */
public class IteratorFlattenerTest {
    // implement code here ...
	
	@Test
	public void testNextForMultiLevel() throws Exception{
		//pre
		int[] requiredOutput = {1, 2, 3, 4, 5};
		Iterator<Integer> i1 = Arrays.asList(new Integer[]{1}).iterator();
	    Iterator<Integer> i2 = Arrays.asList(new Integer[]{2,3}).iterator();
	    Iterator<Integer> i3 = Arrays.asList(new Integer[]{4,5}).iterator();
	    List<Iterator<Integer>> allIterators = new ArrayList<>();
	    allIterators.add(i1);
	    allIterators.add(i2);
	    allIterators.add(i3);
	    
	    Iterator<Iterator<Integer>> testIterator = allIterators.iterator();
	    Iterator<Integer> myIterator = new IteratorFlattener<Integer>(testIterator);
	    
	    int i = 0;
	    while(myIterator.hasNext()){
	    	assertTrue(myIterator.next() == requiredOutput[i]);
	    	i++;
	    }
	}
	
	@Test
	public void testNextForSingleLevelIterator() throws Exception{
		//pre
		int[] requiredOutput = {1, 2, 4};
		Iterator<Integer> i1 = Arrays.asList(new Integer[]{1}).iterator();
	    Iterator<Integer> i2 = Arrays.asList(new Integer[]{2}).iterator();
	    Iterator<Integer> i3 = Arrays.asList(new Integer[]{4}).iterator();
	    List<Iterator<Integer>> allIterators = new ArrayList<>();
	    allIterators.add(i1);
	    allIterators.add(i2);
	    allIterators.add(i3);
	    
	    Iterator<Iterator<Integer>> testIterator = allIterators.iterator();
	    Iterator<Integer> myIterator = new IteratorFlattener<Integer>(testIterator);
	    
	    int i = 0;
	    while(myIterator.hasNext()){
	    	assertTrue(myIterator.next() == requiredOutput[i]);
	    	i++;
	    }
	}
	
	@Test(expected = NullPointerException.class)
	public void testIteratorForNullData() throws Exception{
		//pre
		new IteratorFlattener<Integer>(null);
	}
	
	@Test
	public void testHasNextForNoData() throws Exception{
		//pre
		Iterator<Integer> i1 = Arrays.asList(new Integer[]{}).iterator();
	    List<Iterator<Integer>> allIterators = new ArrayList<>();
	    allIterators.add(i1);
	    
	    Iterator<Iterator<Integer>> testIterator = allIterators.iterator();
	    IteratorFlattener<Integer> myIterator = new IteratorFlattener<Integer>(testIterator);
	    assertFalse(myIterator.hasNext());
	}
}
