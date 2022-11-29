//Jack Newman
package p4;
import java.io.*;
import java.util.*;

public class HuffmanEncode {
	private int totalSize; //Way to keep track of the total size. 
	
	
	
	//The point of this private method is just find the frequency of each charcter, the total amount of characters. Then Add stuff to the binaryheap. Then build the huffmanTree. 
	private HuffmanTree builder(String in) throws IOException  //I made this class. Cause I think makes it neater, and it cuts out varibles I dont need anymore. 
	{ 
		totalSize = 0;  //Keeps track of how many characters there are. 
		int frequency[] = new int[128]; //Each number in the array coralates to a chars int value
		BinaryHeap heapBuilder = new BinaryHeap(127); //This constructs a binary heap class, and is a way i can acess its varibles
	
		FileReader fr = new FileReader(in); //gets a file reader. 
		BufferedReader bw = new BufferedReader(fr); 
		int curInt = bw.read(); //Read the first charcter. Save it because I have to make my loop based off what the int value is
		
		while( curInt != -1){ //Read will return a negative one if it ends  
			frequency[curInt]++; //Incrementing frequency of certain value
			totalSize++; //incrementing size 
			curInt = bw.read();
			}
		bw.close(); //closing the readrer 
	
		for(int i = 0; i < 128; i++ ){ //I go through my int array. I add the frequency and its associated tree adding it to the binary heap. 
			if(frequency[i] == 0) { continue; } 
			heapBuilder.insert(frequency[i], new HuffmanTree((char) i)); //dont really have to check if the thing is full since the set size
		}
		
		while( heapBuilder.size!= 1) {	//creating the actaull huffmantree
				int first = heapBuilder.getMinPriority();  HuffmanTree firstTree = heapBuilder.getMinTree(); heapBuilder.removeMin(); //Saving the top varibles, then removing them 
				HuffmanTree secoundTree = heapBuilder.getMinTree(); //Saving the 2nd tree. 
				int newHighest = first + heapBuilder.getMinPriority();// Dont need save the 2nd top one. 
				heapBuilder.removeMin(); //Removing off priority. 
				HuffmanTree temptree = new HuffmanTree(firstTree, (char) 128, secoundTree); //Build a new tree out of the two top prioritys, and add a nonleaf as its root. 
				heapBuilder.insert(newHighest, temptree); //Inserting the new tree back into the priotrity queue. 
			} 
		return heapBuilder.getMinTree(); //THE TREE IS BUILT
		// 1Find the frequency of each character in the input file //done 
		//2. Build a Huffman tree from the frequency data //done 
	}
	
	
	public HuffmanEncode(String in, String out) throws IOException {  //Implements the main flow of your program in is the name of the source file out is the name of the output file Add private methods and instance variables as needed
		
		HuffmanTree trueTree = builder(in);  
		String[] pathToLeaves = trueTree.pathsToLeaves(); //3. Traverse the Huffman tree and build the encodings for each character found in the input file //
		HuffmanOutputStream filewriter = new HuffmanOutputStream(out, trueTree.toString(), totalSize);  //4 Write a representation of the Huffman tree to the output file//Write the number of characters in the input file to the output file

		
		FileReader fr = new FileReader(in); //gets a file reader. 
		BufferedReader bw = new BufferedReader(fr); 
		
		int currChar = bw.read(); //I will read each character. Its Associted String. Then pick it appart. 
		while( currChar != -1){ //Read will return a negative one if it ends  
			if(pathToLeaves[currChar] == null ) { currChar = bw.read(); continue; }  //If the char doesnt have a string it doesnt exist so skip 
			String temp = pathToLeaves[currChar];//Building the string 
			for(int i = 0; i < temp.length(); i++) { filewriter.writeBit(temp.charAt(i));} //I write it at each postion.
			currChar = bw.read();
			}
		bw.close(); //closing the readrer 
		filewriter.close(); 
		//6. For each character in the input file, write the bits of the Huffman encoding to the output file.
		 }
		
	
	public static void main(String args[]) throws IOException {
		//args[0] is the name of the source file
		 //args[1] is the name of the output file
		 new HuffmanEncode(args[0], args[1]);
		 //do not change anything here
		 }
	
	
}
