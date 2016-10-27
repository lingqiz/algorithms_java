package digraph;

import java.util.Iterator;

public class TopologicalSort 
{
	private final int N;
	private final boolean[] explored;
	private final int[] id;
	private final int[] sorted;
	private final boolean isDAG;
	
	/**
	 * Compute the topological sort sequence for a directed,
	 * acyclic graph.
	 * 
	 * @param g the directed acyclic graph
	 */
	public TopologicalSort(Digraph g)
	{
		this.N     = g.V();
		this.label = N - 1;
		this.count = N - 1;
		
		this.id       = new int[N];		
		sorted   = new int[N];
		explored = new boolean[N];
		
		isDAG = !(new DirectedCycle(g).hasCycle());
		
		topologicalSort(g);
		this.sortedIterable = new NodesIterable();
	}
	
	/**
	 * Global variable for recording current label.
	 */
	private int label;
	private int count;
	
	/**
	 * The outer loop for computing topological ordering 
	 * for making sure every signle node will be labeled.
	 * @param g the directed acyclic graph
	 */
	private void topologicalSort(Digraph g)
	{
		for(int i = 0; i < N; i++)
			topoDfs(g, i);
	}
	
	/**
	 * DFS based subroutine for computing topologial ordering.
	 * Labeling vertex before backtracking -> labeling vertex 
	 * when it becomes a "sink" vertex
	 * 
	 * @param g the directed acyclic graph
	 * @param v currently exploring vertex.
	 */
	private void topoDfs(Digraph g, int v)
	{
		if(this.explored[v])  return;
		
		this.explored[v] = true;
		for(int w : g.adj(v))
			this.topoDfs(g, w);
		
		id[v] = this.label--;
		sorted[count--] = v;
	}
	
	public int id(int v)
	{  return this.id[v];  }
	
	public boolean isDAG()
	{  return this.isDAG;  }
	
	private class NodesIterable implements Iterable<Integer>
	{
		private class NodesIterator implements Iterator<Integer>
		{
			private int p = 0;
			
			@Override
			public boolean hasNext() 
			{  return this.p < TopologicalSort.this.N;  }
				

			@Override
			public Integer next() 
			{  return TopologicalSort.this.sorted[p++];  }
			
		}
		
		@Override
		public Iterator<Integer> iterator() 
		{  return new NodesIterator();  }
		
	}
	private NodesIterable sortedIterable;
	public Iterable<Integer> sorted()
	{  return this.sortedIterable;  }
	
	
	
	// Code below is for testing only 
	public static void main(String[] args)
	{
		int size = 11;
		Digraph g = new Digraph(size);
		
		addEdges(g);

		TopologicalSort ts = new TopologicalSort(g);
		for(int w : ts.sorted())
			System.out.println(w);
	}
	
	private static void addEdges(graph.Graph g)
	{
		g.addEdge(5, 10);
		g.addEdge(5, 4);
		g.addEdge(10, 1);
		g.addEdge(10, 0);
		g.addEdge(4, 9);
		g.addEdge(9, 2);
		g.addEdge(9, 8);
		g.addEdge(2, 6);
		g.addEdge(8, 6);
		g.addEdge(8, 3);
		g.addEdge(6, 3);
		g.addEdge(3, 7);
	}
}
