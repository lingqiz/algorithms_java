package ugraph;

import graph.Graph;

public class Cycle 
{
	private final int N;
	private final boolean[] explored;
	private boolean cycle = false;
	
	public Cycle(Graph g)
	{
		this.N   = g.V();
		explored = new boolean[N];
		
		for(int s = 0; s < N; s++)
		{
			if(!explored[s])
				dfs(g, s, s);
		}
		
	}
	
	private void dfs(Graph g, int v, int u)
	{
		if(cycle)  return;
		
		explored[v] = true;
		for(int w : g.adj(v))
		{
			if(!explored[w])
				dfs(g, w, v);
			else
			{
				if(w != u)
					cycle = true;
			}
		}
	}
	
	public boolean hasCycle()
	{  return this.cycle;  }
}
