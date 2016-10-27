package data.structure;
import java.util.NoSuchElementException;

public class Queue<T> extends DSIterable<T>
{
	// protected Node first;
	private Node last = null;
	private int N = 0;
	
	public boolean isEmpty()
	{  return this.N <= 0;  }
	
	public int size()
	{  return this.N;  }
	
	public void enqueue(T item)
	{
		Node newNode = new Node(item, null);
		if(isEmpty())
		{
			super.first = newNode;
			this.last   = newNode;
		}
		
		else
		{
			last.next = newNode;
			this.last = newNode;
		}
		
		N++;
	}
	
	public T dequeue()
	{
		if(isEmpty()) throw new NoSuchElementException("Empty Queue");
		
		T result   = first.item;
		this.first = first.next;
		N--;
		
		if(isEmpty())  last = null;
		
		return result;
	}
}
