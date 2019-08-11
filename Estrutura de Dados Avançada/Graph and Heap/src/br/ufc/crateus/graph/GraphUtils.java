package br.ufc.crateus.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.ufc.crateus.operations.BreadthFirstPaths;
import br.ufc.crateus.operations.ConnectedComponents;

public class GraphUtils {
	
	static <T> int shortestPath(Graph<T> graph, T v1, T v2) {
		BreadthFirstPaths<T> bs = new BreadthFirstPaths(graph,v1);
		return bs.pathTo(v2);
	}
	
	static <T> Graph<String> readFromFile(InputStream is, String sep) throws IOException{
		AdjacencyListGraph<String> graph = new AdjacencyListGraph(300000);
		InputStreamReader r = new InputStreamReader(is);
		BufferedReader lerArq = new BufferedReader(r);
	    
		String linha = lerArq.readLine();
	    while (linha != null) {
	    	String[] V  = linha.split(sep);
	    	for(int i = 1; i < V.length; i++) graph.addEdge(V[0], V[i]);
	        linha = lerArq.readLine();
	    }
		return graph;
	}
	
	static <T> int componentsConnected(Graph<T> graph){
		ConnectedComponents<T> c = new ConnectedComponents(graph);
		return c.count();
	}
	
/*
	static <T> int shortestPath(Graph<T> graph, T v1, T v2) {
		List<T> mark = new ArrayList<>();
		int numberEdges = graph.countEdges();
		for(T v : graph.adjacents(v1)) {
			mark.add(v1);
			mark.add(v);
			int s = search(graph,mark,v1 ,v2,1);
			if (numberEdges > s)
				numberEdges = s;
			mark = new ArrayList<>();
		}
		return numberEdges;
	}
	
	
	public static <T> int search(Graph<T> g,List<T> marcks, T v1 , T v2, int size) {
		for(T v : g.adjacents(v1)) {
			if(v.equals(v2)) return size;
			if(marcks.contains(v)) {	
			}else if (marcks.contains(v) == false) {
				marcks.add(v);
				return search(g,marcks,v,v2,size+1);
			}
		}
		return size;
	}
	*/
}
