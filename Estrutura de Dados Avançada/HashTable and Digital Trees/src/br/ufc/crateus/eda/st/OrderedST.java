package br.ufc.crateus.eda.st;

public interface OrderedST<K, V> extends ST<K, V>{
	public K min();
	
	public K max();
	
	public K floor(K key);
	
	public K ceiling(K key);
	
	public K select(int n);
	
	public void deleteMax();
	
	public void deleteMin();
	
	public int size(K lo, K hi);
	
	public Iterable<K> keys(K lo, K hi);
	
	public int rank(K key);
}
