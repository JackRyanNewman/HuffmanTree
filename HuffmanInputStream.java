//Jack Newman
package p4;
import java.io.*; 



public class HuffmanInputStream {
	 private String tree;
	 private int totalChars;
	 private DataInputStream d; 
	 
	 private int curCall = 0; //To keep track of how many calls I have. 
	 private int bitFinder; 
	 private int currPower = 7; 
 
	 private int bit; //This value is the indivual bit. 
	
	 
	 
	 public HuffmanInputStream(String filename) throws IOException { 
		 d = new DataInputStream(new FileInputStream(filename));
		 tree = d.readUTF(); 
		 totalChars = d.readInt(); //Dont have to translate these guys
			 try { bitFinder = (int) d.readUnsignedByte();} //Catching a STUPID ERROR THAT I HATE 
			 catch (IOException e){ e.printStackTrace(); } 
	 } 
		 
		 

		 public int readBit() { //returns the next bit is the file the value returned will be either a 0 or a 1. you will need to read each byte from the file (use readUnsignedByte) after 8 calls to readBit you will need to read another byte
			if(curCall == 8) { //checking to see I have made 8 calls.  
				try { bitFinder = (int) d.readUnsignedByte();} 
				catch (IOException e){}  
				curCall = 0;
				currPower = 7; 
			}
			bit = (int) (bitFinder/((int)Math.pow(2,currPower))%2); 
			currPower--; 
			curCall++; 
			return bit; 
			
			 }
		 
		 public String getTree() {return tree; }
		 
		 public int getTotalChars() {return totalChars;} //return the character count read from the file
		 
		 public void close() throws IOException { d.close(); }  //close the DataInputStream  

		 
}
