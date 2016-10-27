package ewgraph;

public class DirectedEdge implements Comparable<DirectedEdge>
{
	
	private final int v;
	private final int w;
	private final double weight;
	
	// Weighted Vertex V --> W 
	public DirectedEdge(int v, int w, double weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int from()
	{  return this.v;  }
	
	public int to()
	{  return this.w;  }
	
	public double weight()
	{  return this.weight;  }
	
	@Override
	public int compareTo(DirectedEdge e)
	{
		if(this.weight > e.weight) return +1;
		if(this.weight < e.weight) return -1;
		
		return 0;
	}
}
