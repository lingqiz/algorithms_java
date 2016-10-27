package search.tree;

public abstract class SymbolTable<Key, Value> // Generic Array 
{
	abstract void put(Key key, Value val);
	abstract Value get(Key key);
	abstract void delete(Key key);
	
	abstract boolean contains(Key key);
	abstract boolean isEmpty();
	
	abstract int size();
	abstract Iterable<Key> keys();
	
	/*
	 * Symbol Table as Generic Array using Key as index 
	 * 	-> Search Tree implementation : Idea from Binary Search, Dynamic Ordered Array 
	 * 	* Ordered Key Symboal Table
	 *  -> Hash & Hash Table
	 */
	
	
}
