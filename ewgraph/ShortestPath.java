package ewgraph;
import data.structure.MinPQ;
import data.structure.Stack;
import java.io.*;

public class ShortestPath 
{
	private int source;
	private boolean[] explored;
	private int[] from;
	private double[] distance;
	
	private static class V implements Comparable<V>
	{
		int v;
		int from;
		double dist;
		
		public V(int v, int from, double dist)
		{
			this.v = v;
			this.from = from;
			this.dist = dist;
		}
		
		public int compareTo(V other)
		{
			if(this.dist > other.dist) return +1;
			if(this.dist < other.dist) return -1;
			else                       return 0;
		}
	}
	
	public ShortestPath(EdgeWeightedGraph g, int source)
	{
		int N = g.V();
		this.source = source;
		
		explored = new boolean[N];
		from     = new int[N];
		distance = new double[N];
		
		dijkstraSearch(g);
	}
	
	private void dijkstraSearch(EdgeWeightedGraph g)
	{
		MinPQ<V> queue = new MinPQ<V>(g.E());
		
		explored[source] = true;
		distance[source] = 0;
		
		for(Edge e : g.adj(source))
		{
			int to   = e.other(source);
			double l = e.weight();
			queue.insert(new V(to, source, l));
		}
		
		
		while(!queue.isEmpty())
		{
			V v = queue.delMin();			
			if(!explored[v.v])
			{
				explored[v.v] = true;
				from[v.v]     = v.from;
				distance[v.v] = v.dist;
				
				for(Edge e : g.adj(v.v))
				{
					if(!explored[e.other(v.v)])
						queue.insert(new V(e.other(v.v), v.v, v.dist + e.weight()));
				}
			}
		}
		
		
	}
	
	
	public int source()
	{  return this.source;  }
	
	public boolean reachable(int v)
	{  return explored[v];  }
	
	public double distance(int v)
	{
		if(explored[v])  return distance[v];
		else             return -1;
	}
	
	public Iterable<Integer> path(int v)
	{
		if(explored[v])
		{
			Stack<Integer> path = new Stack<Integer>();
			while(v != source)
			{
				path.push(v);
				v = from[v];
			}
			
			path.push(source);
			return path;
		}
		
		else  return null;
	}
	
	
	public static void main(String[] args) throws IOException 	
	{
		
		int N = 200;
		EdgeWeightedGraph g  = new EdgeWeightedGraph(N);
		ShortestPath res = new ShortestPath(g, 0);
		int[] list = {6, 36, 58, 81, 98, 114, 132, 164, 187, 196};
		
		for(int i = 0; i < list.length - 1; i++)	
			System.out.printf("%.0f,", res.distance(list[i]));		
		System.out.printf("%.0f%n", res.distance(list[list.length - 1]));
		
		
	}
	
}
