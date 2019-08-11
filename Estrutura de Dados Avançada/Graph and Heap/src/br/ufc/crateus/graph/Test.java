package br.ufc.crateus.graph;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test<T> {

	public static void main(String[] args) throws IOException {
  	AdjacencyListGraph<String> g = new AdjacencyListGraph(10);
	AdjacencyMatrixGraph<String> m = new AdjacencyMatrixGraph(6);
		GraphUtils gu = new GraphUtils();
		
		
		
		m.addEdge("a", "b");
		m.addEdge("a", "c");
		m.addEdge("c","b");
		m.addEdge("c","d");
		m.addEdge("d","f");
		m.addEdge("b","f");

		InputStream is = is = new BufferedInputStream(new FileInputStream("movies.txt")) ;
		Graph<String> t = gu.readFromFile(is, ",");
		System.out.println(gu.componentsConnected(t));
        is.close();
	}

}
