import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		int[] arr = new int[N];
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		int H = 0;
		while(H < max) {
			int mid = (H + max)/2;
			long sum = 0;
			for(int treeHeight : arr) {
				if(treeHeight - mid > 0) {
					sum += (treeHeight - mid);
				}
			}
			
			if(sum < M) {
				max = mid;
				
			}else {
				H = mid + 1;
			}
		}
		System.out.println(H - 1);
	}
}