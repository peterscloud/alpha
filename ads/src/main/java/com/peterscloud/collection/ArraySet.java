package com.peterscloud.collection;

import com.peterscloud.stream.Stream;

public class ArraySet<E> implements Set<E> {
  
  private ArrayList<E> list = new ArrayList<E>();
  
  @Override
  public Iterator<E> iterator() {
   return list.iterator(); 
  }

  @Override public void add(E element) {
    if (list.contains(element)) {
      return; 
    }
    list.add(element);
  }

  @Override public boolean remove(E element) {
    return list.remove(element);    
  }

  @Override public int size() {
    return list.size();
  }

  @Override public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override public void clear() {
    list.clear();
  }

  @Override public Stream<E> stream() {
    return list.stream();
  }

  @Override public void addAll(Collection<? extends E> other) {
    Iterator<? extends E> iter = other.iterator();
    while (iter.hasNext()) {
     add(iter.next()); 
    }
  }

  @Override public boolean contains(E element) {
    return list.contains(element); 
  }

}
