package com.peterscloud.stream;

import java.util.function.*;

public interface Spliterator<T> {
  
  public boolean tryAdvance(Consumer<? super T> action);
  
  public Spliterator<T> trySplit();
}
