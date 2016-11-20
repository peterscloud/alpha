package com.peterscloud.strings;

import java.nio.CharBuffer;
import org.junit.*;
import static org.junit.Assert.*;

public class PalindromeTest {
  
  @Test
  public void multiple() {
    String[] strings = new String[]{
      "a", "aa", "aba", "abba", "ababa", "ab", "abb", "aab", "aabb"
    };
    
    boolean[] valid = new boolean[]{
      true, true, true, true, true, false, false, false, false
    };
    
    for (int i = 0; i < strings.length; i++) {
      testIsValid(CharBuffer.wrap(strings[i]), valid[i]);
    }

    
  }

  
  
  public void testIsValid(CharBuffer string, boolean valid) {
    Palindrome palindrome = new Palindrome(string);
    assertEquals("valid: " + string, valid, palindrome.isValid());
  }

}
