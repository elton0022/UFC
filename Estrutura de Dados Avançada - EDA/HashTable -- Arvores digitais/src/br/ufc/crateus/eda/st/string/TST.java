package br.ufc.crateus.eda.st.string;

import java.util.LinkedList;
import java.util.Queue;


public class TST<V> implements StringST<V> {
	
	private Node root;
	
	private class Node {
		char ch;
		V value;
		Node left, mid, right;
		
		public Node(char ch) {
			this.ch = ch;
		}
	}

	@Override
	public V get(String key) {
		Node node = get(root, key, 0);
		return (node != null)? node.value : null;
	}
	
	private Node get(Node r, String key, int i) {
		if (r == null) return null;
		char ch = key.charAt(i);
		if (ch < r.ch) return get(r.left, key, i);
		if (ch > r.ch) return get(r.right, key, i);
		if (i == key.length() - 1) return r;
		return get(r.mid, key, i + 1);
	}

	@Override
	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}
	
	private Node put(Node r, String key, V value, int i) {
		char ch = key.charAt(i);
		if (r == null) r = new Node(ch);
		if (ch < r.ch) r.left = put(r.left, key, value, i);
		else if (ch > r.ch) r.right = put(r.right, key, value, i);
		else if (i == key.length() - 1) r.value = value;
		else r.mid = put(r.mid, key, value, i + 1);
		return r;
	}
	
	private void collect(Node r, String prefix, Queue<String> queue) {
		if (r != null) {
			if (r.value != null) queue.add(prefix);
			System.out.println(r.ch);
			collect(r.left,prefix+r.ch,queue);
			collect(r.mid,prefix+r.ch,queue);
			collect(r.right,prefix+r.ch,queue);
			
		}
	}
	
	
//	private void print(Node r) {
//		if (r == null) return;
//		System.out.println(r.value);
//		if(r.left != null)print(r.left);
//		if(r.right!=null)print(r.right);
//		if(r.mid != null) print(r.mid);
//		
//	}
	
	

	@Override
	public boolean contains(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub

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
	
	public void print() {
		for(String key: keys()) {
			System.out.println(key);
		}
	}
}
