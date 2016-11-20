package com.peterscloud.stream;

import java.util.function.*;
import com.peterscloud.collection.*;

public class Collectors {
  
  public static <T,K> Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier) {
    return new Collector<T, Map<K,List<T>>, Map<K,List<T>>>() {
      
      public Supplier<Map<K,List<T>>> 	supplier() {
        return new Supplier<Map<K,List<T>>>() {
          public ArrayMap<K,List<T>> get() {
            return new ArrayMap<K,List<T>>();
          }
        };
      }
      

      public BiConsumer<Map<K,List<T>>,T> accumulator() {
        return new BiConsumer<Map<K,List<T>>,T>() {
          public void accept(Map<K,List<T>> acc, T value) {
            K key = classifier.apply(value);
            List<T> list = acc.get(key);
            if (list == null) {
              list = new ArrayList<T>();
              acc.put(key, list);
            }
            list.add(value);
          }
        };
      }

      public BinaryOperator<Map<K,List<T>>> combiner() {
        return new BinaryOperator<Map<K,List<T>>>() {
          public Map<K,List<T>> apply(Map<K,List<T>> a, Map<K,List<T>> b) {
            Map<K,List<T>> map = new ArrayMap<K,List<T>>();
            Iterator<Map.Entry<K,List<T>>> iter = a.entrySet().iterator();
            while (iter.hasNext())
            {
              Map.Entry<K,List<T>> entry = iter.next();
              ArrayList<T> list = new ArrayList<T>();
              list.addAll(entry.getValue());
              List<T> bList = b.get(entry.getKey());
              if (bList != null) {
                 list.addAll(bList);
              }
              map.put(entry.getKey(), list);
            }
            return map;
          }
        };
      }

      public Function<Map<K,List<T>>,Map<K,List<T>>> finisher() {
        return new Function<Map<K,List<T>>,Map<K,List<T>>>() {
          public Map<K,List<T>> apply(Map<K,List<T>> map) {
            return map;
          }

        }; 
      }

    }; 
  }

  public static <T,K,A,D> Collector<T,?,Map<K,D>> groupingBy(
    Function<? super T,? extends K> classifier,
    Collector<? super T,A,D> downstream) {
    
    return new Collector<T,Map<K,A>,Map<K,D>>() {
      
       public BiConsumer<Map<K,A>,T> accumulator() {
         return new BiConsumer<Map<K,A>,T>() {
           public void accept(Map<K,A> acc, T value) {
             
             K key = classifier.apply(value);
             A a = acc.get(key);
             //String found = "true";
             if (a == null) {
               //found = "false";
               a = downstream.supplier().get();
               acc.put(key, a);
             }
             /*
             System.out.println("accept " + value 
             + ", acc.hashCode: "  + acc.hashCode() 
             + ", key: " + key
             + ", found: " + found);
             */
             downstream.accumulator().accept(a, value);
           }
         };
       }

      public BinaryOperator<Map<K,A>> combiner() {
        return new BinaryOperator<Map<K,A>>() {
          public Map<K,A> apply(Map<K,A> a, Map<K,A> b) {
            return a;//TODO
          }
        };
      }

      public Function<Map<K,A>,Map<K,D>> finisher() {
        return new Function<Map<K,A>,Map<K,D>>() {
          public Map<K,D> apply(Map<K,A> map) {
            Set <Map.Entry<K,A>> set = map.entrySet();
            //System.out.println("finisher acc.hashCode: " + map.hashCode() + " set.size: " + set.size());
            Function<A,D> finisher = downstream.finisher();
            Map<K,D> dmap = new ArrayMap<>();
            Iterator<Map.Entry<K,A>> entries = set.iterator();
            while (entries.hasNext()) {
              Map.Entry<K,A> entry = entries.next();
              dmap.put(entry.getKey(), finisher.apply(entry.getValue()));
            }
            return dmap;
          }
        }; 
      }

      public Supplier<Map<K,A>> supplier() {
        return new Supplier<Map<K,A>>() {
          public Map<K,A> get() {
            return new ArrayMap<K,A>(); 
          }

        };
      }

    };

  }

}
