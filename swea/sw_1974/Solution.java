package sw_1974;
import java.util.*;
public class Solution {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   ���� ���� �׽�Ʈ ���̽��� �־����Ƿ�, ������ ó���մϴ�.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{	
			int answer = 1;
			// ������ ���� �����
			int[][] sudoku = new int[9][9];
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sudoku[i][j] = sc.nextInt();
				}
			}
			
			// ������ üũ
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
			
			// ������ üũ
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
			
			// �ڽ� üũ
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
