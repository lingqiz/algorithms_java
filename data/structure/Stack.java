package data.structure;
import java.util.NoSuchElementException;

public class Stack<T> extends DSIterable<T>
{
	
	private int N = 0;
	
	public int size()
	{  return this.N;  }
	 
	public boolean isEmpty()
	{  return super.first == null;  }
	
	public void push(T item)
	{
		Node newNode = new Node(item, super.first);
		super.first  = newNode;
		
		N++;
	}
	
	public T pop()
	{
		if(isEmpty()) throw new NoSuchElementException("Empty Stack");
		
		T result = first.item;
		first    = first.next;
		N--;
		
		return result;
	}
	
	public T peek()
	{
		return first.item;
	}
}
