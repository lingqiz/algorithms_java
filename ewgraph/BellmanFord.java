package ewgraph;

import data.structure.Queue;
import data.structure.Stack;


/**
 * BellmanFord Algorithm for generic graph shortest path problem 
 * without negative cycle. 
 */
public class BellmanFord 
{
	private int   source;
	private double[] distance;
	private boolean[] onQueue;
	private DirectedEdge[] from;
	
	public BellmanFord(EdgeWeightedDigraph g, int s)
	{
		int N  = g.V();
		source = s; 
		
		onQueue  = new boolean[N];
		distance = new double[N];
		from     = new DirectedEdge[N];
		
		for(int i = 0; i < N; i++)
			distance[i] = Double.POSITIVE_INFINITY;
		
		distance[s] = 0;
		
		bellmanFordSearch(g, s);
		
	}
	
	private void bellmanFordSearch(EdgeWeightedDigraph g, int s)
	{
		Queue<Integer> verQue = new Queue<Integer>();
		
		verQue.enqueue(s);
		onQueue[s] = true;
		
		while(!verQue.isEmpty())
		{
			int v = verQue.dequeue();
			onQueue[v] = false;
			
			for(DirectedEdge e : g.adj(v))
			{
				int w = e.to();
				if(distance[w] > distance[v] + e.weight())
				{
					distance[w] = distance[v] + e.weight();
					from[w]     = e;
					if(!onQueue[w])
						verQue.enqueue(w);
				}
			}
		}		
	}
		
	
	public int source()
	{  return this.source;  }
	
	public double distance(int v)
	{  return distance[v];  }
	
	public boolean reachable(int v)
	{  return distance[v] != Double.POSITIVE_INFINITY;  }
	
	public Iterable<DirectedEdge> path(int v)
	{
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		DirectedEdge e = from[v];
		while(e != null)
		{
			path.push(e);
			e = from[e.from()];
		}		
		return path;
	}
	
}
