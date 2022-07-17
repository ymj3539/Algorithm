package sw_1974;
import java.util.*;
public class Solution {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{	
			int answer = 1;
			// 스토쿠 퍼즐 만들기
			int[][] sudoku = new int[9][9];
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sudoku[i][j] = sc.nextInt();
				}
			}
			
			// 가로줄 체크
			for(int i=0; i<9; i++) {
				int[] check = new int[9];
				for(int j=0; j<9; j++) {
					check[sudoku[i][j]-1]++;
				}
				for(int k=0; k<9; k++) {
					if(check[k] == 0) {
						answer = 0;
						break;
					}
				}
			}
			
			// 세로줄 체크
			for(int i=0; i<9; i++) {
				int[] check = new int[9];
				for(int j=0; j<9; j++) {
					check[sudoku[j][i]-1]++;
				}
				for(int k=0; k<9; k++) {
					if(check[k] == 0) {
						answer = 0;
						break;
					}
				}
			}
			
			// 박스 체크
			for(int i=0; i<=6; i+=3) {
				for(int j=0; j<=6; j+=3) {
					int[] check = new int[9];
					int r = i+3;
					int c = j+3;
					
					for(int a=i; a<r; a++) {
						for(int b=j; b<c; b++) {
							check[sudoku[a][b]-1]++;
						}
					}
					
					for(int k=0; k<9; k++) {
						if(check[k] == 0) {
							answer = 0;
							break;
						}
					}
					
				}
			}
			
			
			System.out.println("#"+test_case+" "+answer);
		}
	}

}
