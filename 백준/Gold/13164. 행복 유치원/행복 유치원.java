
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, K;
	static int[] students;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		students = new int[N];
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<N; i++) {
			students[i] = Integer.parseInt(tokens.nextToken());
			if(i>0) {
				pq.add(Math.abs(students[i] - students[i-1]));
			}
		}
		int sum = 0;
		for(int i=0; i<N-1 - (K-1); i++) {
			sum += pq.poll();
		}
		
		
		// System.out.println(Arrays.toString(students));
		System.out.println(sum);
		
		
	}
	
}
