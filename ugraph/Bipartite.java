package ugraph;

import graph.Graph;

public class Bipartite 
{
	
	private final int N;
	private boolean bipartite = true;
	private final boolean explored[];
	private final boolean label[];
	
	
	public Bipartite(Graph g)
	{
		this.N   = g.V();
		explored = new boolean[N];
		label    = new boolean[N];
		
		for(int v = 0; v < N; v++)
			if(!explored[v])
				dfs(g, v, true);
				
	}	
	
	private void dfs(Graph g, int v, boolean c)
	{
		this.explored[v] = true;
		this.label[v]    = c;
		for(int w : g.adj(v))
		{
			if(!explored[w])
				dfs(g, w, !c);
			else
			{
				if(label[w] == c)
					bipartite = false;
			}
		}
	}
	
	public boolean isBipartite()
	{  return this.bipartite;  }
	
	/**
	 * Look up the label for a vertex in a bipartite graph,
	 * if the graph is actually bipartite.
	 * @param v index of the vertex
	 * @return label of the vertex represented by <tt>0</tt>
	 * and <tt>1</tt>, return <tt>-1</tt> if the graph is not 
	 * bipartite.
	 */
	
	public int label(int v)
	{
		if(!bipartite)  return -1;
		
		if(label[v])  return 0;
		else          return 1;
	}
	 	
	public static void main(String[] args)
	{
		Graph g = new AdjacencyList(13);
		
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 6);
		g.addEdge(0, 5);
		g.addEdge(1, 3);
		g.addEdge(0, 2);
		g.addEdge(3, 5);
		g.addEdge(5, 4);
		g.addEdge(4, 6);
		g.addEdge(6, 7);
		g.addEdge(7, 8);
		g.addEdge(8, 10);
		g.addEdge(9, 10);
		g.addEdge(9, 11);
		g.addEdge(12, 11);
		g.addEdge(10, 12);
		
		System.out.println(new Bipartite(g).isBipartite());
		
	}

}
