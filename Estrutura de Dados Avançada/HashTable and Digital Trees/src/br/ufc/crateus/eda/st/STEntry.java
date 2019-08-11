package br.ufc.crateus.eda.st;

import java.util.Map.Entry;

public class STEntry<K, V> implements Entry<K, V> {
	
	K key;
	V value;

	public STEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public V setValue(V value) {
		V tmp = this.value;
		this.value = value;
		return tmp;
	}
	
	@Override
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}
