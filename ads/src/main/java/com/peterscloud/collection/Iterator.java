package com.peterscloud.collection;

public interface Iterator<E> {
    
    boolean hasNext();
    E next();
    void remove();
}
