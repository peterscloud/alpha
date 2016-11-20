package com.peterscloud.collection;

import com.peterscloud.stream.Stream;

public interface Collection<E> extends Iterable<E> {
    
    public void add(E element);
    public boolean remove(E element);
    public int size();
    public boolean isEmpty();
    public void clear();
    public Stream<E> stream();
    public void addAll(Collection<? extends E> other);
    public boolean contains(E element);
}
