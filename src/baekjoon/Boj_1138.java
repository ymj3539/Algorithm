package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Boj_1138 {
	
	static int N;
	static int[] arr;	// 입력 배열
	static List<Integer> list = new ArrayList<>();	// 출력 리스트
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(input.readLine());
		arr = new int[N+1];
		
		tokens = new StringTokenizer(input.readLine());
		
		for(int i=1; i<=N; i++){
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		// 입력 배열 뒤에서부터 list에 추가
		for(int i =N; i>0; i--) {
			if(list.size()<= arr[i]) {
				list.add(i);
			}else {
				list.add(arr[i], i);
			}
		}
		
		for(int i : list) {
			sb.append(i+" ");
		}
		
		System.out.println(sb);
		
		
	}
}
