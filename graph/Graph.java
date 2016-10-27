package graph;

import java.util.Iterator;
import java.io.*;

public abstract class Graph 
{
	public static Graph copyOf(Graph g)
	{
		try
		{
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ObjectOutputStream output    = new ObjectOutputStream(buffer);			
			output.writeObject(g);
			
			ByteArrayInputStream data = new ByteArrayInputStream(buffer.toByteArray());
			ObjectInputStream input   = new ObjectInputStream(data);
			
			return (Graph) input.readObject();
		
		}
		catch(IOException | ClassNotFoundException e)
		{
			System.err.println("Error while copying Graph Object");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int degree(Graph g, int v)
	{		
		int degree = 0;
		Iterator<Integer> ite = g.adj(v).iterator();
		while(ite.hasNext())
		{
			degree ++;
			ite.next();
		}
		
		return degree;
	}
		
	public static int maxDegree(Graph g)
	{
		int maxDegree = 0;
		for(int v = 0; v < g.V(); v++)
		{
			int degree = Graph.degree(g, v);
			if(degree > maxDegree)
				maxDegree = degree;
		}
		
		return maxDegree;
	}
	
	public static double averageDegree(Graph g)
	{ return 2.0 * g.E() / g.V(); }
	
	
	public static int numberOfSelfLoops(Graph g)
	{
		int count = 0;
		for(int v = 0; v < g.V(); v++)
			for(int w : g.adj(v))
				if(v == w) count++;
		// each self - loop edge is counted twice 
		return count / 2; 
	}
	
	// add a edge between vertice v and w
	public abstract void addEdge(int v, int w);
	
	// vertices adjancent to v 
	public abstract Iterable<Integer> adj(int v);
	
	// number of vertices 
	public abstract int V();
	
	// number of edges
	public abstract int E();
	
}
