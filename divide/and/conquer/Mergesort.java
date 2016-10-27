package divide.and.conquer;
public class Mergesort 
{
	public static void mergeSort(int[] a)
	{	
		int[] aux = new int[a.length];
		mergeSort(a, aux, 0, a.length - 1);
	}
	
	private final static int CUTOFF = 7;
	public static void helperInsertionSort(int[] a, int lo, int hi)
	{
		for(int i = lo; i <= hi - 1; i++)
			for(int j = i + 1; j > lo && a[j] < a[j - 1]; j--)
			{
				int buf = a[j];
				a[j] = a[j - 1];
				a[j - 1] = buf;
			}
	}
	
	private static void mergeSort(int[] a, 
			int[] aux, int lo, int hi)
	{
		if(hi - lo <= CUTOFF)
		{
			helperInsertionSort(a, lo, hi);
			return;
		}
		
		int mid = lo + (hi - lo) / 2;
		
		mergeSort(a, aux, lo, mid);
		mergeSort(a, aux, mid + 1, hi);
			
		if(a[mid] <= a[mid + 1]) 	return;
		merge(a, aux, lo, hi, mid);
	}
	

	private static void merge(int[] a, int[] aux,
			int lo, int hi, int mid)
	{
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		int i = lo; int j = mid + 1;
		for(int k = lo; k <= hi; k++)
		{
			if       (i > mid)                          a[k] = aux[j++];
			else if  (j > hi)   						a[k] = aux[i++];
			else if  (aux[i] <= aux[j])                 a[k] = aux[i++];
			else  										a[k] = aux[j++];
		}				
	}

}
