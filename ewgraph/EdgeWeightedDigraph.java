package ewgraph;
import data.structure.Bag;

public class EdgeWeightedDigraph 
{
	private int V;
	private int E;
	private Bag<DirectedEdge>[] adj;
	
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int v)
	{
		this.V   = v;
		this.E   = 0;
		this.adj = (Bag<DirectedEdge>[]) new Bag[V];
		
		for(int i = 0; i < V; i++)
			adj[i] = new Bag<DirectedEdge>();
	}
	
	public int V()
	{  return this.V;  }
	
	public int E()
	{  return this.E;  }
	
	public void addEdge(DirectedEdge e)
	{
		E++;
		adj[e.from()].add(e);
	}
	
	public Iterable<DirectedEdge> adj(int v)
	{  return this.adj[v];  }
}
