package boj_2775;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		int[][] apt = new int[15][15];
		
		// ������ �� ä��� 
		for(int i=0; i<15; i++) {
			apt[i][1] = 1;	// 1ȣ�� ������ 1��
			apt[0][i] = i;  // 0���� �ο����� ȣ���� ����.
		}
		
		for(int i=1; i<15; i++) {
			for(int j=2; j<15; j++) {
				apt[i][j] = apt[i][j-1] + apt[i-1][j];
			}
		}
		
		for(int i=0; i<T; i++) {
			int K = sc.nextInt();
			int n = sc.nextInt();
			System.out.println(apt[K][n]);
		}
		sc.close();
		
		
		
	}

}
