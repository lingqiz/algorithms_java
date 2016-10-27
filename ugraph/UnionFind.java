package ugraph;

public abstract class UnionFind 
{
	// Dynamically Tracking Connected Components Efficiently
	// Vertix indexed array; Could use Symbal Table for translate between Name and Integer

	/*
	 * Connected Components : 
	 * *Maximal set of objects that are Mutually Connected  
	 */
	
	
	private final int N;
	private final int[] id;
	private final int[] sz;
	
	// Array - Represented Trees : look up nodes & path to the node starting from leaves 
	// Avoid large tree by using Weighted Quick Union 
	//				 -> Max depth of any Node is lg N 
	
	
	public UnionFind(int n)
	{
		this.N = n;
		id = new int[N];
		sz = new int[N];
		
		for(int i = 0; i < N; i++)
			sz[i] = 1;
		
	}
	
	/*
	 * Path Compression : 
	 * 	two - pass implementation:
	 * 	add second loop and set each examined node to the root 
	 * 
	 *  one - pass implementation:
	 *  set each node to its grandparent 	 
	 */
	 
	private int root(int v)
	{
		while(this.id[v] != v)
		{
			id[v] = id[id[v]];
			v = this.id[v];
		}
		return v;
	}
	
	public void union(int p, int q)
	{
		int pRoot = root(p);
		int qRoot = root(q);
		
		if(sz[pRoot] < sz[qRoot]) 
		{
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}
		else
		{
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
		
	}
	
	
	public boolean connected(int p, int q)
	{  return this.root(p) == this.root(q);  }
	
	

}
