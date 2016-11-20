package com.peterscloud.stream;

import java.util.function.*;

public class FilteredSpliterator<R> implements Spliterator<R>{
  
  private Spliterator<R> originalSpliterator;
  private Predicate<? super R> predicate;

  class FilteredConsumer implements Consumer<R> {
    
    Consumer<? super R> originalAction;
    
    public void accept(R r) {
      if (predicate.test(r)) {
        originalAction.accept(r);
      }
    }

  }

  private FilteredConsumer filteredAction = new FilteredConsumer();
  
  public FilteredSpliterator(Spliterator<R> original, Predicate<? super R> predicate) {
    this.predicate = predicate;
    this.originalSpliterator = original;
  }

  public boolean tryAdvance(Consumer<? super R> action) {
    filteredAction.originalAction = action;
    return originalSpliterator.tryAdvance(filteredAction);
  }
  
  public Spliterator<R> trySplit() {
    Spliterator<R> spliterator = originalSpliterator.trySplit();
    if (spliterator != null) {
      return new FilteredSpliterator<R>(spliterator, predicate);
    }
    return null;

  }

  
}
