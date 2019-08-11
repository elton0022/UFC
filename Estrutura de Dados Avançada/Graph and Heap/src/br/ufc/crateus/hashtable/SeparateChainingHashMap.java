package br.ufc.crateus.hashtable;

import java.util.ArrayList;
import java.util.List;


public class SeparateChainingHashMap<K, V> implements Map<K, V> {
	
	private Node[] table;
	private int size = 0;
	private int pos = 0;
	
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
	
	private static class Term {
		
		int exp;
		String coef;
		
		Term(int exp, String coef) {		
			this.exp = exp;
			this.coef = coef;
		}
	}
	
	
	public SeparateChainingHashMap() {
		this(97);
	}
	
	public SeparateChainingHashMap(int m) {
		this.table = new Node[m];
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
	
	
	public int sizeTable() {
		return pos;
	}
	
	private int hash(K key) {
		return ((key.hashCode() & 0x7fffffff) % table.length); // Remove bit signal
	}
	
	@Override
	public void put(K key, V value) {
		
		if ((this.size / table.length) >= 8) resize(2 * this.size);
		
		int i = hash(key);
		for (Node n = table[i]; n != null; n = n.next) { 
			if (key.equals(n.key)) {
				n.value = value;
				return;
			}
		}
		table[i] = new Node(key, value, table[i]);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		
		Node n = getNode(key);
		
		return (n!=null) ? (V) n.value : null;
	}
	//@Questão 7
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
		for(int i = 0; i < table.length;i++) {
			for(Node x = table[i]; x!=null; x = x.next) {
				System.out.println("Linha-"+i+": Chave-"+(String)x.key+": Valor-"+x.value);
			}
		}
	}
	
	public Iterable<String> keys() {
		List<String> list = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
			for (Node l =table[i]; l != null; l = l.next) 
				list.add((String)l.key);
				
		return list;
	}
	
	public int numKeys() {
		int n = 0;
		for (int i = 0; i < size; i++)
			for (Node l =table[i]; l != null; l = l.next) 
				if(l.key != null) n++;
				
		return n;
	}
	
	public Iterable<V> values(K key){
		List<V> l = new ArrayList<>(size);
		for(Node n = getNode(key) ; n != null; n = n.next) {
			l.add((V) n.value);
		}
		return l;
	}
	
	public K getKey(V value) {
		for(int i = 0; i < table.length;i++) {
			for(Node x = table[i]; x!=null; x = x.next) {
				if(value == x.value) {
					return (K)x.key;
				}
			}
		}
		return null;
	}
	
	
	 public K ceiling(K key) {
		 int i = hash(key);
		 K ceil = key;
		 for(Node n=table[i]; n!=null; n=n.next) {
			if((int)n.key >= (int)ceil) ceil = (K)n.key;
		}	
		return ceil;
	 }
	 
	 
	
}
