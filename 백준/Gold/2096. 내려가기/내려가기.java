
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		int[] dpMax = new int[3]; // 이전 줄의 숫자들
		int[] dpMin = new int[3];
		
		int max = 0;
		int min = 0;
		
		int n0, n1, n2 = 0;
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
				n0 = Integer.parseInt(tokens.nextToken());
				n1 = Integer.parseInt(tokens.nextToken());
				n2 = Integer.parseInt(tokens.nextToken());
				
				if(i == 0) {
					dpMax[0] = dpMin[0] = n0;
					dpMax[1] = dpMin[1] = n1;
					dpMax[2] = dpMin[2] = n2;
				}else {
					// 최대값 구하기
					int before_max0 = dpMax[0];
					int before_max2 = dpMax[2];
					dpMax[0] = Math.max(dpMax[0], dpMax[1]) + n0; // dpMax 배열 갱신
					dpMax[2] = Math.max(dpMax[1], dpMax[2]) + n2;
					dpMax[1] = Math.max(Math.max(dpMax[1], before_max0), before_max2) + n1;
					
					
					// 최대값 구하기
					int before_min0 = dpMin[0];
					int before_min2 = dpMin[2];
					dpMin[0] = Math.min(dpMin[0], dpMin[1]) + n0; // dpMin 배열 갱신
					dpMin[2] = Math.min(dpMin[1], dpMin[2]) + n2;
					dpMin[1] = Math.min(Math.min(dpMin[1], before_min0), before_min2) + n1;
				
			}
			
			
		}
		max = Math.max(Math.max(dpMax[0], dpMax[1]), dpMax[2]);
		min = Math.min(Math.min(dpMin[0], dpMin[1]), dpMin[2]);
		System.out.println(max+" "+min);
		
	}
}
