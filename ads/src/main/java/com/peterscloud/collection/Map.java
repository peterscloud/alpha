package com.peterscloud.collection;

public interface Map<K,V> {
  
  public interface Entry<K,V> {
    public V getValue();
    public void setValue(V value);
    public K getKey();
    public void setKey(K key);
  }

  
  public V put(K key, V value);
  public V get(K key);
  public Set<Entry<K,V>> entrySet();
}
