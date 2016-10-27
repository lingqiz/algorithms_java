package divide.and.conquer;
public class SortUtility 
{
	public static boolean isSorted(int[] list)
	{
		for(int i = 1; i < list.length; i++)
			if(list[i - 1] > list[i]) return false;
		
		return true;
	}
	
	public static int[] randomInts(int length)
	{
		int[] test = new int[length];
		java.util.Random rd = new java.util.Random();
		
		for(int i = 0; i < length; i++)
			test[i] = rd.nextInt(length * 10);
	
		return test;
	}
	
	
}
