//Jack Newman
package p4;
import java.io.*;
import java.util.*;

public class HuffmanDecode {

	public HuffmanDecode(String in, String out) throws IOException {
		 HuffmanInputStream decoder = new HuffmanInputStream(in);//The in file is the encoded file of the Huffman. 
		 String strTree = decoder.getTree(); //I use to return the string rep of it 
		 int totalchar = decoder.getTotalChars(); //i use it to get the total amount of chars. //Read the number of characters from the input file
		 HuffmanTree tree = new HuffmanTree(strTree, (char) 128);	 // Read the representation of the Huffman tree from the input file and rebuild the Huffman tree
		 
		

		 BufferedWriter bw = new BufferedWriter( new FileWriter(out));//the 2nd paremter appends the file allowing you write into the file without delating its previous contents. 
		
		 
		 int charCount = 0; //char counter should start zero because your havent adding any charcters in yet. This is to make sure to stop when I have written every character 
		 int postion = 0;
		 tree.moveToRoot(); //Setting a the current node pointer to the root. Which exists within our newly constructed huffmanclass 
	
		 
		 while( charCount < totalchar) //Will end when we insert every character. 	 //Starting at the root of the Huffman tree, read each bit from the input file and walk down the Huffman tree.
		 { 
			 int binary = decoder.readBit(); 
			 if(binary == 0) //If the bit read is 0. That means I go left in my huffmantree. 
				{
				 tree.moveToLeft(); //I move it left  
				 if(tree.atLeaf()) { bw.write(tree.current()); tree.moveToRoot(); charCount++; } //If its at a leaf. Then we know we found the value. So we write the charcter to the file. We move current back to the root, cause we have to restart. Then we increment the char counter 
				} 
			  else //(fullByte[postion] == 1) 
				{ 
				tree.moveToRight(); //I move it right 
				if(tree.atLeaf()) { bw.append(tree.current()); tree.moveToRoot(); charCount++; } 
						} 
				 postion++;  //Then I update the postion within the array.
				 bw.flush(); 
		 }
		 decoder.close(); //Close the HuffmanOutputStream object. 
		 bw.close(); //Close the Filewriter. 
		} 
		
	
	public static void main(String args[]) throws IOException {
		//args[0] is the name of a input file (a file created by Huffman Encode)
		//args[1] is the name of the output file for the uncompressed file
		 new HuffmanDecode(args[0], args[1]);
		 //do not add anything here
		 }
	
}
