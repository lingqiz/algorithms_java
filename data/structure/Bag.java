package data.structure;

public class Bag<T> extends DSIterable<T>
{	
	public Bag()
	{
		super.first = null;
	}
	
	public void add(T item)
	{
		Node tail  = super.first;
		super.first = new Node(item, tail);
	}
}
