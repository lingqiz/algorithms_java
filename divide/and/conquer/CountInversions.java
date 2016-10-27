package divide.and.conquer;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CountInversions 
{
	/*
	 * Count Inversions in Array
	 * 	- count left  inversions
	 *  - count right inversions 
	 * 	- count split inversions in the merge step 
	 * 	
	 *  Divide and Conquer -> Recursion Tree of height log(n)
	 *  Do linear work each recursion step -> get n log(n) algorithm  
	 */
	
	private final static int CUTOFF = 7;
	private static long helperCount(int[] a, int lo, int hi)
	{
		long count = 0;
		
		for(int i = lo; i < hi; i++)
			for(int j = i + 1; j > lo && a[j] < a[j - 1]; j--)
			{
				count = count + 1;
				
				int buf = a[j];
				a[j] = a[j -1];
				a[j - 1] = buf;
			}
		
		return count;
	}
	
	public static long countInversions(int[] a)
	{
		int[] aux = Arrays.copyOf(a, a.length);
		return countHalf(a, aux, 0, a.length - 1);
	}
	
	private static long countHalf(int[] a, int[] aux, int lo, int hi)
	{
		if(hi - lo <= CUTOFF)   return helperCount(a, lo, hi);
		
		int mid = lo + (hi - lo) / 2;
		long leftInversions  = countHalf(aux, a, lo, mid);
		long rightInversions = countHalf(aux, a, mid + 1, hi);
		
		if(aux[mid] <= aux[mid + 1])
		{
			for(int k = lo; k <= hi; k++)
				a[k] = aux[k];
			
			return leftInversions + rightInversions;
		}
		
		return leftInversions + rightInversions + countSplit(a, aux, lo, mid, hi);
		
		
	}
	
	private static long countSplit(int[] a, int[] aux, int lo, int mid, int hi)
	{
		long inv = 0;
		int i = lo; int j = mid + 1;
		
		for(int k = lo; k <= hi; k++)
		{
			if      (i > mid)              a[k] = aux[j++];
			else if (j > hi)               a[k] = aux[i++];
			else if (aux[i] <= aux[j])     a[k] = aux[i++];
			else                           
			{
				a[k] = aux[j++]; 
				inv = inv + mid - i + 1; 
			}
		}
	
		return inv;
	}
	
	public static void main(String[] args) throws IOException
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		File input = new File("/Users/Lingqi/Downloads/IntegerArray.txt");
		FileReader fr = new FileReader(input);
		
		Scanner sc = new Scanner(fr);
		while(sc.hasNextInt())
		{
			int next = sc.nextInt();
			list.add(next);
			
		}
		
		sc.close();
		fr.close();
		
		int[] a = new int[list.size()];
		for(int i = 0; i < a.length; i++)
			a[i] = list.get(i);
		
		long count = countInversions(a);
		System.out.println(SortUtility.isSorted(a));
		System.out.println(count);
	}
}
