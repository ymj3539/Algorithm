
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, K, P, X;
	static boolean[][] numbers = {
			{true, true, true, false, true, true, true}, // 0
			{false, false, true, false, false, true, false}, // 1
			{true, false, true, true, true, false, true}, // 2
			{true, false, true, true, false, true, true}, // 3
			{false, true, true, true, false, true, false}, // 4
			{true, true, false, true, false, true, true}, // 5
			{true, true, false, true, true, true, true}, // 6
			{true, false, true, false, false, true, false}, // 7
			{true, true, true, true, true, true, true}, // 8
			{true, true, true, true, false, true, true} // 9
	};
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());	// 층
		K = Integer.parseInt(tokens.nextToken());	// 자리수
		P = Integer.parseInt(tokens.nextToken());	// 반전횟수
		X = Integer.parseInt(tokens.nextToken());	// 현재 층
		
		int answer = 0;
		String x = Integer.toString(X); // 현재 층을 string으로 변환
		
		// 1 ~ N층 돌리기
		for(int i=1; i<=N; i++) {
			String now = Integer.toString(i);	// i층을 string으로 변환
			
			while(now.length() < K) {
				now = "0"+now;
			}
			
			while(x.length() < K) {
				x = "0"+x;
			}
			int cnt = 0;
			
			// i층과 현재층의 각 자리를 비교
			for(int j = 0; j<K; j++) {
				int tmpX = x.charAt(j) - '0';
				int tmpI = now.charAt(j) - '0';
				
				// 각 자리의 숫자의 배열 비교(반전 횟수 구하기)
				for(int k=0; k<=6; k++) {
					// led 온오프가 다르면
					if(numbers[tmpX][k] != numbers[tmpI][k]) {
						cnt++;
					}
				}
			}
			
			if(cnt > 0 && cnt <= P) answer++;
			
		}
	
		System.out.println(answer);
	}
}
