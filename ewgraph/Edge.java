package ewgraph;

public class Edge implements Comparable<Edge> 
{
	private final double weight;
	private final int v;
	private final int w;
	
	public Edge(int v, int w, double weight)
	{
		this.weight = weight;
		this.v      = v;
		this.w      = w;
	}
	
	public double weight()
	{  return this.weight;  }
	
	
	// Why use either & other method 
	public int either()
	{  return this.v;  }
	
	public int other(int u)
	{
		if(u == this.v)  return this.w;
		else return this.v;
	}
	
	
	@Override
	public int compareTo(Edge o) 
	{
		if(this.weight > o.weight)  return +1;
		if(this.weight < o.weight)  return -1;
		
		return 0;
	}

}
