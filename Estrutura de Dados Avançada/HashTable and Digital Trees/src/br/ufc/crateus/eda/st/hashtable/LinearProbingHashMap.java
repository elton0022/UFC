package br.ufc.crateus.hashtable;

public class LinearProbingHashMap<K, V> implements Map<K, V>  {
	
	private K[] keys;
	private V[] values;
	private int size;
	
	@SuppressWarnings("unchecked")
	public LinearProbingHashMap(int len) {
		this.keys = (K[]) new Object[len];
		this.values = (V[]) new Object[len];
		size = 0;
	}
	public LinearProbingHashMap(){
		this(397);
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % keys.length;
	}
	
	
	@Override
	public V get(K key) {
		int p = hash(key);
		while(keys[p] != null && !key.equals(keys[p])) p = (p + 1) % keys.length;
		return values[p];
	}

	@Override
	public void put(K key, V value) {
		int i = hash(key);
		while (keys[i] != null) {
			if (key.equals(keys[i])) {
				values[i] = value;
				return;
			}
			i = (i + 1) % size;
		}
		
		keys[i] = key;
		values[i] = value;		
	}

	@Override
	public void remove(K key) {
		int p = hash(key);
		while(keys[p] != null && !key.equals(keys[p])) p = (p + 1) % keys.length;
		values[p] = null;
	}
	
	
	public void print() {
		
		for(int i=0; i < keys.length; i++) {
			System.out.println(keys[i]);
			System.out.println(values[i]);
		}
	}

}
