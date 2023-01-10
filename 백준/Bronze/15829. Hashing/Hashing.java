import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int L = Integer.parseInt(input.readLine());
		int[] arr = new int[L];
		String str = input.readLine();
		long tmp = 0;
		
		for(int i=0; i<L; i++) {
			arr[i] = str.charAt(i)-'a'+1;
			
			tmp += ( (arr[i]%1234567891) * (Math.pow(31, i)) % 1234567891 );
		}
		
		
		// System.out.println(Arrays.toString(arr));
		System.out.println(tmp);
		
	}
	
}