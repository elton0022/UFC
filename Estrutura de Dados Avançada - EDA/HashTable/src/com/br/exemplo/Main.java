package com.br.exemplo;

public class Main {

	public static void main(String[] args) {

		SeparateChainingHashMap<Integer, String> hash = new SeparateChainingHashMap<>(10);
		
		hash.put(1, "Daniel");
		hash.put(2, "Fabio");
		hash.put(3, "Manel");
		hash.put(4, "Hartur");
		
		hash.remove(3);
		
		//System.out.println(hash.get(4));
		
		hash.print();
	}

}
