package ugraph;
import data.structure.Bag;
import graph.Graph;

/*
 * Space requirement O(E)
 * Iterate over adjacent vertices O(degree)  
 */


public class AdjacencyList extends Graph
{
	private final int V;
	protected     int E;
	protected Bag<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public AdjacencyList(int v)
	{
		this.V = v;
		this.adj = (Bag<Integer>[]) new Bag[v];
		
		for(int i = 0; i < v; i++)
			adj[i] = new Bag<Integer>();
		
	}
	
	@Override
	public void addEdge(int v, int w) 
	{
		adj[v].add(w);
		adj[w].add(v);		
		E++;
	}

	@Override
	public Iterable<Integer> adj(int v) 
	{
		return adj[v];
	}

	@Override
	public int V() 
	{  return this.V;  }
	
	@Override
	public int E()
	{  return this.E;  }
}
