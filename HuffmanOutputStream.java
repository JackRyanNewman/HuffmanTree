//Jack Newman
package p4;
import java.io.*;
public class HuffmanOutputStream {
	private DataOutputStream d;
	private int curByte = 0 ; //A byte can be represented as a int. So I can find the int of the Byte. Then I can find how many 1's and zeros there are!!! 
	private int curCall = 0; //This is to keep track of how many calls I have done. 
		
	public HuffmanOutputStream(String filename, String tree, int totalChars) {
		 try 
			 {
				 d = new DataOutputStream(new FileOutputStream(filename));
				 d.writeUTF(tree); //Converting the tree string into binary using modified UTF-8 encoding. 
				 d.writeInt(totalChars); //This, writes the total chars as a 4 bytes in binary. So we can store it. 			 
			 }
		 catch (IOException e) {} 
	 } 

	 public void writeBit(char bit) throws IOException {  //PRE:bit == '0' || bit == '1'  /You need to fill a byte with bits and after every 8 calls to writeBit //you must write the byte to the file 
		 if(curCall == 8) {   
			 	 d.write(curByte);
				 curCall = 0; 
				 curByte = 0; 
			  } 
		 curByte = curByte*2 +( (int) bit - '0'); // The bit will either be a 0 or a 1. Char 1 casted as aint is a 49, and 0 is 48. This represents, the math conversaion rate of a bits in binary. 
		 curCall++; 
	 } 
//if you ever go beyond the 8 limit, and you have some extra characters. You need to read the binary in order, and if you just add one extra charcter 1. The curByte count = 1. Which will produce a bits 00000001
//You need to read it as 10000000. Then ingore the 0's. that value is 2^7. 	 
		 public void close() throws IOException {  //write final byte (if needed); 	 //close the DataOutputStream
			if(curCall == 0) { d.close(); return; } //There isnt a need for any padding. 
			if( curByte == 0 ) { d.write(curByte); d.close(); return; } //No point in doing loop bellow. If any part of curbyte is 0. That means the partial part of the byte is 0. Padding it will always result in a total of 0
			while(curCall < 8) { curByte = curByte*2; curCall++; } //MIGHT NEED TO CHECK THIS WHILE LOOP >//So to pad by adding zeros infront. That increases the int value of the byte. So I do this by doubling it evertime. I also know the remainder of something 2, when even will always result in 0. So it will balance out later. 
			d.write(curByte); //Then I write the bytevalue. 
			d.close(); //Then I close it. 
		 } 
}		
