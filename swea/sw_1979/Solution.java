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
			
			int count =0; // �� �� �ִ� �ڸ��� �?
			int word = 0; // �ܾ��� ���� üũ
			// ���� Ž��
			for(int i =0; i<N; i++) {
				word=0;
				for(int j=0; j<N; j++) {
					if(square[i][j] == 0) {  // 0(����)�ϋ� 
						if(word == K) {
							count++;
							word =0;
						}else word =0;
					}else {  // 1(���) �ϋ�
						word++;
					}
				}
				if(word == K) {
					count++;
				}
			}
			
			word = 0; // �ܾ��� ���� üũ
			// ���� Ž��
			for(int i =0; i<N; i++) {
				word=0;
				for(int j=0; j<N; j++) {
					if(square[j][i] == 0) {  // 0(����)�ϋ� 
						if(word == K) {
							count++;
							word =0;
						}else word =0;
					}else {  // 1(���) �ϋ�
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