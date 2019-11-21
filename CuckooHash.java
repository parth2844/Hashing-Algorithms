import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class CuckooHash {
	static int elements[], hashArray1[], hashArray2[], primes[], y_axis[];
	static double x_axis[];
	static int size,item_size,val_in,k1,k2,pno=0,step=1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		File file = new File("comp2.txt");
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		size=Integer.parseInt(br.readLine());
		item_size=Integer.parseInt(br.readLine());
		elements= new int[item_size];
		y_axis= new int[item_size];
		x_axis= new double[item_size];
		for(int i=0;i<item_size;i++) {
			elements[i]=Integer.parseInt(br.readLine());
			double alpha=(i+1)/(double)size;
			x_axis[i]=1/(1-alpha);
		}
		br.close();
		primes = new int[160];
		generateprimes(1000);
		hashArray1 = new int[size/2];
		hashArray2 = new int[size/2];
		
		init(step);
		display();
		print(x_axis,y_axis);
	}
	
	public static void generateprimes(int n) {
		int j=0;
		for (int num = 23; num <= n; num++){
            boolean isPrime = true;
            for (int i=2; i <= num/2; i++){
                if ( num % i == 0){
                    isPrime = false;
                    break;
                }
            }
            if ( isPrime == true ) {
                primes[j]=num;
                j++;
            }
        }
		
	}
	
	public static void init(int step) {
		//initalizing hash tables 
		for(int i=0;i<hashArray1.length;i++)
			hashArray1[i]=-1;
		for(int i=0;i<hashArray2.length;i++)
			hashArray2[i]=-1;
		k1=primes[pno];
		for(int i=0;i<item_size;i++,step=1) {
			int access=insert(elements[i],1,0,step);
			y_axis[i]=access;
		}
		//System.out.println("Insertion Complete");
		
	}
	
	public static int hash1(int key) {
		return (key)%hashArray1.length;
	}
	
	public static int hash2(int key) {
		return (key/k1)%hashArray2.length;
	}
	
	public static void display() {
		//displaying hash tables
		System.out.println("Table 1");
		for(int i=0;i<hashArray1.length;i++)
			System.out.print(hashArray1[i]+" ");
		System.out.println();
		System.out.println("Table 2");
		for(int i=0;i<hashArray2.length;i++)
			System.out.print(hashArray2[i]+" ");
	}
	
	public static void print(double[] x, int[] y) throws FileNotFoundException, UnsupportedEncodingException {
		// writing x and y values to file for plotting
		PrintWriter writerx = new PrintWriter("x.txt", "UTF-8");
		for(int i=0;i<x.length;i++)
			writerx.println(x[i]);
		writerx.close();
		PrintWriter writery = new PrintWriter("y.txt", "UTF-8");
		for(int i=0;i<y.length;i++)
			writery.println(y[i]);
		writery.close();
	}
	
	public static int insert(int key, int array, int count, int step) {
		if(hashArray1[hash1(key)]!=key && hashArray2[hash2(key)]!=key) {
			if(count==0) 
				val_in=key;
			if(key==val_in) count++;
			if(count<3) {// checking for infinite loop
				if(array==1) {//hashing in array 1
					int location= hash1(key);
					if(hashArray1[location]==-1) {
						hashArray1[location]=key;
						return step;
					}
					else {
						int temp= hashArray1[location];
						hashArray1[location]= key;
						step=insert(temp, 2, count,step+1);
					}
				}
				else if(array==2) {//hashing in array 2
					int location= hash2(key);
					if(hashArray2[location]==-1) {
						hashArray2[location]=key;
						return step;
					}
					else {
						int temp= hashArray2[location];
						hashArray2[location]= key;
						step=insert(temp, 1, count,step+1);
					}
				}
			}
			else {
				System.out.println("Infinite loop at "+key+" Now Rehashing");//Infinite loop detected
				pno++;
				//System.out.println(primes[pno]);
				if(pno==primes.length-1)
					System.exit(0);
				init(step);
			}
		}
		return step;
	}

}
