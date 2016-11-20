package com.peterscloud.collection;

public class ArrayMap<K,V> implements Map<K,V> {
  
  static class Entry<K,V> implements Map.Entry<K,V> {
    K key;
    V value;
    
    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    
    public K getKey() { return key; }
    public V getValue() { return value; }
    public void setValue(V value) { this.value = value; }
    public void setKey(K key) { this.key = key; }
  }

  private Set<Map.Entry<K,V>> entries = new ArraySet<Map.Entry<K,V>>();
  
  public V put(K key, V value) {
    Iterator<Map.Entry<K,V>> iter = entries.iterator();
    while (iter.hasNext()) {
      Map.Entry<K,V> entry = iter.next();
      if (entry.getKey().equals(key)) {
       V oldValue = entry.getValue();
       entry.setValue(value);
       return oldValue;
      }
    }
    entries.add(new Entry<K,V>(key, value));
    return null; 
  }

  public Set<Map.Entry<K,V>> entrySet() {
    return entries;
  }

  public V get(K key) {
    Iterator<Map.Entry<K,V>> iter = entries.iterator();
    while (iter.hasNext()) {
      Map.Entry<K,V> entry = iter.next();
      if (entry.getKey().equals(key)) {
       return entry.getValue();
      }
    }
    return null;
  }

}
