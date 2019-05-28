package com.br.exemplo;

public class SeparateChainingHashMap<K, V> implements Map<K, V> {
	
	private Node[] table;
	private int size;
	
	private static class Node {
		
		Object key;
		Object value;
		Node next;
		
		Node(Object key, Object value, Node next) {
			
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	public SeparateChainingHashMap() {
		this(97);
	}
	
	public SeparateChainingHashMap(int m) {
		
		this.table = new Node[97];
		size = 0;
	}
	
	private Node getNode(K key) {
		
		int i = hash(key);
		
		for(Node n=table[i]; n!=null; n=n.next) {
			
			if(key.equals(n.key)) {
				
				return n;
			}
		}
		
		return null;
	}
	
	private int hash(K key) {
		
		return (key.hashCode() & 0x7fffffff) % table.length; // Remove bit signal
	}
	
	@Override
	public void put(K key, V value) {
		
		Node n = getNode(key);
		
		if(n==null) {
			
			int i = hash(key);
			table[i] = new Node(key, value, table[i]);
			this.size++;
			if((this.size / table.length) == 8) resize(table.length * 2);
			
		}
		else {
			
			n.value = value;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		
		Node n = getNode(key);
		
		return (n!=null) ? (V) n.value : null;
	}
	
	@Override
	public void remove(K key) {
		
		int i = hash(key);
		
		Node tmp = new Node(null, null, table[i]);
		
		for(Node n = tmp; n.next!=null; n=n.next) {
			
			if(key.equals(n.next.key)) {
				n.next = n.next.next;
				this.size--;
				if((this.size / table.length) == 2) resize(table.length/2);
				break;
			}
		}
		
		table[i] = tmp.next;
	}
	
	private void resize(int newLength) {
		
		SeparateChainingHashMap <K,V> tmp = new SeparateChainingHashMap<>(newLength);
		for(int i = 0; i < table.length;i++) {
			for(Node n= table[i];n != null;n = n.next) {
				tmp.put((K)n.key, (V)n.value);
			}
		table = tmp.table;
		
		}
	}
	
	public void print() {
		
		for(Node n=table[0]; n!=null; n=n.next) {
			
			System.out.println(n);
		}
	}
}
