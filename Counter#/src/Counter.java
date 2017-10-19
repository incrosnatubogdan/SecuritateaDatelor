import java.util.Scanner;
import java.io.*;

public class Counter {

	public static void main(String[] args) throws IOException{
		
		Scanner input = new Scanner(System.in);

		System.out.print("Nume Fisier: ");
		String fileName = input.nextLine();

		File file = new File(fileName + ".txt");
		
		if(!file.exists()){
			System.out.println("Fisierul " + fileName + ".txt nu exista.");
			System.exit(0);
		}
		Scanner fileInput = new Scanner(file);

		System.out.println("Caracter: ");
		String charInput = input.nextLine();
		char character = charInput.charAt(0);
		
		String line; 
		
		int count = 0;
		
		while(fileInput.hasNext()){
			line = fileInput.nextLine();
			for(int j=0; j<line.length(); j++){
				if(line.charAt(j)==character){
					count += 1;
				}
			}
		}
		
		System.out.println("Caracterul " + character + " apare de " + count + " ori.");
		
		fileInput.close();
	}
}