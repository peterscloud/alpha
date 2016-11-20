package com.peterscloud.stream;

import com.peterscloud.stream.sequential.SequentialStream;

public class StreamSupport {
  
  public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel) {
    return new SequentialStream<T>(spliterator); 
  }

}
