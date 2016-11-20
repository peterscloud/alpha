package com.peterscloud.stream;

import java.util.function.*;

public class MappedSpliterator<T, R> implements Spliterator<R>{
  
  private Spliterator<T> originalSpliterator;
  private Function<? super T,? extends R> mapper;

  class MappedConsumer implements Consumer<T> {
  //implements Consumer<? super T> {
    
    Consumer<? super R> originalAction;
    
    public void accept(T t) {
      originalAction.accept(mapper.apply(t));
    }

  }

  private MappedConsumer mappedAction = new MappedConsumer();
  
  public MappedSpliterator(Spliterator<T> original, Function<? super T,? extends R> mapper) {
    this.mapper = mapper;
    this.originalSpliterator = original;
  }

  public boolean tryAdvance(Consumer<? super R> action) {
    mappedAction.originalAction = action;
    return originalSpliterator.tryAdvance(mappedAction);
  }
  
  public Spliterator<R> trySplit() {
    Spliterator<T> spliterator = originalSpliterator.trySplit();
    if (spliterator != null) {
      return new MappedSpliterator<T,R>(spliterator, mapper);
    }
    return null;

  }

  
}
