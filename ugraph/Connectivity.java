package ugraph;
import java.util.Arrays;

import graph.Graph;

/*
 * Perprocess graph to answer queries of the form is v connected to w?
 * in constant time 
 * -> Solve in the depth - first search manner 
 * 
 * Divide vertices set into Connected Components
 * Build up Connectivity Object in Linear Time!! 
 * 	-> DFS, O(n + m) or O(2m)  
 * 
 * Union - Find : not quite 
 * but can handle mixed union and find (DYNAMIC connectivity)  
 */
public class Connectivity 
{
	private final boolean[] marked;
	private final int[] components;
	private final int         size;
	private int count;
	
	public Connectivity(Graph g)
	{
		this.size   = g.V();
		this.marked = new boolean[size];
		components  = new int[size];
		
		this.count = 0;
		initilize(g);
	}
	
	private void initilize(Graph g)
	{
		for(int idx = 0; idx < this.marked.length; idx++)
		{
			if(!this.marked[idx])
			{
				depthFirstSearch(g, idx);
				count++;
			}					
		}		
	}
	
	private void depthFirstSearch(Graph g, int c)
	{
		if(this.marked[c])  return;
		
		this.marked[c]     = true;
		this.components[c] = count;
		
		for(int w : g.adj(c))
			depthFirstSearch(g, w);
	}
	
	// Test if vertex v, w is connected
	public boolean connected(int v, int w)
	{  return components[v] == components[w];  }
	
	// Number of connected components 
	public int count()
	{  return this.count;  }
	
	// Component identifier for v
	public int id(int v)
	{  return components[v];  }
	
	public int size()
	{  return this.size;  }
	
	public int[] components()
	{
		return Arrays.copyOf(this.components, this.components.length);
	}
}
