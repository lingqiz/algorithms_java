package data.structure;

public class MaxPQ<T extends Comparable<T>> extends BinaryHeap<T> 
{
	public MaxPQ(int capacity) 
	{  super(capacity);  }

	@Override
	protected boolean compare(int a, int b)
	{
		T i = super.cbt[a];
		T j = super.cbt[b];
		
		if(i.compareTo(j) >= 0)  return true;
		else                     return false;
	}
	
	public T delMax()
	{  return super.delRoot();  }
	
	public T max()
	{  return super.root();  }
}
