package com.peterscloud.collection;

import com.peterscloud.stream.Stream;
import com.peterscloud.stream.StreamSupport;
import com.peterscloud.stream.Spliterator;
import java.util.function.*;

public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int count;


    public ArrayList() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int count) {
        elements = (E[])new Object[count];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int position = 0;

            public boolean hasNext() {
                return position < count;
            }

            public E next() {
                return elements[position++];
            }

            public void remove() {
                ArrayList.this.remove(position);
            }
        };
    }

    @Override
    public void add(E element) {
        if (count == elements.length) {
            elements = java.util.Arrays.copyOf(elements, elements.length * 2);
        }
        elements[count++] = element;
    }

    @Override
    public boolean remove(E element) {
      if (element != null) {
        for (int i = 0; i < count; i++) {
          if (element.equals(elements[i])) {
            remove(i);
            return true;
          }
        }
      } else {
        for (int i = 0; i < count; i++) {
          if (elements[i] == null) {
            remove(i);
            return true;
          }
        }
      }
      return false;
    }

    @Override
    public void addAll(Collection<? extends E> other) {
      Iterator<? extends E> iter = other.iterator();
      while (iter.hasNext()) {
        add(iter.next()); 
      }

    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        count = 0;
    }

    @Override
    public E get(int index) {
        check(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        check(index);
        return elements[index] = element;
    }

    @Override
    public void insert(int index, E element) {

    }

    @Override public boolean contains(E element) {
      if (element != null) {
        for (int i = 0; i < count; i++) {
          if (element.equals(elements[i])) {
            return true;
          }
        }
      } else {
        for (int i = 0; i < count; i++) {
          if (elements[i] == null) {
            return true;
          }
        }
      }
      return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        check(index);
        E element = elements[index];
        count--;
        int len = count - index;
        if (elements.length > 10 && count == elements.length / 2 && count > 0) {
            Object[] tmp = new Object[elements.length * 3 / 4];
            System.arraycopy(elements, 0, tmp, 0, index - 1);
            System.arraycopy(elements, index, tmp, index + 1, len);
            elements = (E[])tmp;
        } else if (len > 0) {
            System.arraycopy(elements, index + 1, elements, index, len);
        } 
        elements[count] = null;//delete the reference to the last element

        return element;
    }

    private void check(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
    
    class ListSpliterator implements Spliterator<E> {
      private int low;
      private int high;
      
      /**
       * Creates a new Spliterator.
       * @param start Low index, inclusive
       * @param end High index, exclusive
       */
      ListSpliterator(int start, int end) {
        low = start;
        high = end;
      }

      
      public boolean tryAdvance(Consumer<? super E> action) {
        if (low < high) {
          action.accept(elements[low++]);
          return true;
        }
        return false;
      }


      public Spliterator<E> trySplit() {
        int diff = high - low;
        if (diff > 1) {
          int end = high, start = high = low + diff / 2;
          return new ListSpliterator(start, end);
        }
        return null;
        
      }
    }

    
    @Override
    public Spliterator<E> spliterator() {
      return new ListSpliterator(0, count); 
    }

    @Override
    public Stream<E> stream() {
      boolean parallel = false;
      return StreamSupport.stream(spliterator(), parallel);
    }

}
