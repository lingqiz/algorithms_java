package divide.and.conquer;
import java.util.Arrays;

public class InsertionSort 
{
	
	
	
	private static void swap(int[] a, int i, int j)
	{
		int buf = a[i];
		a[i] = a[j];
		a[j] = buf;
	}
	
	public static void insertionSort(int[] list)
	{
		for(int i = 0; i < list.length - 1; i++)
			for(int j = i + 1; j > 0 && list[j] < list[j - 1]; j--)
				swap(list, j, j - 1);
	}
	
	/*
	 * Shell Sort : 
	 *  move entries more than one position by at a time h - sorting the array 
	 *  worst case O(N ^ (3/2)) 
	 */
	
	public static void shellSort(int[] list)
	{
		int h = 1;
		while(h < list.length / 3)  h = 3 * h + 1;
		
		while(h >= 1)
		{
			for(int i = h - 1; i < list.length - 1; i++)
				for(int j = i + 1; j > h - 1 && list[j] < list[j - h]; j -= h)
					swap(list, j, j - h);
			
			h = h / 3;
		} 
	}
	

	public static void main(String[] args)
	{
		int len = 100000;
		int[] test1 = SortUtility.randomInts(len);
		int[] test2 = Arrays.copyOf(test1, test1.length);
		
		long start = System.currentTimeMillis();
		InsertionSort.insertionSort(test1);
		System.out.println(SortUtility.isSorted(test1));
		System.out.println(System.currentTimeMillis() - start);
		
		start = System.currentTimeMillis();
		InsertionSort.shellSort(test2);
		System.out.println(SortUtility.isSorted(test2));
		System.out.println(System.currentTimeMillis() - start);

	}

}
