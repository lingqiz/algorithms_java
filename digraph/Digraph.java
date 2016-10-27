package digraph;

public class Digraph extends ugraph.AdjacencyList
{

	public Digraph(int v) 
	{ super(v);  }
	
	
	/**
	 * Add a directed edge v -> w.
	 * 
	 * @param v the tail vertex in the edge
	 * @param w the head vertex in the edge
	 */
	@Override
	public void addEdge(int v, int w)
	{
		super.E++;
		adj[v].add(w);
	}
	
	public Digraph reverse()
	{
		Digraph newG = new Digraph(super.V());
		for(int v = 0; v < super.V(); v++)
			for(int w : this.adj(v))
				newG.addEdge(w, v);
		
		return newG;
	}
	
}
