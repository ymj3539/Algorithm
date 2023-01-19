import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, C;
	static int[] homes;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		homes = new int[N+1];
		
		for(int i = 1; i<=N; i++) {
			homes[i] = Integer.parseInt(input.readLine());
		}
		
		Arrays.sort(homes);
		
		int max = 0;
		
		int left = 1;
		int right = homes[N] - homes[1];
		int d = 0;
		int answer = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			int start = homes[1];
			int cnt = 1;
			for(int i=1; i<= N; i++) {
				d = homes[i] - start; // 집 사이 거리
				if (d >= mid) { // 공유기 설치 가능 체크
					cnt++;
					start = homes[i];
				}
				
			}
			
			if(cnt >= C) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(answer);
			
	}
}
