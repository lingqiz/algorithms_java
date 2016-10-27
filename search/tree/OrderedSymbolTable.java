package search.tree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import data.structure.Median;
import data.structure.Queue;


// 1.39 lgN random case
/* Improvement : 2 - 3 tree implemented as Red - Black Tree
	*LOCAL Transformation in 2 - 3 trees
	*Perfect Balance 
	
	
	Left - Leaning Red - Black BSTs 
		- No nodes has two red links 
		- Perfect Black balance 
		- Red link lean left 
		
	1 - 1 Correspondance between 2-3 trees and LLRB 
	Operation in LLRB  <-->  Operation in 2-3 tree 

*/
public class OrderedSymbolTable<Key extends Comparable<Key>, Value> 
{
	private final static boolean RED   = true;
	private final static boolean BLACK = false;
	
	private class Node
	{
		Key key;
		Value value;
		
		Node left, right;
		int size;
		
		boolean color;
		
		public Node(Key k, Value v, boolean c)
		{
			this.key = k;
			this.value = v;
			this.color = c;
		}
	}
	
	private Node root = null;
	public boolean isEmpty() 
	{  return this.root == null;  }
	
	private Node search(Key key, Node node)
	{
		if(node == null)  return null;
		
		int cmp = key.compareTo(node.key);
		if     (cmp == 0)     return node;
		else if(cmp > 0)      return search(key, node.right);
		else 				  return search(key, node.left);
		
	}
	
	private boolean isRed(Node n)
	{
		if(n == null) return BLACK;
		return n.color == RED;
	}
	
	private Node rotateLeft(Node x)
	{
		Node y = x.right;
		Node b = y.left;
		
		y.left  = x;
		x.right = b;
		
		y.color = x.color;
		x.color = RED;
		
		x.size = nodeSize(x.left) + nodeSize(x.right) + 1;
		y.size = nodeSize(y.left) + nodeSize(y.right) + 1;
		
		return y;
	}
	
	private Node rotateRight(Node x)
	{
		Node y = x.left;
		Node b = y.right;
		
		y.color = x.color;
		x.color = RED;
		
		y.right = x;
		x.left  = b;
		
		x.size = nodeSize(x.left) + nodeSize(x.right) + 1;
		y.size = nodeSize(y.left) + nodeSize(y.right) + 1;
		
		return y;
	}
	
	private void flipColor(Node x)
	{
		x.color       = RED;
		x.left.color  = BLACK;
		x.right.color = BLACK;
	}
	
	
	public Value get(Key key)
	{
		Node result = search(key, root);
		if(result == null) return null;
		
		return result.value;
	}   
	
	public void insert(Key k, Value v)
	{
		root = insert(k, v, root);		
		root.color = BLACK;
	}
	
	private Node insert(Key k, Value v, Node n)
	{
		if(n == null)  
		{
			Node newNode = new Node(k, v, RED);
			newNode.size = 1;
			return newNode;
		}
		
		int cmp = k.compareTo(n.key);
		if(cmp == 0)
		{
			n.value = v;
			return n;
		}
		
		else if(cmp > 0)
		{
			Node newRight = insert(k, v, n.right);
			n.right = newRight;
		}
		else
		{
			Node newLeft = insert(k, v, n.left);
			n.left = newLeft;
		}
		
		if(isRed(n.right) && !isRed(n.left))     n = rotateLeft(n);
		if(isRed(n.left)  && isRed(n.left.left)) n = rotateRight(n);
		if(isRed(n.left)  && isRed(n.right))     flipColor(n);
		
		n.size = nodeSize(n.left) + nodeSize(n.right) + 1;
		return n;	
	}
	
	public void delete(Key k)
	{  this.root = delete(k, root);  }
	
	private Node delete(Key k, Node n)
	{
		if(n == null)  return null;
		
		int cmp = k.compareTo(n.key);
		if      (cmp > 0) n.right = delete(k, n.right);
		else if (cmp < 0) n.left  = delete(k, n.left);
		else
		{
			if (n.left == null)    return n.right;
			if (n.right == null)   return n.left;
			
			Node t = n;
			n = min(t.right);
			
			
			n.right = deleteMin(t.right);
			n.left  = t.left;			
		}
		
		n.size = nodeSize(n.left) + nodeSize(n.right) + 1;
		return n;
	}
	
	public void deleteMin()
	{  this.root = deleteMin(root);  }		
	
	private Node deleteMin(Node n)
	{
		if(n == null)       return null;		
		if(n.left == null)  return n.right;
		
		n.left = deleteMin(n.left);
		n.size = nodeSize(n.left) + nodeSize(n.right) + 1;
		return n;
	}
	
