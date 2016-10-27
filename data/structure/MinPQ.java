package data.structure;

public class MinPQ<T extends Comparable<T>> extends BinaryHeap<T>
{
	public MinPQ(int capacity)
	{  super(capacity);  }
	
	@Override
	protected boolean compare(int a, int b)
	{
		T i = super.cbt[a];
		T j = super.cbt[b];
		
		if(i.compareTo(j) <= 0)   return true;
		else                      return false;
	}
	
	public T delMin()
	{  return super.delRoot();  }
		
	public T min()
	{  return super.root();  }
	
	public static <T extends Comparable<T>> void heapSort(T[] array)
	{
		MinPQ<T> heap = new MinPQ<T>(array.length);
		for(T t : array)
			heap.insert(t);
		
		for(int i = 0; i < array.length; i++)
			array[i] = heap.delMin();
	}
}
