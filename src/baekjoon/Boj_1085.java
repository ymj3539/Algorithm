package baekjoon;

import java.util.Scanner;

// commit test


public class Boj_1085 {
	static int d =0;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		
		cal(x,0);
		cal(y,0);
		cal(x,w);
		cal(y,h);
		
		System.out.println(min);
		
	}
	
	static void cal(int a, int b) {
		d = Math.abs(a-b);
		min = Math.min(min, d);
	}
}
