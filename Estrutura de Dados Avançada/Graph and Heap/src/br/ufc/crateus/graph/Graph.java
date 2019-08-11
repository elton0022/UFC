package br.ufc.crateus.graph;

public interface Graph<T> {
	
	int countVertices();
	int countEdges(); 
	int index(T v);
	T label(int index);
	boolean contains(T v); 
	void addEdge(T v1, T v2); 
	Iterable<T> adjacents(T v); 
	int degree(T v);
}
