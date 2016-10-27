package search.tree;

/**
 * 	Dynamic Ordered Array 
 */

import data.structure.Queue;
public class RedBlackTree<T extends Comparable<T>> 
{
	private final static boolean RED   = true;
	private final static boolean BLACK = false;
	
	private class Node
	{
		T key;
		
		Node left, right;
		int size;
		
		boolean color;
		
		public Node(T item, boolean c)
		{
			this.key = item;
			this.color = c;
		}
	}
	
	private Node root = null;
	public boolean isEmpty() 
	{  return this.root == null;  }
	
	private Node search(T key, Node node)
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
		x.color       = !x.color;
		x.left.color  = !x.left.color;
		x.right.color = !x.right.color;
	}
	
	
	public boolean contains(T key)
	{
		Node result = search(key, root);
		if(result == null) return false;
		
		return true;
	}   
	
	public void insert(T k)
	{
		root = insert(k, root);		
		root.color = BLACK;
	}
	
	private Node insert(T k, Node n)
	{
		if(n == null)  
		{
			Node newNode = new Node(k, RED);
			newNode.size = 1;
			return newNode;
		}
		
		int cmp = k.compareTo(n.key);		
		if(cmp > 0)
		{
			Node newRight = insert(k, n.right);
			n.right = newRight;
		}
		else
		{
			Node newLeft = insert(k, n.left);
			n.left = newLeft;
		}
		
		return fixUp(n);
	}
	
	private Node fixUp(Node n)
	{
		if(isRed(n.right) && !isRed(n.left))      n = rotateLeft(n);
		if(isRed(n.left)  && isRed(n.left.left))  n = rotateRight(n);
		if(isRed(n.left)  && isRed(n.right))      flipColor(n);
		
		n.size = nodeSize(n.left) + nodeSize(n.right) + 1;
		return n;
	}	
	
	private Node moveRedLeft(Node h)
	{
		flipColor(h);
		if(isRed(h.right.left))
		{
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColor(h);
		}
		return h;
	}
	
	private Node moveRedRight(Node h)
	{
		flipColor(h);
		if(isRed(h.left.left))
		{
			h = rotateRight(h);
			flipColor(h);
		}		
		return h;
	}
	
	private Node deleteMin(Node h)
	{
		if(h.left == null) return null;
		if(!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);
		
		h.left = deleteMin(h.left);
		return fixUp(h);
	}
	
	public void deleteMax()
	{
		root = deleteMin(root);
		root.color = BLACK;
	}
	
	public void delete(T elem)
	{
		root = delete(root, elem);
		root.color = BLACK;
	}
	
	private Node delete(Node h, T elem)
	{
		if(elem.compareTo(h.key) < 0)
		{
			if(!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			
			h.left = delete(h.left, elem);
		}
		else
		{
			if(isRed(h.left))
				h = rotateRight(h);
			if(elem.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if(!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if(elem.compareTo(h.key) == 0)
			{
				h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			}			
			else h.right = delete(h.right, elem);
			
		}
		
		return fixUp(h);		
	}
	
	public int size()
	{  return nodeSize(root);  }
	
	public int size(T lo, T hi)
	{  return rank(hi) - rank(lo);  }
	
	private int nodeSize(Node n)
	{  
		if(n == null) return 0;
		else          return n.size;
	}
	
	public T select(int i)
	{  return select(i + 1, root);  }
	private T select(int i, Node n)
	{
		if(n == null) throw new IndexOutOfBoundsException("Index: " + i);
		
		if(nodeSize(n.left) + 1 == i)  return n.key;
		if(i <= nodeSize(n.left))      return select(i, n.left);
		else                      return select(i - nodeSize(n.left) - 1, n.right);
	}
	
	public int rank(T k)
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
	
	public T max()
	{
		Node n  = root;
		while(n.right != null)
			n = n.right;
		
		return n != null? n.key : null;		
	}
	
	public T min()
	{
		Node n = root;
		while(n.left != null)
			n = n.left;
		
		return n != null? n.key : null;
	}
	
	private Node min(Node x)
	{
		while(x.left != null)
			x = x.left;
		
		return x;
	}
	
	// The largest Key in the tree that is less than k
	private T floor(T k, Node n)
	{
		if(n == null)  return null;
		
		int cmp = k.compareTo(n.key);
		if(cmp == 0) return k;
		if(cmp <  0) return floor(k, n.left);
		else
		{
			T right = floor(k, n.right);
			return right != null? right : n.key;
		}			
		
	}
	
	public T floor(T k)
	{  return floor(k, root);  }
	
	// The smallest Key in the tree that is larger than k
	private T ceiling(T k, Node n)
	{
		if(n == null)  return null;
		
		int cmp = k.compareTo(n.key);
		if(cmp == 0) return k;
		if(cmp >  0) return ceiling(k, n.right);
		else
		{
			T left = ceiling(k, n.left);
			return left != null? left : n.key;
		}
	}
	
	public T ceiling(T k)
	{  return ceiling(k, root);  }
	
	
	public Iterable<T> keys()
	{  
		Queue<T> queue = new Queue<T>();
		keys(root, queue);
		return queue;
	}
	
	private void keys(Node n, Queue<T> queue)
	{
		if(n == null)  return;
		
		keys(n.left, queue);
		queue.enqueue(n.key);
		keys(n.right, queue);
	}
	
	public Iterable<T> keys(T lo, T hi)
	{
		Queue<T> queue = new Queue<T>();
		keys(root, queue, lo, hi);
		
		return queue;
	}
	
	private void keys(Node n, Queue<T> queue, T lo, T hi)
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
	
	public T get(int i)
	{  return select(i + 1, root);  }
	
	public void put(T t)
	{  insert(t);  }
	
	
	
	
}