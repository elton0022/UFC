package br.ufc.crateus.graph;

import java.util.ArrayList;
import java.util.List;

import br.ufc.crateus.hashtable.SeparateChainingHashMap;

public class AdjacencyListGraph<T> implements Graph<T> {
	
	private int numberEdges = 0;
	private int vertex = 0;
	private List<T> [] adJ;
	private int size = 0;
	private SeparateChainingHashMap<T,Integer> table;
	
	public AdjacencyListGraph (int n){
		this.table = new  SeparateChainingHashMap(n);
		this.adJ =  new List[n];
		this.size = n;
		for(int i = 0; i < n; i++) {
			adJ[i] = new ArrayList<>();
		}
	}
		
	public int getSize() {
		return size;
	}

	@Override
	public int countVertices() {
		return vertex;
	}

	@Override
	public int countEdges() {
		return numberEdges;
	}

	@Override
	public int index(T v) {
		return table.get(v);
	}

	@Override
	public T label(int index) {
		return table.getKey(index);
	}

	@Override
	public boolean contains(T v) {
		if(table.get(v) != null) return true;
		return false;
	}

	@Override
	public void addEdge(T v1, T v2) {
		
		if(!contains(v1)) {
			table.put(v1,vertex);
			vertex++;
		}
		adJ[index(v1)].add(v2);
		
		if(!contains(v2)) {
			table.put(v2,vertex);
			vertex++;
		}
		adJ[index(v2)].add(v1);
		numberEdges++;
	}

	@Override
	public Iterable<T> adjacents(T v) {
		return adJ[index(v)];
	}

	@Override
	public int degree(T v) {
		return adJ[index(v)].size();
	}
	
	public void printt() {
		table.print();
	}
	
}
