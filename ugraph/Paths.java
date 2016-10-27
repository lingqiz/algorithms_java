package ugraph;

import graph.Graph;

import java.util.Iterator;

import data.structure.Bag;

/*
 * API for graph DFS 
 * 
 * Design Pattern for graph processing : 
 * 	*Decouple graph data type form graph processing 
 * 
 * 	 Create a Graph Object (represent Graph information)
 *   
 *   Pass Graph to graph processing routine 
 *   (that routine only use Gaph Object API)
 *   
 *   Query the graph-processing routine for information 
 *   (Graph - Processing Object provide API for querying) 
 *
 *	
 *	!!! Distinguishing between different kinds(purpose) 
 *  of graph processing algorithm, avoid fat, big Graph object 
 *  
 *   
 */

// Path finding object using DFS 
public abstract class Paths 
{	
	private class PathIterable implements Iterable<Integer>
	{
		private final int v;
		public PathIterable(int v)
		{  this.v = v;  }
		
		@Override
		public Iterator<Integer> iterator() 
		{  return new PathIterator(v);  };
		
		private class PathIterator implements java.util.Iterator<Integer>
		{
			private int v;
			public PathIterator(int v)
			{  this.v = v;  }
							
			@Override
			public boolean hasNext() 
			{  return this.v != Paths.this.start;  }				
	
			@Override
			public Integer next() 
			{
				int vertice = Paths.this.from[v];
				this.v      = vertice;
				
				return vertice;
			}
			
		}
		
	}

	private final Bag<Integer> reachable;
	private final int          start;
	
	protected final boolean[]  marked;
	protected final int[]      from;
	
	
	
	public Paths(Graph g, int s)
	{
		start = s;
		marked = new boolean[g.V()];
		from   = new int[g.V()];
		
		search(g, s);
		
		reachable = new Bag<Integer>();
		for(int i = 0; i < marked.length; i++)
			if(marked[i])
				reachable.add(i);
	}
	
	// DFS marks all vertices connected to s in time proportional to 
	// the [sum of their degrees]  
	
	protected abstract void search(Graph g, int s);

	
	public boolean hasPathTo(int v)
	{  return this.marked[v]; }
	
	public Iterable<Integer> reachable()
	{  return this.reachable();  }
	
	public Iterable<Integer> pathFrom(int v)
	{
		if(marked[v]) { return new PathIterable(v); }			
		else          { return null; }
	}
	
	public int s()
	{  return this.start;  }
} 
