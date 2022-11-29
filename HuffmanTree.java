//Jack Newman
package p4;
import java.util.*;
public class HuffmanTree {
	//DO NOT include the frequency or priority in the tree
	
	private Node root;
	private Node current; //this value is changed by the move methods
	private String[] path; //A string array, that I constanlty update through recursion. 
	
	 private class Node 
	 {
		 private Node left;
		 private char data;
		 private Node right;
		 private Node(Node L, char d, Node R)  
		 	 {
				 left = L;
				 data = d;
				 right = R;
			 }
	 }
	
	
	public HuffmanTree() {
		 root = null;
		 current = null;
		 }
	
	public HuffmanTree(char d) {  root = new Node(null, d, null); } //makes a single node tree 
		
		
	
	    //Because its postorder and I know nonleaf i can reconstruct the tree. I will do this by adding it the top of a stack, and time I come across a nonleaf. I just pop 3 items off. Combining them into a tree. Then countine to do so until I reach the end of the array. 
	    //Because its based off arraysize, it will naturally progress towards the end and always combine them. If its a true huffman tree it will be complete. 
		//Assumes t represents a post order representation of the tree as discussed in class
		//nonLeaf is the char value of the data in the non-leaf nodes
	 	//in the following I will use (char) 128 for the non-leaf value
		
	public HuffmanTree(String t, char nonLeaf) {
		
		Stack<HuffmanTree> list = new Stack<>();										//using the stack class so I can add everything on the way I want it to. 						
		for(int currPostion = 0; currPostion < t.length(); currPostion++ ) 		//I know the specific size, thus a forloop, and I base it off 0 to maxium postion. length is really maxium postion accesible of array + 1.   It will ensure, As soon as I update the currpostion to length that will cause an error. It will exit the loop  		
		{ 
			if( t.charAt(currPostion) == nonLeaf ) //the case in which the item I just pushed onto the top is the nonleaf. 
			{ 
				HuffmanTree right = list.pop(); //The first one will be right 
				HuffmanTree left =  list.pop(); 
				list.push( (new HuffmanTree(left,t.charAt(currPostion), right)) ); //I create a new tree with the nonleaf at the center. 
			}
			else { list.push((new HuffmanTree(t.charAt(currPostion)))); } //It creates a singlar huffmanTree with the char at index currPostion. 
		} 
		root = list.pop().root; 
		 }

		public HuffmanTree(HuffmanTree b1, char d, HuffmanTree b2) { root = new Node(b1.root, d,b2.root); } //makes a new tree where b1 is the left subtree and b2 is the right subtree. d is the data in the root. Do not make a copy of b1 and b2 
		
		//the move methods to traverse the tree
		//the move methods change the value of current
		 //use these in the decoding process
		
		public void moveToRoot() {current = root; }//change current to reference the root of the tree 
		
		public void moveToLeft() { if(current.left != null ) { current = current.left; }}  //PRE: the current node is not a leaf //change current to reference the left child of the current node
		 
		public void moveToRight() { if(current.right != null ) { current = current.right; } }  //PRE: the current node is not a leaf  //change current to reference the right child of the current node 
		
		public boolean atRoot() { return current == root; } //boolean value. If current is equivlent to root. It returns true. Else false.  //returns true if the current node is the root otherwise returns false
			 
		public boolean atLeaf() { return current.left == null && current.right == null; }	//returns true if current references a leaf other wise returns false
			
		public char current() { return current.data; } //returns the data value in the node referenced by current 
				
		
		
		public String[] pathsToLeaves() { /*returns an array of 128 strings (some of which could be null) with all paths from the root to the leaves Each string will be a string of 0s and 1s. Store the path for a particular leaf in the array.  at the location of the leaf value’s character code  */  
		path = new String[128]; 
				Node curr = root; 
				String location = ""; 
				finder(curr, location); 	
		return path; 
		} 
		
	
		//A recusrive method That hits everyspot in the list. It checks if its at a leaf. If so, it casts the char of the data. So i can figure out what postion of the array im updating. 
		//Else then I go into left and then right, and pass through the updated string postion. 
		//The huffman tree. Nonleaf node will always have two children. Children can have no nodes. So there will never be a case of a non full tree. 
		private void finder(Node curr, String location) 
		{ 
			if(curr.left == null && curr.right == null) // your at a leaf. Techinally I only need one side. Cause a leaf only have a null reference. 
				{ 
				int x = (int) curr.data; 
				path[x] = location; //array casts char into int. 
				} 
			else //else if your at a nonleaf. Then you go into update the location, and descend further down. 
			{ 
				finder(curr.left, location+0); 
				finder(curr.right, location+1);
			} 
		} 
			
		
		
		public String toString() { //returns a string representation of the tree using the postorder format // discussed in class 
			if(root == null ) { return null; } //Parnoid base case. 
			else { return toString(root); } } 
		
		//this is a private recursive method. Descends into the list in postorder and adds it in post order.
		private String toString(Node x) 
		{
			String s = ""; 
			if(x == null ) {return ""; } 
			s+=toString(x.left); 
			s+=toString(x.right); 
			s+=x.data;
			return s; 
		}

	
			
			 
		
		
		
}
