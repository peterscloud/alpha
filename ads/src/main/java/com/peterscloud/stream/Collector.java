package com.peterscloud.stream;

import java.util.function.*;

public interface Collector<T,A,R> {
  
  public BiConsumer<A,T> accumulator();
  public BinaryOperator<A> combiner();
  public Function<A,R> finisher();
  public Supplier<A> 	supplier();
  
  static <T,R> Collector<T,R,R> of(Supplier<R> supplier,
                                 BiConsumer<R,T> accumulator,
                                 BinaryOperator<R> combiner) {
    return new Collector<T, R, R>() {
      
      private Function<R,R> finisher = new Function<R,R>() {
          public R apply(R r) {
            return r;
          }
        };
      
      public Supplier<R> 	supplier() {
        return supplier;
      }

      public BiConsumer<R,T> accumulator() {
        return accumulator;
      }

      public BinaryOperator<R> combiner() {
        return combiner;
      }

      public Function<R,R> finisher() {
        return finisher; 
      }

    };                                
  }

}
