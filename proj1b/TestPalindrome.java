import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("byb"));
    }

    @Test
    public void testisPalindromeNew(){
        OffByOne cc=new OffByOne();
        assertFalse(palindrome.isPalindrome("cat",cc));
        assertTrue(palindrome.isPalindrome("acb",cc));
    }
}
