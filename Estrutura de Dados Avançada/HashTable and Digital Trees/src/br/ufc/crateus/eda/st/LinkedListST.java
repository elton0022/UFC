package br.ufc.crateus.eda.st;

public class LinkedListST<K, V> implements ST<K, V> {

	private LLEntry lst;
	private int count = 0;
	
	private class LLEntry extends STEntry<K, V> {
		LLEntry next;

		public LLEntry(K key, V value) {
			
			super(key, value);
			
		}
	}

	@Override
	public V get(K key) {
		LLEntry e = getEntry(key);
		return (e != null)? e.value : null;
	}

	private LLEntry getEntry(K key) {
		for (LLEntry i = lst; i != null; i = i.next) 
			if (i.key.equals(key)) return i;
		return null;
	}

	@Override
	public void put(K key, V value) {
		LLEntry e = getEntry(key);
		if (e == null && value != null) {
			LLEntry n = new LLEntry(key, value);
			n.next = lst;
			lst = n;
			count++;
		}
		else if (value != null) e.value = value;
		else delete(key);
	}

	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}

	@Override
	public void delete(K key) {
		LLEntry head = new LLEntry(null, null);
		head.next = lst;
		for (LLEntry i = head; i.next != null; i = i.next) {
			if (i.next.key.equals(key)) {
				i.next = i.next.next;
				count--;
				break;
			}
		}
		lst = head.next;
	}

	@Override
	public boolean isEmpty() {
		return lst == null;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void print() {
		for (LLEntry i = lst; i != null; i = i.next) System.out.println(i); 
	}
	
	public static void main(String[] args) {
		LinkedListST<String, Integer> st = new LinkedListST<>();
		st.put("João", 35);
		st.put("Paulo", 15);
		st.put("Ana", 25);
		st.put("Rafael", 20);		
		st.put("Rafael", 24);
		st.put("Ana", null);
		st.print();
		System.out.println(st.isEmpty());
	}

}
