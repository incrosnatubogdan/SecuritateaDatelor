import java.util.stream.IntStream;

public class Beaufort {

	private static class MyCharStream {
		
		private IntStream stream;
		
		public MyCharStream() {
			stream = IntStream.range(0, 0);
		}
		
		public MyCharStream concat(IntStream s) {
			
			this.stream = IntStream.concat(this.stream, s);
			return this;
		}
		
		public MyCharStream concat(int[] s) {
			
			return this.concat(IntStream.of(s));
		}
		
		public MyCharStream concat(char[] s) {
			
			int[] sInt = new int[s.length];
			for (int i = 0; i < s.length; i++) {
				
				sInt[i] = s[i];
			}
			
			return this.concat(sInt);
		}
		
		public char[] toArray() {
			
			char[] result = null;
			
			int[] intArray = this.stream.toArray();
			
			result = new char[intArray.length];
			for (int i = 0; i < intArray.length; i++) {
				
				result[i] = (char)intArray[i];
			}
			
			return result;
		}
	}
	
	private static void shift(char[] alphabet) {

		char first = alphabet[0];
		
		for (int i = 0; i < alphabet.length - 1; i++) {
			
			alphabet[i] = alphabet[i + 1];
		}
		
		alphabet[alphabet.length - 1] = first;
	}
	
	//public static void main( String[] args ) {
	public static void Run() {
		
		System.out.println("BeaufortCipher");
		System.out.println();
			
        String clearText = "texttocipher";
        System.out.println("Original Text: " + clearText);
        System.out.println();

        String keyword = "alltech";
        System.out.println("Keyword: " + keyword);
        
		char[] alphabet = 
				new MyCharStream()
				.concat(IntStream.range('a', 'z' + 1))
				.toArray();
		
		char[][] tabulaRecta = new char['z' - 'a' + 1][];
		for (int i = 0; i < tabulaRecta.length; i++) {
			
			tabulaRecta[i] = alphabet.clone();
			shift(alphabet);
		}
		
        String cipherText = Cipher(clearText, tabulaRecta, keyword);
        System.out.printf("Ciphered Text: %s\n", cipherText);

        System.out.println();
        
        String decipherText = Decipher(cipherText, tabulaRecta, keyword);
        System.out.printf("Deciphered Text: %s\n", decipherText);		
	}

	private static String growToTextSize(int length, String text) {
		
		String result = text;
		
		int idx = 0;
		while (result.length() < length) {
			
			result += text.charAt(idx++);
			
			if (idx >= length) {
				
				idx = 0;
			}
		}
		
		return result;
	}
	
	private static int indexOf(char[] array, char toFind) {
		
		int result = -1;
		
		for (int i = 0; i < array.length; i++) {
			
			if (array[i] == toFind) {
				
				result = i;
				break;
			}
		}
		
		return result;
	}
	
	private static String Cipher(
			String clearText, char[][] tabulaRecta, String keyword) 
	{
		String result = "";
		
		keyword = growToTextSize(clearText.length(), keyword);
		
		for (int i = 0; i < clearText.length(); i++) {
			
			int row = keyword.charAt(i) - 'a';
			char charToCipher = clearText.charAt(i);
			
			int idx = indexOf(tabulaRecta[row], charToCipher);
			
			if (idx == 0) {
				
				idx = tabulaRecta[row].length;
			}
			
			idx = tabulaRecta[row].length - idx;
			
			result += tabulaRecta[0][idx];
		}
		
		return result;
	}

	private static String Decipher(
			String cipherText, char[][] tabulaRecta, String keyword) 
	{
		String result = "";
		
		result = Cipher(cipherText, tabulaRecta, keyword);
		
		return result;
	}
}






