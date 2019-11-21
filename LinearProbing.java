import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class LinearProbing{
	public static void main(String args[]) throws NumberFormatException, IOException {
		File file = new File("comp2.txt");
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		int size=Integer.parseInt(br.readLine()), item_size=Integer.parseInt(br.readLine());
		Hash_Table table = new Hash_Table(size);
		double x_axis[]=new double[item_size];
		int y_axis[]=new int[item_size];
		for(int i=0;i<item_size;i++) {
			double alpha=(i+1)/(double)size;
			x_axis[i]=1/(1-alpha);
			int access=table.insert(Integer.parseInt(br.readLine()));
			y_axis[i]=access;
		}
		br.close();		
		table.print(x_axis,  y_axis);
		//table.displayTable();
	}
}

class Hash_Table{
	int arraySize;
	int hashArray[];
	
	public Hash_Table(int size){
		arraySize= size;
		hashArray= new int[size];
		for(int i=0;i<size;i++)
			hashArray[i]=-1;
	}
	
	public int hash (int key) {
		return (key)%arraySize;
	}
	
	public int insert(int key) {
		int location= hash(key);
		int access=1;
		while(hashArray[location]!=-1) {//if location is not empty
			if(hashArray[location]==key) {//if key already exists
				System.out.println("Same ");
				break;
			}
			else {
				location++;
				access++;
				location%= arraySize;
			}
		}
		hashArray[location]=key;//inserting the key
		
		return access;//returning number of probes
	}
	
	public void displayTable() throws FileNotFoundException, UnsupportedEncodingException {
		//Printing the HashTable
		PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
	    writer.print("Table: ");
	    for (int j = 0; j < arraySize; j++) {
	      if (hashArray[j] != -1)
	        writer.print(hashArray[j] + " ");
	      else
	        writer.print("# ");
	    }
	    writer.close();
	}
	
	public void print(double[] x, int[] y) throws FileNotFoundException, UnsupportedEncodingException {
		//Writing x and y co-ordinates to text file for plotting
		PrintWriter writerx = new PrintWriter("x.txt", "UTF-8");
		for(int i=0;i<x.length;i++)
			writerx.println(x[i]);
		writerx.close();
		PrintWriter writery = new PrintWriter("y.txt", "UTF-8");
		for(int i=0;i<y.length;i++)
			writery.println(y[i]);
		writery.close();
	}
	
}