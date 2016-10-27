package ewgraph;
import data.structure.Bag;
public class EdgeWeightedGraph  
{
	private int E;
	private final int V;
	private final Bag<Edge>[] adj;
	//private final Edge[] edges;
	
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V)
	{
		this.V = V;
		adj = (Bag<Edge>[]) new Bag[V];
	
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Edge>();
	
	}
	
	public int V()
	{  return this.V;  }
	
	public int E()
	{  return this.E;  }
	
	public void addEdge(Edge e)
	{
		E++;
		
		int v = e.either(); int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}
	
	public Iterable<Edge> adj(int v)
	{  return adj[v];  }
	
	
}
