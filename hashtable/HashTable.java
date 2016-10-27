package hashtable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
public class HashTable 
{
	public static void main(String[] args) throws IOException
	{
		int lower = -10000;
		int upper =  10000;
		int count = 0;
		
		HashSet<Long> set = new HashSet<Long>();
		FileReader read = new FileReader("/Users/Lingqi/Downloads/2sum.txt");
		Scanner    sc   = new Scanner(read);
		
		while(sc.hasNextLong())
			set.add(sc.nextLong());

		sc.close();
		read.close();		
		System.out.println("Read all integers in and add them to the hash set.");
		
		
		for(int t = lower; t <= upper; t++)
		{
			System.out.println("t: " + t);
			if(hasPair(t, set))
				count ++;
		}
		
		System.out.println(count);
		
		
	}
	
	private static boolean hasPair(int t, HashSet<Long> set)
	{
		Iterator<Long> ite = set.iterator();
		while(ite.hasNext())
		{
			long i = ite.next();
			long j = t - i;
			if(i != j)
				if(set.contains(j))
					return true;
		}
		
		return false;
	}
}
