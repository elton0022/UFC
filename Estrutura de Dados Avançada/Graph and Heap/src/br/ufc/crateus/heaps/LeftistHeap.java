package br.ufc.crateus.heaps;

import java.util.Queue;

public class LeftistHeap <Key extends Comparable<Key>>{
	
	private class Node{
		Key key;
		Node left;
		Node right;
		private int i;
		
		public Node(Key key) {
			this(key,null,null);
			
		}
		public Node(Key key,Node right,Node left) {
			this.key = key;
			this.right = right;
			this.left = left;
			i = 0;
		}
	}
	
	private Node root;
	private int size;
	
	
	public void insert(Key key) {
		root = merge(root, new Node(key));
		size++;
	}
	public Key Extract_Min() {
		if(root == null)return null;
		Key tmp = root.key;
		root = merge(root.left,root.right);
		size--;
		return tmp;
	}
	public Key min() {
		return root == null ? null:root.key;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public int size() {
		return size;
	}
	public void merge(LeftistHeap h1) {
		root = merge(root,h1.root);
		size += h1.size();
		
	}
	
	private Node merge(Node h1,Node h2) {
		if(h1 == null)return h2;
		if(h2 == null)return h1;
		
		if(h1.key.compareTo(h2.key) > 0) {
			Node tmp = h1;
			h1 = h2;
			h2 = tmp;
		}
		if(h1.left == null)
			h1.left = h2;
		else {
			h1.right = merge(h1.right,h2);
			if(h1.left.i < h1.right.i) {
				Node tmp = h1.left;
				h1.left = h1.right;
				h1.right = tmp;
			}
			h1.i = h1.right.i + 1;
			
		}
		return h1;
		
	}

	private void keys(Node r,Key key,Queue<Key> queue) {
		if(r == null)return;
		int cmp = key.compareTo(r.key);
		if(cmp < 0 )keys(r.left,key,queue);
		queue.add(r.key);
		if(cmp > 0)keys(r.right,key,queue);
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	public Key get(Key key) {
		return get(root,key);
		
	}
	private Key get(Node r,Key key) {
		if(r == null)return null;
		int cmp =  key.compareTo(r.key);
		if(cmp < 0)return get(r.left,key);
		else if(cmp > 0)return get(r.right,key);
		else return r.key;
	}
}
