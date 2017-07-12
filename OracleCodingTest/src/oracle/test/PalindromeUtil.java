package oracle.test;

/**
 * Checks if a string is a palindrome.
 *
 * Palindrome is a word, phrase or sentence that reads the same backward or
 * forward. For example, the following string is a palindrome:
 * "Madam, I'm Adam."
 */
public class PalindromeUtil {
	/**
	 * Checks whether passed String is a palindrome or not.
	 *
	 * Requirements: - Limit the amount of additional consumed memory to O(1). -
	 * Limit complexity to O(n).
	 *
	 * @return true if passed string is palindrome, false - otherwise
	 */
	public static boolean isPalindrome(String str) {
		if(str == null){
			return false;
		}
		
		int startCounter = 0;
		int endCounter = str.length() - 1;

		while (startCounter < endCounter) {
			while (isEmpty(str.charAt(startCounter))) {
				startCounter++;
				if (startCounter >= endCounter) {
					return true;
				}
			}
			while (isEmpty(str.charAt(endCounter))) {
				endCounter--;
				if (startCounter >= endCounter) {
					return true;
				}
			}
			if (toLowerCase(str.charAt(startCounter)) != toLowerCase(
					str.charAt(endCounter))) {
				return false;
			}
			startCounter++;
			endCounter--;
		}
		return true;
	}

	/**
	 * checks for empty string
	 * @param c
	 * @return
	 */
	private static boolean isEmpty(char c) {
		return c == ' ';
	}

	/**
	 * converts to lowercase
	 * @param c
	 * @return
	 */
	private static char toLowerCase(char c) {
		return Character.toLowerCase(c);
	}
}
