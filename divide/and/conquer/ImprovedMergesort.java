package divide.and.conquer;
public class ImprovedMergesort 
{
	/*
	 * Adding improvements to original merge sort
	 * 	- using insertion sort for small arrays
	 * 	- return for array that is already sorted 
	 * 	- avoid copying by alternating a and aux 
	 */
	
	
	// *Switch between a and aux ?!
	
	
	private final static int CUTOFF = 9;
	public static void mergeSort(int[] list)
	{
		int[] aux = new int[list.length];
		mergeSort(list, aux, 0, list.length - 1);
	}
	
	private static void mergeSort(int[] a, int[] aux, int lo, int hi)
	{
		if(hi - lo <= CUTOFF)
		{
			Mergesort.helperInsertionSort(a, lo, hi);
			return;
		}
		
		int mid = lo + (hi - lo) / 2;
		mergeSort(aux, a, lo, mid);
		mergeSort(aux, a, mid + 1, hi);
		
		if(aux[mid] <= aux[mid + 1])
		{
			for(int k = lo; k <= hi; k++)
				a[k] = aux[k];
			
			return;
		}
		
		merge(a, aux, lo, mid, hi);
		
	}
	
	private static void merge(int[] a, int[] aux, int lo, int mid, int hi)
	{
		int i = lo; int j = mid + 1;
		for(int k = lo; k <= hi; k++)
		{
			if       (i > mid)            a[k] = aux[j++]; 
			else if  (j > hi)			  a[k] = aux[i++];
			else if  (aux[j] < aux[i])    a[k] = aux[j++];
			else     					  a[k] = aux[i++];
		}
	}
	
	public static void main(String[] args)
	{
		
		for(int i = 1; i <= 1000000; i++)
		{
			int[] test = SortUtility.randomInts(i);
			mergeSort(test);
			
			if(!SortUtility.isSorted(test))   
				System.out.println(i);
		}		
	}	
}
