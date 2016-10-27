package ugraph;

import graph.Graph;

import java.util.LinkedList;

public class BFSPaths extends Paths 
{
	// super.marked   super.from 
	private int[] dist;
	
	public BFSPaths(Graph g, int s) 
	{
		super(g, s);
	}
	
	@Override
	protected void search(Graph g, int s)
	{  breadthFirstSearch(g, s);  }

	
	private void breadthFirstSearch(Graph g, int s) 
	{
		super.marked[s] = true;
		this.dist[s]    = 0;
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
				
		Integer c = null;	
		while((c = queue.poll()) != null)
		{
			int depth = dist[c];
			for(int w : g.adj(c))
			{
				if(!super.marked[w])
				{
					super.marked[w] = true;
					super.from  [w] = c;
					this.dist   [w] = depth + 1;
					queue.add(w);
				}
			}	
		}
	}
	
	/*
	 * (Shortest) path length to the destination
	 * Return -1 if there are not connected  
	 */
	public int distance(int dest)
	{
		if(super.marked[dest])
			return this.dist[dest];
		else
			return -1;
	}
	
	

}
