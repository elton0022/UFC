package br.ufc.crateus.graph;

import java.util.ArrayList;
import java.util.List;

import br.ufc.crateus.hashtable.SeparateChainingHashMap;

public class AdjacencyMatrixGraph<T> implements Graph<T> {
	
	private int numberEdges = 0;
	private int vertex = 0; 
	private int size;
	private boolean [][] adJ;
	private SeparateChainingHashMap<T,Integer> table;
	
	public AdjacencyMatrixGraph(int n) {
		this.table = new  SeparateChainingHashMap(n);
		this.adJ = new boolean[n][n];
		this.size = n;
		
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < n ; j++) {
				adJ[i][j] = false;
			}
		}
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
			vertex++;
			table.put(v1,vertex);
		}
		if(!contains(v2)) {
			vertex++;
			table.put(v2,vertex);
		}
		adJ[index(v1)] [index(v2)] = true;
		adJ[index(v2)] [index(v1)] = true;
		numberEdges++;
	}

	@Override
	public Iterable<T> adjacents(T v) {
		List<T> l = new ArrayList<>();
		for(int j = 0; j < this.size ; j++) {
			if (adJ[index(v)][j] == true) {
				l.add(label(j));
			}
		}
		return l;
	}

	@Override
	public int degree(T v) {
		int num = 0;
		for(int j = 0; j < this.size ; j++) {
			if (adJ[index(v)][j] == true) {
				num++;
			}
		}
		return num;
	}

	public void printt() {
		table.print();
	}
	

}
