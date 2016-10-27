package ugraph;

import graph.Graph;

public class DFSPaths extends Paths 
{
	public DFSPaths(Graph g, int s) 
	{  super(g, s);  }
	
	@Override
	protected void search(Graph g, int s)
	{ this.depthFirstSearch(g, s, -1); }

	private void depthFirstSearch(Graph g, int s, int v)
	{
		if(super.marked[s] == true)  return;
		else
		{
			super.marked[s] = true;
			super.from  [s] = v;
			for(int w : g.adj(s))
				depthFirstSearch(g, w, s);			
		}
	}
	
	
	/*
	 * Non - Recursive implementation of depth first search 
	 
	private void depthFirstSearch(Graph g, int s)
	{
		super.marked[s] = true;
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(s);
		
		Integer c = null;
		while(!stack.isEmpty())
		{
			c = stack.pop();
			for(int w : g.adj(c))
			{
				if(!super.marked[w])
				{
					super.marked[w] = true;
					super.from  [w] = c;
					stack.push(w);
				}	
			}
		}
	}
	
	*/

}
