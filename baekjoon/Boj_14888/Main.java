package Boj_14888;

import java.util.Scanner;

public class Main {
	static int N;
	static int[] operator = new int[4]; // ������ �迭 ����
	static int[] num;
	static int Max = Integer.MIN_VALUE;
	static int Min = Integer.MAX_VALUE;
	
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //���� ����
		num = new int[N]; // ���� ����
		
		// ���� �Է�
		for(int i =0; i<N; i++) {
			num[i] = sc.nextInt();
		}
		
		// ������ �Է�
		for(int i =0; i<4; i++) {
			operator[i] = sc.nextInt();
		}
		
		sc.close();
		
		dfs(num[0], 1);
		
		System.out.println(Max);
		System.out.println(Min);
		
	}
	
	static void dfs(int number, int index) {
		if(index == N) {
			Max = Math.max(number, Max);
			Min = Math.min(number, Min);
			return;
		}
		
		for(int i=0; i<4; i++ ) {
			if( operator[i] > 0 ) {
				operator[i]--;
				
				switch(i) {
				case 0 : dfs(number+num[index], index+1); break;
				case 1 : dfs(number-num[index], index+1); break;
				case 2 : dfs(number*num[index], index+1); break;
				case 3 : dfs(number/num[index], index+1); break;
				}
				
				operator[i]++;
			}
		}
	}
	
	
}
