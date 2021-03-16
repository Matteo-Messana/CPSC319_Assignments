import java.util.ArrayList;

public class BinaryTree {
	private class Node{
		String data;
		int frequency;
		Node left, right;
		
		public Node(String d)
		{
			data = d;
			frequency = 1;
		}
	}
	Node root;
	int size;
	public BinaryTree()
	{
		size = 0;
	}
	
	
	public void add(String d) {
		Node e = new Node(d);
		if (root == null)
			root = e;
		else
			add(root, e);
	}
	
	private void add(Node test, Node current) {
		if (current.data.compareTo(test.data) > 0) 
		{
			if (test.right == null)
			{
				test.right = current;
				//System.out.println(current.data);
				return;
			}
			else
				add(test.right, current);
		}
		else if (current.data.compareTo(test.data) < 0) 
		{
			if (test.left == null)
			{
				test.left = current;
				//System.out.println(current.data);
				return;
			}
			else
				add(test.left, current);
		}
		else if(current.data.compareTo(test.data) == 0)
			test.frequency++;
			//System.out.println(test.frequency);
			return;
		
	}
	
	
	
	
	private int countPreorder(Node curr) {
		if (curr == null)
			return 0;
		
		return 1 + countPreorder(curr.left) + countPreorder(curr.right);
	}

	public int countPreorder() {
		return countPreorder(root);
	}
	
	
	
	private int uniquePreorder(Node curr)
	{
		if(curr == null)
		{
			return 0;
		}
		
		if(curr.frequency == 1)
		{
			return 1 + uniquePreorder(curr.left) + uniquePreorder(curr.right);
		}
		else
			return 0 + uniquePreorder(curr.left) + uniquePreorder(curr.right);
	}
	
	public int uniquePreorder()
	{
		return uniquePreorder(root);
	}
	
	
	private void preorderPrint(Node curr)
	{
		if(curr == null)
		{
			return;
		}
		System.out.println(curr.data);
		preorderPrint(curr.left);
		preorderPrint(curr.right);
	}
	
	public void preorderPrint()
	{
		preorderPrint(root);
	}
	
	private void inorderPrint(Node curr)
	{
		if(curr == null)
		{
			return;
		}
		inorderPrint(curr.left);
		System.out.println(curr.data);
		inorderPrint(curr.right);
	}
	
	public void inorderPrint()
	{
		inorderPrint(root);
	}
	
	private void postorderPrint(Node curr)
	{
		if(curr == null)
		{
			return;
		}
		postorderPrint(curr.left);
		postorderPrint(curr.right);
		System.out.println(curr.data);
	}
	
	public void postorderPrint()
	{
		postorderPrint(root);
	}
	
	
	private int largestPreorder(Node curr)
	{
		if(curr == null)
		{
			return 0;
		}
		
		int large = curr.frequency;
		int largeL = largestPreorder(curr.left);
		int largeR = largestPreorder(curr.right);
		
		if(large < largeR)
		{
			large = largeR;
		}
		if(large < largeL)
		{
			large = largeL;
		}
		return large;
	}
	
	public int largestPreorder()
	{
		return largestPreorder(root);
	}
	
	
	
	private void findLargest(Node curr, int largest)
	{
		if(curr == null)
		{
			return;
		}
		
		if(curr.frequency == largest)
		{
			System.out.println(curr.data + " = " + largest + " times");
		}
		findLargest(curr.left,largest);
		findLargest(curr.right,largest);
	}
	
	public void findLargest(int largest)
	{
		findLargest(root, largest);
	}
	
	
	private int findWord(Node curr, String word)
	{
		if(curr != null)
		{
			if(curr.data.equals(word))
			{
				return curr.frequency;
			}
			else	
			{
				int found = findWord(curr.right, word);
				if(found == -1)
				{
					found = findWord(curr.left, word);
				}
				return found;
			}
		}
		else
		{
			return -1;
		}	
	}
	
	public int findWord(String word)
	{
		return findWord(root, word);
	}
	
	private int maximumDepth(Node curr)
	{
		if(curr == null)
		{
			return 0;
		}
		int lDepth = maximumDepth(curr.left);
		int rDepth = maximumDepth(curr.right);
		
		if(lDepth > rDepth)
			return (lDepth + 1);
		else 
			return(rDepth + 1);
	}
	
	public int maximumDepth()
	{
		return maximumDepth(root);
	}
}
