package digraph;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class SCC 
{
	private final int N;
	private final int[] label;
	private final boolean[] explored;
	public SCC(Digraph g)
	{
		this.N = g.V();
		this.label    = new int[N];
		this.explored = new boolean[N];
	
		computeSCC(g);
	}
	
	private int leader = 0;
	private void computeSCC(Digraph g)
	{		
		for(int s : new TopologicalSort(g.reverse()).sorted())
			if(!explored[s])
			{
				dfs(g, s);
				leader++;
			}
	}
	
	private void dfs(Digraph g, int v)
	{
		if(explored[v])  return;
		
		explored[v] = true;
		label[v]    = leader;
		for(int w : g.adj(v))
			dfs(g, w);
	}
	
	/**
	 * After computing SCC, given a vertex, return its SCC identifier.
	 * @param v vertex
	 * @return SCC indetifier
	 */
	public int id(int v)
	{  return this.label[v];  }
	
	/**
	 * Get the number of strong connected components in the graph.
	 * @return the number of Strong Connected Components.
	 */
	public int count()
	{  return this.leader;  }
	
	
	// Code below is used only for testing
	public static void main(String[] args) throws IOException
	{
		int size  = 875714;
		Digraph g = new Digraph(size);
		
		File inputFile    = new File("/Users/Lingqi/Downloads/SCC.txt");
		BufferedReader rd = new BufferedReader(new FileReader(inputFile));
		
		Scanner sc  = null;
		String line = null;
		while((line = rd.readLine()) != null)
		{
			sc = new Scanner(line);
			int tail = sc.nextInt() - 1;
			int head = sc.nextInt() - 1;
			g.addEdge(tail, head);
			
			sc.close();
		}
		rd.close();
		System.out.println("Read Input Data");
		
		SCC scc = new SCC(g);
		System.out.printf("There are %d SCCs %n", scc.count());
		
		HashMap<Integer, Integer> barStat = new HashMap<Integer, Integer>();
		for(int c = 0; c < scc.count(); c++)
			barStat.put(c, 0);
		
		for(int v = 0; v < scc.N; v++)
		{
			int label = scc.id(v);
			barStat.put(label, barStat.get(label) + 1);
		}
		 
		 Integer[] list = barStat.values().toArray(new Integer[0]);
		 Arrays.sort(list);
		 
		 for(int p = list.length - 1; p >= list.length - 1 - 10; p--)
			 System.out.printf("%d ", list[p]);
		
	}
	
}
