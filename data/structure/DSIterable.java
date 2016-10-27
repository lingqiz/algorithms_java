package data.structure;

import java.util.Iterator;

public abstract class DSIterable<T> implements Iterable<T>
{
	protected Node first;
	protected class Node
	{
		T    item;
		Node next;
		public Node(T item, Node next)
		{
			this.item = item;
			this.next = next;
		}
	}
	
	private class DSIterator implements Iterator<T>
	{
		private Node current;
		public DSIterator()
		{  this.current = first;  }
		
		@Override
		public boolean hasNext() 
		{  return current != null;  }

		@Override
		public T next() 
		{			
			T value = current.item;
			current = current.next;
			
			return value;
		}
		
	}

	@Override
	public Iterator<T> iterator() 
	{  return new DSIterator();  }
	
	
}
