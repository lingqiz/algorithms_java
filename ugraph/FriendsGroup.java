package ugraph;
import java.util.Scanner;

import graph.Graph;

public class FriendsGroup 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);		
		Graph g = new AdjacencyList(sc.nextInt());
		
		int pair = sc.nextInt();
		for(int i = 1; i <= pair * 2; i++)
		{
			int l = sc.nextInt();
			int r = sc.nextInt();
			g.addEdge(l, r);
		}
		
		sc.close();
		
		Connectivity result = new Connectivity(g);
		System.out.printf("Number of Groups : %d%n", result.count());
		int[] comp = result.components();
		for(int i = 0; i < result.count(); i++)
		{
			for(int j = 0; j < comp.length; j++)
			{
				if(i == comp[j])
					System.out.printf("%d ", j);
			}
			System.out.println();
		}
		
	}
}
