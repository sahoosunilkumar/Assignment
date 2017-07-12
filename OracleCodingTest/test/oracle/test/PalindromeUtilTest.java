package oracle.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Provides sufficient test coverage for oracle.test.PalindromeUtil class.
 */
public class PalindromeUtilTest {
    // implement code here ...
	@Test
	public void testPalindromForNull() throws Exception{
		assertFalse(oracle.test.PalindromeUtil.isPalindrome(null));
	}
	
	@Test
	public void testPalindromForValidLowercaseString() throws Exception{
		assertTrue(oracle.test.PalindromeUtil.isPalindrome("aabaa"));
	}
	
	@Test
	public void testPalindromForValidUpperString() throws Exception{
		assertTrue(oracle.test.PalindromeUtil.isPalindrome("AABAA"));
	}
	
	@Test
	public void testPalindromForValidMixedString() throws Exception{
		assertTrue(oracle.test.PalindromeUtil.isPalindrome("AaBAA"));
	}
	
	@Test
	public void testPalindromForValidStringWithSpace() throws Exception{
		assertTrue(oracle.test.PalindromeUtil.isPalindrome("A   aBAA"));
		assertTrue(oracle.test.PalindromeUtil.isPalindrome("AaB   A A"));
	}
	
	@Test
	public void testPalindromForInValidString() throws Exception{
		assertFalse(oracle.test.PalindromeUtil.isPalindrome("ABCAA"));
	}
}
