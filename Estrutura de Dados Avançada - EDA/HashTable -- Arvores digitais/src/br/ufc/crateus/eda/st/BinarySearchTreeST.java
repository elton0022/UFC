package br.ufc.crateus.eda.st;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeST<K extends Comparable<K>, V> implements OrderedST<K, V> {
	
	protected Node root; 
	
	protected class Node extends STEntry<K, V> {
		
		Node  left, right;
		int count;
		
		public Node(K key, V value) {
			super(key, value);
			this.count = 1;
		}
	}
	
	private Node getNode(K key) {
		Node r = root;
		while (r != null) {
			if (key.compareTo(r.key) < 0) r = r.left;
			else if (key.compareTo(r.key) > 0) r = r.right;
			else return r;
		}
		
		return null;
	}

	@Override
	public V get(K key) {
		Node node = getNode(key);
		return (node != null)? node.value : null;
	}

	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}
	
	private Node put(Node r, K key, V value) {
		if (r == null) return new Node(key, value);
		int cmp = key.compareTo(r.key);
		
		if (cmp < 0) r.left = put(r.left, key, value);
		else if (cmp > 0) r.right = put(r.right, key, value);
		else r.value = value;
		r.count = size(r.left) + size(r.right) + 1;
		return r;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(K key) {
		root = delete(root, key);
	}
	
	private Node delete(Node r, K key) {
		if (r == null) return null;
		int cmp = key.compareTo(r.key);
		
		if (cmp < 0) r.left = delete(r.left, key);
		else if (cmp > 0) r.right = delete(r.right, key);
		else {
			if (r.left == null) return r.right;
			if (r.right == null) return r.left;
			
			Node t = r;
			r = min(r.right);
			r.right = deleteMin(t.right);
			r.left = t.left;
		}
		r.count = size(r.left) + size(r.right) + 1;
		return r;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return size(root);
	}
	
	private int size(Node r) {
		return (r != null)? r.count : 0;
	}

	@Override
	public Iterable<K> keys() {
		Queue<K> queue = new LinkedList<>();
		inorder(root, queue);
		return queue;
	}
	
	private void inorder(Node r, Queue<K> queue) {
		if (r != null) {
			inorder(r.left, queue);
			queue.add(r.key);
			inorder(r.right, queue);
		}
	}

	@Override
	public K min() {
		Node r = min(root);
		return r.key;
	}
	
	private Node min(Node r) {
		if (r.left == null) return r;
		return min(r.left);
	}

	@Override
	public K max() {
		Node r = max(root);
		return r.key;
	}
	
	private Node max(Node r) {
		if (r.right == null) return r;
		return min(r.right);
	}

	@Override
	public K floor(K key) {
		Node t = floor(root, key);
		return (t != null)? t.key : null;
	}
	
	private Node floor(Node r, K key) {
		if (r == null) return null;
		int cmp = key.compareTo(r.key);
		if (cmp < 0) return floor(r.left, key);
		if (cmp > 0) {
			Node t = floor(r.right, key);
			return (t != null)? t : r;
		}
		return r;
	}

	@Override
	public K ceiling(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K select(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMax() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node r) {
		if (r.left == null) return r.right;
		r.left = deleteMin(r.left);
		r.count = size(r.left) + size(r.right) + 1;
		return r;
	}

	@Override
	public int size(K lo, K hi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<K> keys(K lo, K hi) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public int rank(K key) {
		return rank(root, key);
	}
	
	private int rank(Node r, K key) {
		if (r == null) return 0;
		int cmp = key.compareTo(r.key);
		
		if (cmp < 0) return rank(r.left, key);
		if (cmp > 0) return 1 + size(r.left) + rank(r.right, key);
		return size(r.left);
	}

}
