package divide.and.conquer;

import java.util.Random;

public class Selection 
{
	private static Random rd = new Random(System.currentTimeMillis());
	
	private static void swap(int[] list, int i, int j)
	{
		int swap = list[i];
		list[i]  = list[j];
		list[j]  = swap;
		
	}
	
	private static int partition(int[] list, int lo, int hi)
	{		
		// randomly choose a pivot element 
		int pivotIdx = rd.nextInt(hi - lo + 1);
		swap(list, lo, lo + pivotIdx);
		
		int pivot = list[lo]; 
		int i = lo + 1, j = i;
		while(j <= hi)
		{
			int c = list[j];
			if      (c < pivot)  swap(list, i++, j++);
			else if (c > pivot)  j++;  
			else                 j++;  // Equal case 
		}
		
		swap(list, lo, i - 1);
		return i - 1;
	}
	
	private static void selection(int[] list, int lo, int hi, int order)
	{
		if(hi - lo <= 0)  return;
		
		int pivotIdx = partition(list, lo, hi);
		
		if       (pivotIdx == order)   return;
		else if  (pivotIdx >  order)   selection(list, lo, pivotIdx - 1, order);
		else   /*(pivotIdx <  order)*/ selection(list, pivotIdx + 1, hi, order);   
				
	}
	
	public static int selection(int[] list, int order)
	{
		rd = new Random(System.currentTimeMillis());
		selection(list, 0, list.length - 1, order - 1);
		return list[order - 1];
	}
	
	/*
	 * Deterministic selection algorithm
	 * 
	 *	1. Break A into groups of 5, sort each group 
	 *	2. C = the n / 5 middle elements 
	 *	3. p = Select(C, 0, n / 5, n / 5 / 2)
	 *	4. Partition A around P 
	 *  5. If j = i return p 
	 *  6. Else recurse on Select method     
	 */
	
	
}
