package sw_1979;

import java.util.Scanner;

public class Solution {
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			int[][] square = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					square[i][j] = sc.nextInt();
				}
			}
			
			int count =0; // 들어갈 수 있는 자리는 몇개?
			int word = 0; // 단어의 길이 체크
			// 가로 탐색
			for(int i =0; i<N; i++) {
				word=0;
				for(int j=0; j<N; j++) {
					if(square[i][j] == 0) {  // 0(검정)일떄 
						if(word == K) {
							count++;
							word =0;
						}else word =0;
					}else {  // 1(흰색) 일떄
						word++;
					}
				}
				if(word == K) {
					count++;
				}
			}
			
			word = 0; // 단어의 길이 체크
			// 새로 탐색
			for(int i =0; i<N; i++) {
				word=0;
				for(int j=0; j<N; j++) {
					if(square[j][i] == 0) {  // 0(검정)일떄 
						if(word == K) {
							count++;
							word =0;
						}else word =0;
					}else {  // 1(흰색) 일떄
						word++;
					}
				}
				if(word == K) {
					count++;
				}
			}
			
			System.out.println("#"+test_case+" "+count);
		
		}
		sc.close();
	}
}