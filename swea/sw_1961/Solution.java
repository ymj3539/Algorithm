package sw_1961;
import java.util.*;
public class Solution {
	static int N;
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt(); 
			int[][] arr = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					arr[i][j] = sc.nextInt(); //N*N 행렬
				}
			}
			
			int [][] arr90 = spin(arr);
			int [][] arr180 = spin(arr90);
			int [][] arr270 = spin(arr180);
			
			System.out.println("#"+test_case);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(arr90[i][j]);
				}
				System.out.print(" ");
				
				for(int j=0; j<N; j++) {
					System.out.print(arr180[i][j]);
				}
				System.out.print(" ");
				
				for(int j=0; j<N; j++) {
					System.out.print(arr270[i][j]);
				}
				System.out.println();
			}
			
			
		
			
		}
		sc.close();
	}
	
	public static int[][] spin (int[][] arr1){
		int[][] tmp = new int[N][N];
		for(int i =0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = arr1[N-j-1][i]; 
			}
		}
		return tmp;
	}

}
