package br.ufc.crateus.eda.st.string;

import java.util.LinkedList;
import java.util.Queue;

public class RWayTrieST<V> implements StringST<V> {
	private static final int R = 256;
	private Node root = new Node();
	
	private static class Node {
		Object value = 0;
		Node[] next = new Node[R];
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(String key) {
		Node node = get(root, key, 0);
		return (node != null)? (V) node.value : null;
	}
	
	private Node get(Node r, String key, int i) {
		if (r == null) return null;
		if (i == key.length()) return r;
		char ch = key.charAt(i);
		return get(r.next[ch], key, i + 1);
	}

	@Override
	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}
	
	private Node put(Node r, String key, V value, int i) {
		if (r == null) r = new Node();
		if (i == key.length()) {
			r.value = value;
			return r;
		}
		char ch = key.charAt(i);
		r.next[ch] = put(r.next[ch], key, value, i + 1);
		return r;
	}
	

	@Override
	public boolean contains(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		delete(root, key, 0);
	}
	
	private Node delete(Node r, String key, int i) {
		if (r == null) return null;
		if (i == key.length()) r.value = null;
		else {
			char ch = key.charAt(i);
			r.next[ch] = delete(r.next[ch], key, i + 1);
		}
		
		if (r.value != null) return r;
		for (Node n : r.next) if (n != null) return r;
		return null;
	}
	
	private void collect(Node r, String prefix, Queue<String> queue) {
		if (r != null) {
			if (r.value != null) queue.add(prefix);
			for (char ch = 0; ch < R; ch++) collect(r.next[ch], prefix + ch, queue);
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<String> keys() {
		Queue<String> queue = new LinkedList<>();
		collect(root, "", queue);
		return queue;
	}
	
	public Iterable<String> keysWithPrefix(String prefix) {
		Node node = get(root, prefix, 0);
		Queue<String> keys = new LinkedList<>();
		collect(node, prefix, keys);
		return keys;
	}
	
	public String longPrefixOf(String query) {
		int size = search(root, query, 0, 0);
		return query.substring(0, size);
	}
	
	private int search(Node node, String query, int i, int length) {
		if (node == null) return length;
		if (node.value != null) length = i;
		if (i == query.length()) return length;
		char ch = query.charAt(i);
		return search(node.next[ch], query, i + 1, length);
	}
	
	
	//@Questão 13
	public int subString(int L, String work) {
		int i = subString(work,0,L-1);
		return i;
	}
	
	private int  subString(String work, int i, int L) {
		if(L >= work.length()) return sizeKeys();
		char[] p = work.toCharArray();
		String aux = "";
		
		for(int j = i;  j<=L ; j++) aux+=p[j];
		put(aux, (V)"value");

		return subString(work,i+1,L+1);
	}
	
	private int sizeKeys() {
		int i = 0;
		for(String k :keys())i++;
		return i;
	}
	
	//@Questao 14
	
	public void putN(String key, V value) {
		root = putN(root, key, value, 0);
	}
	
	private Node putN(Node r, String key, V value, int i) {
		if (r == null) r = new Node();
		if (i == key.length()) {
			r.value = (int) r.value+1;
			return r;
		}
		char ch = key.charAt(i);
		r.next[ch] = putN(r.next[ch], key, value, i + 1);
		return r;
	}
	
	
	private void sizeKeysValues(int L) {
		for(String k :keys()) {
			
			if(get(k) != null && k.length() == L) {
				System.out.println(k+":"+get(k));
			}
		}
	}
	
	public void subStringOc(String text, int L) {
		subStringOc(text,0, L-1);
		sizeKeysValues(L);
	}
	
	private void subStringOc(String text, int i, int L) {
		if(L >= text.length()) return;
		char[] p = text.toCharArray();
		String aux = "";
		
		for(int j = i;  j<=L ; j++) aux+=p[j];
		putN(aux,(V)"vaue");

		subStringOc(text,i+1,L+1);

	}
	
	//Questão 12

		public String max() {
			String aux =  "";
			for(String ks : keys()) {
					if(aux.compareTo(ks) <= -1)aux = ks;
			}
			return aux;
		}
		
		public String min() {
			String aux =  max();
			for(String ks : keys()) {
					if(ks.compareTo(aux) <= -1)aux = ks;
			}
			return aux;
		}
		
		public String ceiling(String key) {
			String aux = max();
			for(String ks : keys()) {
				if((ks.compareTo(key) == 1 || ks.compareTo(key) == 0) && ks.compareTo(aux) <= -1) {
					aux = ks;
				}
			}
			return aux;
		}
		
		public String floor(String key) {
			String aux = min();
			for(String ks : keys()) {
				if((ks.compareTo(key) == -1 || ks.compareTo(key) == 0) && ks.compareTo(aux) >= 1) {
					aux = ks;
				}
			}
			return aux;
		}
		
		public int rank(String key) {
			int i = 0;
			for(String ks : keys()) {
				if(ks.compareTo(key) <= -1) i++;
			}
			return i;
		}
		
		public String select(int rk) {
			for(String ks : keys()) {
				if(rk == rank(ks)) return ks;
			}
			return "Error";
		}
		
		
		//@Questão 11
		public Iterable<String> keysThatMatch (String pat){
			Queue<String> q = new LinkedList<>();
			collectN(root,"",pat,q);
			return q;
		} 
		

		private void collectN(Node x,String pre,String pat,Queue<String>q) {
			int d = pre.length();
			if(x == null) return ;
			if(d == pat.length() && x.value != null) q.add(pre);
			if(d == pat.length()) return;
			
			char next = pat.charAt(d);
			for(char c = 0; c < R; c++) {
				if(next == '.' || next == c) {
					collectN(x.next[c], pre+c,pat,q);
				}
			}
		}
	

}
