package hashtable;
import java.util.Date;


@SuppressWarnings("unchecked")
public class HashSet<T> 
{
	/*
	 * Hashing is a tradeoff between Space requirement and Performance requirement 
	 * 	-> bit string as array index : fast but too much space 
	 * 	-> linked list + sequential search : O(n) time requirement
	 * 
	 * 	*reasonable table load (~0.75) 
	 *  *good hash function (uniformly distribution)
	 *  	- Good Hash Code (java implementation) 
	 *  		Require:    x.equals(y) --> x.hashCode() == y.hashCode()
	 *  		Desirable: !x.equals(y) --> x.hashCode() != y.hashCode()
	 *  		(default implementation: Memory Address of object) 
	 *  	
	 *  	- Modular by array length (Modular hashing)  
	 *  		* (key.hashCode() & 0x7fffffff) % M : set sign bit to 0
	 *  		* M better be Prime number 
	 *  
	 *     *Universal Hashing
	 *     Properly implemented Hash Function : Uniformaly Distribution across the array
	 *     										!! Pathological Data Set Exist 
	 *     										!! Randomized Solution : Universal Hashing 
	 *     		-> expected linked list length is S / M = table load (for separate chaining) 
	 *       	-> open addressing analysis (linear probing)  
	 *      
	 */
	
	// prime sequnce for hash table size ? 
//	private final static int[] PRIME = null;
//	private int prime = 0;
//	private T[] keys;
//	private int size;
	
	public HashSet()
	{
//		keys = (T[]) (new Object[PRIME[prime++]]);
//		size = 0; 
	}
	
//	private int index(T key)
//	{  return (key.hashCode() & 0x7fffffff) % keys.length;  }
	
	/**
	 * Return the hash code of this object.
	 * Hash Code : 
	 * 	Require:    x.equals(y) --> x.hashCode() == y.hashCode()
	 *  Desirable: !x.equals(y) --> x.hashCode() != y.hashCode()   
	 */
	
	@Override 
	public int hashCode()
	{  return super.hashCode();  }
		
	// Hash Code example for non - primitive object
	public final class Transaction 
	{
		private final String who;
		private final Date   when;
		private final double amount;
		
		public Transaction(String who, Date when, double amount)
		{
			this.who = who;
			this.when = when;
			this.amount = amount;
		}
		
		@Override
		public boolean equals(Object that)
		{
			if(that instanceof HashSet.Transaction)
			{				
				Transaction y = (Transaction) that;
				return this.who.equals(y.who) &&
					   this.when.equals(y.when) &&
					   this.amount == y.amount;
			}
			
			return false;
		}
		
		public int hashCode()
		{
			int hash = 17;
			hash = 31 * hash + who.hashCode();
			hash = 31 * hash + when.hashCode();
			hash = 31 * hash + ((Double) amount).hashCode();
			
			return hash;
		}
	}	
	
}
