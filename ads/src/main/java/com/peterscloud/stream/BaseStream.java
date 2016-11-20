package com.peterscloud.stream;

import com.peterscloud.collection.Iterator;

public interface BaseStream<T, S extends BaseStream<T,S>> {
  
  /**
   * Returns an equivalent stream that is parallel.
   */
  public S parallel();
  
  /**
   * Returns an iterator for the elements of this stream.
   * This is a terminal operation.
   */
  public Iterator<T> iterator();
  
  
  /**
   * Returns an equivalent stream that is sequential. 
   * May return itself, either because the stream was already sequential, 
   * or because the underlying stream state was modified to be sequential.
   * This is an intermediate operation.
   */
  public S sequential();
  
  /**
   * Returns an equivalent stream that is unordered. 
   * May return itself, either because the stream was already unordered, 
   * or because the underlying stream state was modified to be unordered.
   * This is an intermediate operation.
   */
  public S unordered();
}
