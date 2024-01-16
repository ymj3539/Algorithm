
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int K, N;
	static int[] line; 
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		K = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		line = new int[K];
		long max = 0;
		for(int i=0; i<K; i++) {
			line[i] = Integer.parseInt(input.readLine());
			max = Math.max(max, line[i]);
		}
		
		long min = 1;
		long mid = 0;
		// min과 max가 교차하기 전까지 구하기
		while(min <= max) {
			mid = (max+min)/2;
			
			long cnt = 0;
			for(int i=0; i<K; i++) {
				cnt += line[i]/mid;
			}
			if(cnt < N) {
				max = mid-1;
			}else {
				min = mid+1;
			}
		}
		System.out.println(max);
		
	}
}