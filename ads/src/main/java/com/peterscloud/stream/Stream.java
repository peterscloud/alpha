package com.peterscloud.stream;

import java.util.function.*;

public interface Stream<T> extends BaseStream<T, Stream<T>> {
  
  public Stream<T> filter(Predicate<? super T> predicate);
  
  public <R> Stream<R> map(Function<? super T,? extends R> mapper);
  
  public T reduce(T identity, BinaryOperator<T> accumulator);
  
  public <R> R collect(Supplier<R> supplier,
              BiConsumer<R,? super T> accumulator,
              BiConsumer<R,R> combiner);
              
  public <R,A> R collect(Collector<? super T,A,R> collector);
}
