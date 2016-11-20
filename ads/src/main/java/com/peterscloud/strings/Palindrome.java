package com.peterscloud.strings;

import java.nio.CharBuffer;

/**
 * <p>A palindrome is a string that reads the same forwards or backwards.</p>
 * <p>Palindrome assumes that the underlying CharBuffer does not change.</p>
 */
public final class Palindrome {
  
  private final CharBuffer string;
  
  public Palindrome(CharBuffer string) {
    if (string == null) throw new IllegalArgumentException("string is null");
    this.string = string;
  }
  
  public boolean isValid() {
    int len = string.length();
    for (int i = 0; i < len; i++) {
      if (string.charAt(i) != string.charAt(len - 1 - i)) {
        return false;
      }
    }
    return true; 
  }


}
