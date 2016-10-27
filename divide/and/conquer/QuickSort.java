package divide.and.conquer;
import java.util.Arrays;
import java.util.Random;

public class QuickSort 
{
	private final static int CUTOFF = 15;
	private static void swap(int[] list, int i, int j)
	{
		int buf = list[i];
		list[i] = list[j];
		list[j] = buf;
	}
	
	/*
	 * Shuffle an array in linear time 
	 * 	Knuth shuffle 
	 * 
	 * In iteration i, pick integer r between 0 and i uniformly at random 
	 * Swap a[i] and a[r]
	 */	
	public static void shuffle(int[] list)
	{
		Random rd = new Random();
		for(int i = 0; i < list.length; i++)
		{
			int r = rd.nextInt(i + 1);
			swap(list, i, r);
		}
	}
	// The generation of random numbers is too important to be left with chance 
	
	public static void quickSort(int[] list)
	{
		shuffle(list);
		quickSort(list, 0, list.length - 1);
		InsertionSort.insertionSort(list);
	}
	
	private static void quickSort(int[] list, int lo, int hi)
	{
		if(hi - lo <= CUTOFF)  return;
		
		int pivotIdx = partition(list, lo, hi);
		quickSort(list, lo, pivotIdx - 1);
		quickSort(list, pivotIdx + 1, hi);
	}
	
	//* Partition is hard to implement correctly!
	/*
	private static int partition2(int[] list, int lo, int hi)
	{
		int pivot = list[lo];
		int i = lo, j = hi + 1;
		
		while(true)
		{
			while(list[++i] < pivot)
				if(i == hi) break;
			
			while(list[--j] > pivot);
				
			if(i >= j) break;
			swap(list, i, j);
		}
		
		swap(list, lo, j);
		return j;		
	}
	*/
	
	private static int partition(int[] list, int lo, int hi)
	{
		int pivot = list[lo];
		int i = lo + 1; int j = hi;
		
		out : do
		{
			while(list[i] < pivot)   
			{
				i++;
				if(i > hi)			// handling boundary condition 
					break out;
			}
			
			while(list[j] > pivot)
				j--;
			
			if(i < j)
				swap(list, i++, j--);						
		}
		while(i < j);
		
		swap(list, lo, j);
		return j;
	}
	
	/*
	 * Selection algorithm which takes linear time
	 * Quick Selection based on quick sort partition  
	 */
	public static int selection(int[] list, int order)
	{
		shuffle(list);
		int lo = 0; int hi = list.length - 1;
			
		while(hi - lo >= 0)
		{
			int k = partition(list, lo, hi);
			if      (k == order)  return list[k];
			else if (k > order)   hi = k - 1;
			else                  lo = k + 1;
		}
		return list[order];		
	}
	
	// Often the purpose of sort is to bring items with equal keys together 
	//   --> This implies : LARGE NUMBER of duplicate keys 
	
	// Array with large numbers of duplicate keys 
	// Stop on equal keys -> avoid go quadratic  
		
	// Desirable Way : putting all items equal to the partitioning item in place 
	// --> 3-way partitioning 
	
	public static void threeWayQuickSort(int[] list)
	{
		//shuffle(list);
		threeWayQuickSort(list, 0, list.length - 1);
		InsertionSort.insertionSort(list);
	}
	
	private static void threeWayQuickSort(int[] list, int lo, int hi)
	{
		if(hi - lo <= 0)  return;
		
		int pivot = list[lo];
		int i = lo, j = hi, k = lo;
		while(k < j)
		{
			int c = list[k];
			if      (c == pivot)   k++;
			else if (c <  pivot)   swap(list, i++, k++);
			else                   swap(list, j--, k);
		}
		
		threeWayQuickSort(list, lo, i - 1);
		threeWayQuickSort(list, j + 1, hi);
	}
	
			
	public static void main(String[] args)
	{		 		
		int length = 300000;
		Random rd = new Random();
		
		int[] test1 = new int[length];
		for(int i = 0; i < length; i++)
			test1[i] = rd.nextInt(length);
		
		 int[] test2 = Arrays.copyOf(test1, length);
		
		long current = System.currentTimeMillis();
		threeWayQuickSort(test1);
		System.out.println(System.currentTimeMillis() - current);
		System.out.println(SortUtility.isSorted(test1));
		
		
		current = System.currentTimeMillis();
		Arrays.sort(test2);
		System.out.println(System.currentTimeMillis() - current);
		System.out.println(SortUtility.isSorted(test2));
		
	}
	
	
	
	
	
	
	
}
