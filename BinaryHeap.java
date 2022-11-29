//Jack Newman
//Line 62, fix if needed. 
package p4;
import java.util.Arrays;

public class BinaryHeap {
	//implements a binary heap where the heap rule is the value in the parent. Node is less than or equal to the values in the child nodes
	//the implementation uses parallel arrays to store the priorities and the trees. This is un-java like but I want you to have practice with parallel arrays. you must use this implementation
	
	
	//Jacks Notes: Heap rule. Parent <= child. So priotery goes to child with least value, and if they are the same. Then seniority? 
	int priority[];
	HuffmanTree trees[]; 
	int size;
	 
	
	public BinaryHeap(int s) { //Its the amount of unique charcter values each thing has. 
	 priority = new int[s+1]; 
	 trees = new HuffmanTree[s+1];
	 size = 0;
	 }
	
	
	
	public void removeMin() { //PRE: size != 0   //removes the priority and tree at the root of the heap
		priority[1] = priority[size]; trees[1] = trees[size--]; //This inserts this into the top spot, and when you insert size--. It inserts the size, then updates the size 
		removeMin(1); //I just my remove method with a paremeter. 
		priority[size+1] = 0; //making the last spot 0. I need for insert. If the while loop executes. Something is in the last spot. If not, I need to able to recongize I never placed something in there. So i make it zero in the last spot. 
		}  //My start of the recursive the call.
	
	//The hole at first represents the value you decremented from the bottom. Else it represents the spot of the child you moved up 
	private void removeMin(int hole) 
		{ 
			int child; 
			int tmpInt= priority[hole]; //I save values of the temp 
			HuffmanTree tmpTree = trees[hole]; 
			
			for(;hole*2 <= size; hole = child)  //Going until I go to a parent doesnt have any children. You go until the temp varible is the smallest. It either prematurely stops, and puts the temp in a higher spot. Or puts it in the last hole 
			{ 
				child = hole*2;  // line bellow. If child == size, then that means reached the bottom. If it less that means there more of the tree, and if there is more then it has a 2nd child
				if( child != size && priority[child+1] < priority[child] ) { child++;}//  If child 2 is less than or = to child two. Then you have to update to child 2.  
				if( priority[child]< tmpInt )//if the current child is less than the parent/hole 
				{ priority[hole] = priority[child]; trees[hole] = trees[child]; } //I set the hole to the last value
				else { break; } //Else the temp value is the smallest. So I have to break out, and assign it to the postion. 
			}
			priority[hole] = tmpInt; trees[hole] = tmpTree; 
		}
	
	
		public void insert(int p, HuffmanTree t) { //insert the priority p and the associated tree t into the heap //PRE !full()  
			int parent = size+1; // has to start from previous location + 1. //If it ever goes into the loop bellow that means p has to be somewhere else. 
			while(priority[parent/2] >= p && parent != 1) //Case for swapping the values constatnly. Checking the parents value is seeing if they or equal to or less than p. If parent ever gets to 1, its already done the work at one. Thus I dont want to divide it again. 
			{ 
					int tempint = priority[parent/2]; HuffmanTree temptree = trees[parent/2]; //saving temp values for these guys 
					priority[parent/2] = p; trees[parent/2] = t; //The new value now becomes the parent. 
					priority[parent] = tempint; trees[parent] =temptree; //The old value becomes the child. Or the last spot in the array intinally. 
					parent/=2; //dividing it by two here will always make it reach its parent. 	 
			}
			if(priority[size+1] == 0) { priority[size+1] = p; trees[size+1] = t;} //case in which there is no swapping needed. Then you insert into last postion. 
			size++; 					
		 	}
		
		public int getMinPriority() {return priority[1]; } //PRE: size != 0 //return the priority in the root of the heap 

		public HuffmanTree getMinTree() { return trees[1]; }  //PRE: size != 0  //return the tree in the root of the heap
		 
		public int getSize() { return size; } //return the number of values, (priority , tree) pairs, in the heap 
		 
		public boolean full() {  return trees[trees.length-1]==null; }  //return true if the heap is full otherwise return false
		
		
		
		
}
