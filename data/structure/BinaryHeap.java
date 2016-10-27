package data.structure;

import java.util.NoSuchElementException;

/*
 * Immutable T 
 * Using resizing array technique
 */

public abstract class BinaryHeap<T extends Comparable<T>> 
{
	protected T[] cbt;
	private int N;
	
	// Binary Heap is a generalized queue  
	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity)
	{
		this.N   = 0;
		this.cbt = (T[]) new Comparable[capacity + 1];	
	}
	
	public int size()
	{  return this.N;  }
	
	public boolean isEmpty()
	{  return this.N == 0;  }
	
	protected T root()
	{
		if(N == 0) throw new NoSuchElementException("Empty Queue");
		return this.cbt[1];  
	}
	
	public void insert(T item)
	{
		cbt[++N] = item;
		swim(N);
	}
	
	private void swim(int k)
	{
		while(k > 1 && compare(k, k / 2))
		{
			swap(k, k / 2);
			k = k / 2;
		}
	}
	
	protected T delRoot()
	{
		if(N == 0) throw new NoSuchElementException("Empty Queue");
		
		T root = this.root();
		swap(1, N--);
		sink(1);
		
		cbt[N + 1] = null;
		return root;
	}
	
	private void sink(int k)
	{
		while(2 * k <= N)
		{
			int child = 2 * k;
			if(child < N && compare(child + 1, child)) child ++;
			
			if(compare(k, child))  return;
			
			swap(k, child);
			k = child;
		}
	}
	
	private void swap(int i, int j)
	{
		T swap = cbt[i];
		cbt[i] = cbt[j];
		cbt[j] = swap;
	}
	
	//Give concrete implementation for this method to 
	//get minPQ & maxPQ
	protected abstract boolean compare(int a, int b);
	
	public static void main(String[] args)
	{
		int size = 10000;
		
		MinPQ<Integer> min = new MinPQ<Integer>(size);
		MaxPQ<Integer> max = new MaxPQ<Integer>(size);
		
		java.util.Random rd = new java.util.Random();
		
		for(int i = 0; i < size; i++)
		{
			int number = rd.nextInt(size * 2);
			
			max.insert(number);
			min.insert(number);
		}
			
		for(int i = 0; i < size; i++)
			System.out.print(max.delMax() + " ");
		
		System.out.println();
			
		for(int i = 0; i < size; i++)
			System.out.print(min.delMin() + " ");
		
	}
}
