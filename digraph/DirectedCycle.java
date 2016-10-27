package digraph;

public class DirectedCycle 
{
	private final int N;
	private final boolean[] explored;
	private final boolean[] onStack;
	private boolean hasCycle = false;
	
	public DirectedCycle(Digraph g)
	{
		this.N   = g.V();
		explored = new boolean[N];
		onStack  = new boolean[N];
		
		for(int s = 0; s < N; s++)
		{
			if(hasCycle) break;
			if(!explored[s]) dfs(g, s);
		}
	}
	
	private void dfs(Digraph g, int v)
	{
		if(hasCycle) return;
		
		explored[v] = true;
		onStack[v]  = true;
		for(int w : g.adj(v))
		{
			if(!explored[w])  dfs(g, w);
			else
			{
				if(onStack[w]) 
					hasCycle = true;
			}
		}
		
		onStack[v] = false;
	}
	
	public boolean hasCycle()
	{  return this.hasCycle;  }
}
