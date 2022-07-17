package sw_2001;

import java.util.*;

public class Solution {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			// 영역 채우기
			int[][] area = new int[N][N];
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					area[i][j] = sc.nextInt();
				}
			}
			
			int Max=0;
			// 영역 돌면서 파리의 갯수 구하기
			for(int i=0; i<N-M+1; i++) {
				for(int j=0; j<N-M+1; j++) {
					// 죽은 파리의 개수 구하기
					int sum=0;
					for(int k=i; k<i+M; k++) {
						for(int l=j; l<j+M; l++) {
							sum += area[k][l];
						}
					}
					
					Max = Math.max(Max, sum);
					
				}
			}
			System.out.println("#"+test_case+" "+Max);
			
		}
		sc.close();
	}
}
