package com.peterscloud.collection;

import org.junit.*;
import static org.junit.Assert.*;

public class CollectionTest {
    
    private Collection<Integer> newInstance() {
      return new ArrayList<Integer>();
    }
    
    @Test
    public void testAdd() {
        Collection<Integer> collection = newInstance();
        Integer zero = new Integer(0);
        collection.add(zero);
        assertEquals(zero, collection.iterator().next());
    }
    
    @Test
    public void testRemove() {
      Collection<Integer> collection = newInstance();
      for (int i = 0; i < 15; i++) {
        collection.add(i);
      }
      
      collection.remove(14);
      assertEquals(14, collection.size());
      Iterator<Integer> iter = collection.iterator();
      for (int i = 0; i < 14; i++) {
        Integer element = iter.next();
        assertEquals((Integer)i, element);
      }
      
      collection.remove(0);
      assertEquals(13, collection.size());
      iter = collection.iterator();
      for (int i = 1; i < 14; i++) {
        Integer element = iter.next();
        assertEquals((Integer)i, element);
      }
      
      collection.remove(5);
      assertEquals(12, collection.size());
      iter = collection.iterator();
      for (int i = 1; i < 14; i++) {
        if (i == 5) continue;
        Integer element = iter.next();
        assertEquals((Integer)i, element);
      }
      
      collection.remove(6);
      collection.remove(7);
      collection.remove(8);
      collection.remove(9);
      
      assertEquals(8, collection.size());
      iter = collection.iterator();
      for (int i = 1; i < 14; i++) {
        if (i >= 5 || i <= 9) continue;
        Integer element = iter.next();
        assertEquals((Integer)i, element);
      }
    }
}
