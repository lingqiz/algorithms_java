package mincont;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


// Data structure for undirected graph 
public class AdjacencyListMincont implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7396407700820987399L;

	private static class Vertice implements java.io.Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -1026380421433249835L;
		private ArrayList<Integer> edges;
		
		public Vertice()
		{
			edges = new ArrayList<Integer>();
		}
		
		public ArrayList<Integer> connectedEdges()
		{
			return edges;
		}
		
		public int degree()
		{
			return edges.size();
		}
		
		public void addEdge(int edge)
		{
			this.edges.add(edge);
		}
	}
	private static class Edge    implements java.io.Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7462600697053193709L;
		// Tail(smaller object) --->  Head(large object) 
		private int tail;
		private int head;
		
		public Edge(int tail, int head)
		{
			this.tail = tail;
			this.head = head;
		}
		
		public int getTail()
		{
			return this.tail;
		}
		
		public int getHead()
		{
			return this.head;
		}
		
		public boolean isEdge(int tail, int head)
		{
			return this.tail == tail && this.head == head;
		}
		
		public void changeEdge(int tail, int head)
		{
			this.tail = tail;
			this.head = head;
		}
		
		public void setTail(int tail)
		{
			this.tail = tail;
		}
		
		public void setHead(int head)
		{
			this.head = head;
		}
	}

	private ArrayList<Vertice> vertices;
	private ArrayList<Edge>    edges;
	private Random rd;
	
	public AdjacencyListMincont()
	{
		vertices = new ArrayList<Vertice>();
		edges    = new ArrayList<Edge>();
	}
	
	public void addConnection(int tail, int head)
	{
		int edge = edges.size();
		edges.add(new Edge(tail, head));
		
		this.addConnectionVertice(tail, edge);
		this.addConnectionVertice(head, edge);
	}
	
	private void addConnectionVertice(int vertice, int edge)
	{
		if(vertice >= vertices.size())
		{
			int size = vertices.size();
			for(int i = size; i <= vertice; i++)
			{
				Vertice v = new Vertice();
				vertices.add(v);
			}
		}
		
		Vertice v = vertices.get(vertice);
		v.addEdge(edge);
	}
		
	public int randMincut()
	{
		this.rd = new Random(System.currentTimeMillis());
		while(vertices.size() > 2)
			mergeVertices();
		
		Vertice v1 = vertices.get(0);				
		return v1.degree();
	}
	
	private void mergeVertices()
	{
		int choose = rd.nextInt(edges.size());
		Edge edge  = edges.get(choose);		
		
		int v1 = edge.getTail();
		int v2 = edge.getHead();
		
		int newVertice = vertices.size();
		Vertice newVer = new Vertice();   
		vertices.add(newVer);
		
		for(int i = 0; i < this.edges.size(); i++)
		{
			Edge e = edges.get(i);
			if(e.isEdge(v1, v2))
			{				
				deleteEdge(i);
				i = i - 1;
			}
			else if(e.getTail() == v1 || e.getTail() == v2) 
			{
				newVer.addEdge(i);
				e.changeEdge(e.getHead(), newVertice);
			}
			else if(e.getHead() == v1 || e.getHead() == v2)
			{
				newVer.addEdge(i);
				e.changeEdge(e.getTail(), newVertice);
			}
		}
		
		deleteVertice(v1);
		deleteVertice(v2 - 1);
		
	}
	
	// O(m) Operation 
	private void deleteVertice(int vertice)
	{
		for(Edge e : edges)
		{
			if(e.getTail() > vertice)
				e.setTail(e.getTail() - 1);
			
			if(e.getHead() > vertice)
				e.setHead(e.getHead() - 1);
		}
		
		vertices.remove(vertice);
	}
	
	// O(m) Operation 
	private void deleteEdge(int edge)
	{
		for(Vertice v : vertices)
		{
			ArrayList<Integer> edges = v.connectedEdges();
			for(int i = 0; i < edges.size(); i++)
			{
				int connected = edges.get(i);
				if(connected > edge)
					edges.set(i, connected - 1);
			}
		}
		
		this.edges.remove(edge);
		
	}
	
	public int n()
	{
		return this.vertices.size();
	}

	public int m()
	{		
		return this.edges.size();
	}
	
	@Override
	public AdjacencyListMincont clone()
	{				
		try 
		{
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ObjectOutputStream output    = new ObjectOutputStream(buffer);
			output.writeObject(this);
			
			ByteArrayInputStream reader = new ByteArrayInputStream(buffer.toByteArray());
			ObjectInputStream input     = new ObjectInputStream(reader);
			
			AdjacencyListMincont copied = (AdjacencyListMincont) input.readObject();
			return copied;
		} 
		
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) throws IOException
	{
		AdjacencyListMincont graph = new AdjacencyListMincont();
				
		File inputFile = new File("/Users/Lingqi/Downloads/kargerMinCut.txt");
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		
		String line = null;		
		Scanner sc  = null;
		
		while((line = reader.readLine()) != null)
		{
			sc = new Scanner(line);
			int lineNumber = sc.nextInt() - 1;
			
			while(sc.hasNextInt())
			{
				int input = sc.nextInt() - 1;
				if(input > lineNumber)
					graph.addConnection(lineNumber, input);
			}
		}
		
		sc.close();
		reader.close();
			
		int minCut = Integer.MAX_VALUE;
		for(int i = 0; i < graph.n(); i++)
		{
			int min = graph.clone().randMincut();
			if(min < minCut)
				minCut = min;
		}
		
		System.out.println("Final Result : " + minCut);
		
		
		
		/*
		System.out.println("Vertice");
		for(Vertice v : graph.vertices)
		{
			for(Integer edge : v.edges)
				System.out.print(edge + " ");
			
			System.out.println();
		}
		
		System.out.println("Edge");
		for(Edge e : graph.edges)		
			System.out.print(e.tail + "->" + e.head + "\n");
		*/
		
	}
	
	
	
}
