package br.ufc.crateus.eda.st;

public interface ST<K, V> {
	V get(K key);
	
	void put(K key, V value);
	
	boolean contains(K key);
	
	void delete(K key);
	
	boolean isEmpty();
	
	int size();
	
	Iterable<K> keys();
}
