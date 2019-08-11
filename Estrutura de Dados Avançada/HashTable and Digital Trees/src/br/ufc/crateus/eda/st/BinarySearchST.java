package br.ufc.crateus.eda.st;

import java.util.List;

public class BinarySearchST<K extends Comparable<K>, V> implements OrderedST<K, V> {
	private K[] keys;
	private V[] values;
	private int length;
	
	@SuppressWarnings("unchecked")
	public BinarySearchST(int size) {
		this.keys = (K[]) new Object[size];
		this.values = (V[]) new Object[size];
		this.length = 0;
	}
	
	public int rank(K key) {
		int lo = 0, hi = length - 1;
		while (lo < hi) {
			int m = (lo + hi) / 2;
			int cmp = key.compareTo(keys[m]);
			if (cmp < 0) hi = m - 1;
			else if (cmp > 0) lo = m + 1;
			return m;
		}
		return lo;
	}

	@Override
	public void put(K key, V value) {
		int j = rank(key);
 		if (!key.equals(keys[j])) {
 			for (int i = length; i > j; i--) { 
 				keys[i] = keys[i - 1];
 				values[i] = values[i - 1];
 			}
 			length++;
 		}
 		keys[j] = key;
 		values[j] = value;
	}

	@Override
	public V get(K key) {
		int j = rank(key);
 		return (key.equals(keys[j]))? values[j] : null;
	}

	@Override
	public void delete(K key) {
		int j = rank(key);
		if (key.equals(keys[j])) {
 			for (int i = j; i < length - 1; i++) {
 				keys[i] = keys[i + 1];
 				values[i] = values[i + 1];
 			}
 			length--;
		}
	}
	
	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public int size() {
		return length;
	}

	public Iterable<K> keys() {
		List<K> x ;
		return null;
	}

	@Override
	public K min() {
		return keys[0];
	}

	@Override
	public K max() {
		return keys[length - 1];
	}

	@Override
	public K floor(K key) {
		int j = rank(key);
		if (key.equals(keys[j])) return key;
		return (j > 0)? keys[j - 1] : null;
	}

	@Override
	public K ceiling(K key) {
		int j = rank(key);
		return keys[j];
	}

	@Override
	public K select(int i) {
		return keys[i];
	}

	@Override
	public Iterable<K> keys(K lo, K hi) {
		return null;
	}

	@Override
	public int size(K lo, K hi) {
		return rank(hi) - rank(lo);
	}

	@Override
	public void deleteMax() {
		length--;
	}

	@Override
	public void deleteMin() {
		for (int i = 0; i < length - 1; i++) {
			keys[i] = keys[i + 1];
			values[i] = values[i + 1];
		}
		length--;
	}
	
	public static void main(String[] args) {
		BinarySearchST<String, Integer> st = new BinarySearchST<>(10);
		st.put("abc", 1);
		st.put("bcd", 2);
		st.put("cde", 3);
		
		for (String key : st.keys) System.out.println(key);
	}
}
