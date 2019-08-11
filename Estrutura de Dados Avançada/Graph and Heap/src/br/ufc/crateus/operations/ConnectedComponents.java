package br.ufc.crateus.operations;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.ufc.crateus.graph.Graph;

public class ConnectedComponents<T> {
	private Graph<T> G;
	private boolean[] marked;
	private int[] id;
	private int count = 0;

	public ConnectedComponents(Graph<T> G) {
		this.G = G;
		this.id = new int[G.countVertices()];
		this.marked = new boolean[G.countVertices()];
		for (int s = 0; s < G.countVertices(); s++) {
			if (!marked[s] && G.label(s) != null) {
				bfs(G, G.label(s));
				count++;
			}
		}
	}

	private void bfs(Graph<T> G, T s) {
		Queue<T> queue = new LinkedList<T>();
		marked[G.index(s)] = true;
		id[G.index(s)] = count;
		queue.add(s);
		while (!queue.isEmpty()) {
			T v = queue.remove();
			for (T w : (List<T>) G.adjacents(v))
				if (!marked[G.index(w)]) {
					marked[G.index(w)] = true;
					queue.add(w);
				}
		}
	}

	public boolean connected(T v, T w) {
		return id[G.index(v)] == id[G.index(w)];
	}

	public int id(T v) {
		return id[G.index(v)];
	}

	public int count() {
		return count;
	}
}
