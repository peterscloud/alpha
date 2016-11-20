package com.peterscloud.stream.sequential;

import com.peterscloud.collection.Iterator;
import com.peterscloud.stream.*;
import java.util.function.*;

public class SequentialStream<T> implements Stream<T> {
  
  Spliterator<T> spliterator;
  
  public SequentialStream(Spliterator<T> spliterator) {
    this.spliterator = spliterator;
  }

  
  /**
   * Reurns an equivalent stream that is parallel.
   */
  public SequentialStream<T> parallel() {
    throw new UnsupportedOperationException();
  }

  
  /**
   * Returns an iterator for the elements of this stream.
   * This is a terminal operation.
   */
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException();
  }

  
  
  /**
   * Returns an equivalent stream that is sequential. 
   * May return itself, either because the stream was already sequential, 
   * or because the underlying stream state was modified to be sequential.
   * This is an intermediate operation.
   */
  public SequentialStream<T> sequential() {
    return this; 
  }

  
  /**
   * Returns an equivalent stream that is unordered. 
   * May return itself, either because the stream was already unordered, 
   * or because the underlying stream state was modified to be unordered.
   * This is an intermediate operation.
   */
  public SequentialStream<T> unordered() {
    throw new UnsupportedOperationException(); 
  }

  public Stream<T> filter(Predicate<? super T> predicate) {
    return new SequentialStream<T>(new FilteredSpliterator<T>(spliterator, predicate));
  }

  
  public <R> Stream<R> map(Function<? super T,? extends R> mapper) {
    return new SequentialStream<R>(new MappedSpliterator<T,R>(spliterator, mapper));
  }

  
  public T reduce(T identity, BinaryOperator<T> accumulator) {
    class Accumulator<U> implements Consumer<U> {
      U result;
      BinaryOperator<U> acc;
      
      Accumulator(U ident, BinaryOperator<U> acc) {
         result = ident;
         this.acc = acc;
      }

      public void accept(U element) {
         result = acc.apply(result, element);
      }

    }

    Accumulator<T> consumer = new Accumulator<>(identity, accumulator);
    
    while (spliterator.tryAdvance(consumer)) ;

    return consumer.result;
  }
  
  public <R> R collect(Supplier<R> supplier,
              BiConsumer<R,? super T> accumulator,
              BiConsumer<R,R> combiner) {
    R r = supplier.get();
    while (spliterator.tryAdvance(t -> accumulator.accept(r, t))) ;
    return r;              
  }

  public <R,A> R collect(Collector<? super T,A,R> collector) {
    A container = collector.supplier().get();
    BiConsumer<A,? super T> accumulator = collector.accumulator();
    while (spliterator.tryAdvance(t -> accumulator.accept(container, t))) ;
    return collector.finisher().apply(container);
  }

}
