package divide.and.conquer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickSortHomework 
{
	public static long cmpCount = 0;
	
	private static void swap(int[] list, int i, int j)
	{
		int swap = list[i];
		list[i]  = list[j];
		list[j]  = swap;
		
	}
	
	private static void median(int[] list, int lo, int hi)
	{
		int mid = lo + (hi - lo ) / 2;
		if(list[lo] < list[hi])
		{
			if(list[hi] < list[mid])
			{
				swap(list, lo, hi);				
			}
			else
			{
				if(list[lo] < list[mid])
				{
					swap(list, lo, mid);
				}
			}
		}
		else 
		{
			if(list[mid] < list[hi])
			{
				swap(list, lo, hi);
			}
			else
			{
				if(list[mid] < list[lo])
				{
					swap(list, lo, mid);
				}
			}
		}
	}
	
	private static int partition(int[] list, int lo, int hi)
	{		
		median(list, lo, hi);
		
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
	
	private static void quickSort(int[] list, int lo, int hi)
	{
		if(hi - lo <= 0)  return;
		
		//Count cmps
		cmpCount = cmpCount + hi - lo;
		int pivotIdx = partition(list, lo, hi);
		
		quickSort(list, lo, pivotIdx - 1);
		quickSort(list, pivotIdx + 1, hi);
	}
	
	public static void quickSort(int[] list)
	{
		quickSort(list, 0, list.length - 1);
	}

	
	
	
	public static void main(String[] args) throws FileNotFoundException
	{
		File input = new File("/Users/Lingqi/Downloads/QuickSort.txt");
		Scanner sc = new Scanner(input);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(sc.hasNextInt())
			list.add(sc.nextInt());
		sc.close();
		
		int[] array = new int[list.size()];
		for(int i = 0; i < array.length; i++)
			array[i] = list.get(i);
				
		System.out.println(array.length);
		
		quickSort(array);
		System.out.println(SortUtility.isSorted(array));
		System.out.println(cmpCount);
	}
	
	
	
	
}
