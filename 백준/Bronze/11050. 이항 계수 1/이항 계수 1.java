import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, K;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		int answer = fact(N)/(fact(K)*fact(N-K));
		System.out.println(answer);
	}
	
	static int fact(int n) {
		if(n == 0) {
			return 1;
		}
		
		return n * fact(n-1);
		
	}
}