package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_7568 {
	
	static int N;
	static int[][] arr;
	static int[] answer;
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		N = Integer.parseInt(input.readLine());
		
		arr = new int[N][2];
		answer = new int[N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			arr[i][0] = Integer.parseInt(tokens.nextToken());
			arr[i][1] = Integer.parseInt(tokens.nextToken());
		}
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == j) continue;
				if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
					++answer[i];
				}
			}
			++answer[i];
		}
		
		for(int i=0; i<N-1; i++) {
			sb.append(answer[i]+" ");
		}
		sb.append(answer[N-1]);
		
		System.out.println(sb);
	}
	
}
