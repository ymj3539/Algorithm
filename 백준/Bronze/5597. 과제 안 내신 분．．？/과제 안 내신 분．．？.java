import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		boolean[] arr = new boolean[31];
		for(int i=1; i<=28; i++) {
			int n = Integer.parseInt(input.readLine());
			arr[n] = true;
		}
		
		for(int i=1; i<=30; i++) {
			if(arr[i]) continue;
			else System.out.println(i);
		}
	}
}