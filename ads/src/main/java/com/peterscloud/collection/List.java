package com.peterscloud.collection;

import com.peterscloud.stream.Spliterator;

public interface List<E> extends Collection<E> {
    
    public E get(int index);
    public E set(int index, E element);
    public void insert(int index, E element);
    public E remove(int index);
    public Spliterator<E> spliterator();
    
}
