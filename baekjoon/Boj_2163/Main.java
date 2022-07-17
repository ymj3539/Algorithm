package Boj_2163;

import java.util.Scanner;

public class Main {
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.close();
		
		int answer = M * N -1; // (N-1) + N(M-1)
		System.out.println(answer);
		
	}
}
