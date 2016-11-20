package com.peterscloud.collection;

import org.junit.*;
import static org.junit.Assert.*;

public class MapTest {
  
  @Test public void add() {
    Map<String,String> map = new ArrayMap<>();
    
    map.put("key0", "value0");
    
    assertEquals("map.entrySet.size", 1, map.entrySet().size());

  }

}
