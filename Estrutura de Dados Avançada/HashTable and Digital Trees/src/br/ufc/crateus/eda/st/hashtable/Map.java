package br.ufc.crateus.hashtable;

public interface Map <K, V>{
	
	V get(K key);
	void put(K key, V value);
	void remove(K key);
}
