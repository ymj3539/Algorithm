import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		int[] dp1 = new int[122]; // 한변의 길이가 i인 정삼각형의 포탄 갯수
		int[] dp2 = new int[122]; // 높이가 i인 사면체의 포탄 수
		
		dp1[1] = 1;
		dp2[1] = 1;
		
		// dp 배열 채우기
		for(int i=2; i<122; i++) {
			dp1[i] = dp1[i-1] + i;
			dp2[i] = dp2[i-1] + dp1[i];
		}
		
		// i일 때 더미의 최소 갯수를 저장하는 배열
		int[] minCnt = new int[N+1];
		
		// 최소값을 구하기 위해 최대값으로 초기화
		Arrays.fill(minCnt, Integer.MAX_VALUE);
		
		minCnt[0] = 0;
		minCnt[1] = 1;
		
		for(int i=2; i<=N; i++) {
			for(int j=1; j<122; j++) {
				if(dp2[j] > i) break;
				minCnt[i] = Math.min(minCnt[i], minCnt[i-dp2[j]]+1);
			}
		}
		
		System.out.println(minCnt[N]);
	}
}
