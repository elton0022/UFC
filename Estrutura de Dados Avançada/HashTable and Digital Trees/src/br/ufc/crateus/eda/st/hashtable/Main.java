package br.ufc.crateus.hashtable;


public class Main {

	public static void main(String[] args) {

		SeparateChainingHashMap<String,String> hash = new SeparateChainingHashMap<>(11);
	
		hash.put("S", "aaaa");
		hash.put("E", "aaaa");
		hash.put("A", "aaaa");
		hash.put("R", "aaaa");
		hash.put("C", "aaaa");
		hash.put("H", "aaaa");
		hash.put("X", "aaaa");
		hash.put("M", "aaaa");
		hash.put("P", "aaaa");
		hash.put("L", "aaaa");
		
		hash.print();

		
	}

}
