package data.structure;
import divide.and.conquer.SortUtility;

// Heap Sort : in place worst case NlogN sorting algorithm 
public class Heapsort 
{
	private int[] list;
	private int N;
	
	public Heapsort(int[] list)
	{
		this.list = list;
		this.N    = list.length;
		
		for(int i = N / 2; i >= 1; i--)
			sink(i);
		
		while(N >= 1)
			delete();		
	}
			
	private void sink(int k)
	{
		while(2 * k <= N)
		{
			int child = k * 2;
			if(child < N && greater(child + 1, child))  child++;
			if(greater(k, child))  return;
			
			swap(k, child);
			k = child;
		}
	}
	
	private void delete()
	{
		swap(1, N--);
		sink(1);
	}
	
	
	private void swap(int i, int j)
	{
		i = i - 1; j = j - 1;
		int swap = list[i];
		list[i]  = list[j];
		list[j]  = swap;
	}
	
	private boolean greater(int i, int j)
	{ return list[i - 1] > list[j - 1];  }
	
	
	
	public static void main(String[] args)
	{
		int size = 123456;
		int[] list = new int[size];
		
		java.util.Random rd = new java.util.Random();
		for(int i = 0; i < size; i++)
			list[i] = rd.nextInt(size * 2);
		
		new Heapsort(list);
		System.out.println(SortUtility.isSorted(list));
		
	}
	
}
