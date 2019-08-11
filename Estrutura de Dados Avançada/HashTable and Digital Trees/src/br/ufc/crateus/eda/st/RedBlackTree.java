package br.ufc.crateus.eda.st;

public class RedBlackTree<K extends Comparable<K>, V> extends BinarySearchTreeST<K, V> {
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	protected class RBNode extends Node {
		boolean color; 
		
		public RBNode(K key, V value) {
			super(key, value);
			this.color = RED;
		}
	}
	
	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
		setColor(root, BLACK);
	}
	
	private Node put(Node r, K key, V value) {
		if (r == null) return new RBNode(key, value);
		
		int cmp = key.compareTo(r.key);
		if (cmp < 0) r.left = put(r.left, key, value);
		else if (cmp > 0) r.right = put(r.right, key, value);
		else r.value = value;
		
		if (getColor(r.left) == BLACK && getColor(r.right) == RED) r = rotateLeft(r);
		if (getColor(r.left) == RED && getColor(r.left.left) == RED) r = rotateRight(r);
		if (getColor(r.left) == RED && getColor(r.right) == RED) flipColors(r);
		return r;
	}
	
	@SuppressWarnings("unchecked")
	private void setColor(Node r, boolean color) {
		RBNode rb = (RBNode) r;
		rb.color = color;
	}
	
	@SuppressWarnings("unchecked")
	private boolean getColor(Node r) {
		if (r != null) {
			RBNode rb = (RBNode) r;
			return rb.color;
		}
		else return BLACK;
	}

	@SuppressWarnings("unchecked")
	private Node rotateLeft(Node r) {
		Node x = r.left;
		r.right = x.left;
		x.left = r;
		setColor(x, getColor(r));
		setColor(r, RED);
		return x;
	}
	
	@SuppressWarnings("unchecked")
	private Node rotateRight(Node r) {
		Node x = r.right;
		r.left = x.right;
		x.right = r;
		setColor(x, getColor(r));
		setColor(r, RED);
		return x;
	}	
	
	private void flipColors(Node r) {
		setColor(r, RED);
		setColor(r.left, BLACK);
		setColor(r.right, BLACK);
	}
	
	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
		super.delete(key);
	}
	
	private boolean isRed(RBNode node) {
		if (node == null) return false;
		return node.color == RED;
	}
}
