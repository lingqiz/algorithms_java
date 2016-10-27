package data.structure;

public class Median<T extends Comparable<T>> 
{
	private MaxPQ<T> max;
	private MinPQ<T> min;
	private int size;
	
	private int count = 0;
	
	public Median(int size)
	{
		this.size = size * 2;
		max = new MaxPQ<T>(size);
		min = new MinPQ<T>(size);
	}
	
	public void add(T t)
	{
		count++;
		if(count > size) throw new IndexOutOfBoundsException("Too much element.");
		
		if(count == 1) 
			max.insert(t);
		else
		{
			T left = max.max();			
			
			if(t.compareTo(left) <= 0) max.insert(t);
			else 					   min.insert(t);
		}
		
		if(max.size() - min.size() > 1)
			min.insert(max.delMax());	
		
		if(min.size() - max.size() >= 1)
			max.insert(min.delMin());
		
	}
	
	public T median()
	{  return max.max();  }
	
	public int size()
	{  return this.count;  }
	
}
