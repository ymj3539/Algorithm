import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			
			// 소수면 true 아니면 false
			boolean flag = true;
			// num은 소수인지 판별해야되는 숫자
			int num = Integer.parseInt(tokens.nextToken());
			
			if(num == 1) {
				continue;
			}
			for(int j=2; j<=Math.sqrt(num); j++) {
				if(num % j == 0) {
					flag = false;
					break;
				}
			}
			
			if(flag) cnt++;
		}
		System.out.println(cnt);
	}
}