	public void deleteMax()
	{  this.root = deleteMax(root);  }
	
	private Node deleteMax(Node n)
	{
		if(n == null)       return null;		
		if(n.right == null) return n.left;
		
		n.right = deleteMax(n.right);
		n.size = nodeSize(n.left) + nodeSize(n.right) + 1;
		return n;
	}
	
	
	public int size()
	{  return nodeSize(root);  }
	
	public int size(Key lo, Key hi)
	{  return rank(hi) - rank(lo);  }
	
	private int nodeSize(Node n)
	{  
		if(n == null) return 0;
		else          return n.size;
	}
	
	public Key select(int i)
	{  return select(i + 1, root);  }
	private Key select(int i, Node n)
	{
		if(n == null) throw new IndexOutOfBoundsException("Index: " + i);
		
		if(nodeSize(n.left) + 1 == i)  return n.key;
		if(i <= nodeSize(n.left))      return select(i, n.left);
		else                      return select(i - nodeSize(n.left) - 1, n.right);
	}
	
	public int rank(Key k)
	{
		Node n   = this.root;
		int rank = 0;
		while(n != null)
		{
			int cmp = k.compareTo(n.key);
			if(cmp == 0) return rank + nodeSize(n.left);
			if(cmp <  0) n = n.left;
			else         
			{
				rank += (nodeSize(n.left) + 1);
				n = n.right;
			}
		}
		return rank;
	}
	
	public Key max()
	{
		Node n  = root;
		while(n.right != null)
			n = n.right;
		
		return n != null? n.key : null;		
	}
	
	public Key min()
	{
		Node n = root;
		while(n.left != null)
			n = n.left;
		
		return n != null? n.key : null;
	}
	
	private Node min(Node n)
	{
		while(n.left != null)
			n = n.left;
		
		return n;
	}
	
	// The largest Key in the tree that is less than k
	private Key floor(Key k, Node n)
	{
		if(n == null)  return null;
		
		int cmp = k.compareTo(n.key);
		if(cmp == 0) return k;
		if(cmp <  0) return floor(k, n.left);
		else
		{
			Key right = floor(k, n.right);
			return right != null? right : n.key;
		}			
		
	}
	
	public Key floor(Key k)
	{  return floor(k, root);  }
	
	// The smallest Key in the tree that is larger than k
	private Key ceiling(Key k, Node n)
	{
		if(n == null)  return null;
		
		int cmp = k.compareTo(n.key);
		if(cmp == 0) return k;
		if(cmp >  0) return ceiling(k, n.right);
		else
		{
			Key left = ceiling(k, n.left);
			return left != null? left : n.key;
		}
	}
	
	public Key ceiling(Key k)
	{  return ceiling(k, root);  }
	
	
	public Iterable<Key> keys()
	{  
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue);
		
		return queue;
	}
	
	private void keys(Node n, Queue<Key> queue)
	{
		if(n == null)  return;
		
		keys(n.left, queue);
		queue.enqueue(n.key);
		keys(n.right, queue);
	}
	
	public Iterable<Key> keys(Key lo, Key hi)
	{
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		
		return queue;
	}
	
	private void keys(Node n, Queue<Key> queue, Key lo, Key hi)
	{
		if(n == null)  return;
		
		int cmpLo = lo.compareTo(n.key);
		int cmpHi = hi.compareTo(n.key);
		 				  
		if(cmpLo <=  0 && cmpHi >= 0) 
		{
			keys(n.left, queue, lo, hi);
			queue.enqueue(n.key);
			keys(n.right, queue, lo, hi);
		}			  
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		FileReader read = new FileReader("/Users/Lingqi/Downloads/Median.txt");
		Scanner    sc   = new Scanner(read);
		Queue<Integer> bag = new Queue<Integer>();
		
		RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
				
		int sum  = 0;
		while(sc.hasNextInt())
			bag.enqueue(sc.nextInt());			
		
		sc.close();
		
		long start = System.currentTimeMillis();
		for(int i : bag)
		{
			rbt.insert(i);
			int mid = (rbt.size() + 1) / 2;
			sum += rbt.select(mid - 1);
		}		
		
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(sum % 10000);
		
		Median<Integer> m = new Median<Integer>(rbt.size());
		
		sum = 0;
		start = System.currentTimeMillis();		
		for(int i : bag)
		{
			m.add(i);			
			sum += m.median();
		}		
		
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(sum % 10000);
		
		
		
	}
}
