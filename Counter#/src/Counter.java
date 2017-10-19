import java.util.Scanner;
import java.io.*;

public class Counter {
	
	public static void main(String[] args) throws Exception {

		 int[] array = new int[26];
	        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
	        int ch;
	        while((ch = br.read()) != -1) {
	            if (ch > 64 && ch < 97) 
	                ch += 32; 
	            if(-1 < ch-97 && ch-97 < 26)
	                array[ch-97] += 1;
	        }
	        br.close();
	        int i = 0;
	        for (char chr = 'a'; chr <= 'z'; chr++) {
	            System.out.printf("%s --> %d\n", chr, array[i]);
	            i++;
	        }
	}
}