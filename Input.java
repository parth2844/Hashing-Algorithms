import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random; 
public class Input {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		int size=4000;
		//double load_factor=0.4;
		int item_size= 2000;
		Random rand = new Random();
		PrintWriter writer = new PrintWriter("comp.txt", "UTF-8");
		writer.println(size);
		writer.println(item_size);
		for(int i=0;i<item_size;i++) {
			int x=rand.nextInt(1000000);
			if(map.get(x)==null) {
				map.put(x, 1);
				writer.println(rand.nextInt(1000000));
			}
			else {
				i--;
				System.out.println(x);
			}
		}
		writer.close();
	}

}
