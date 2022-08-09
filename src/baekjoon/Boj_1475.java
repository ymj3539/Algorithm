package baekjoon;

import java.util.Scanner;

public class Boj_1475 {
	static int[] numArr = new int[10];
	static String N;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextLine();
		
		for(int i=0; i<N.length(); i++) {
			
			int num = N.charAt(i) -'0';
			if(num == 9) num = 6;
			
			numArr[num]++;
		}
		
		// 6와 9의 개수가 짝수 일 때
		if(numArr[6]%2 ==0) {
			numArr[6] /= 2;
			
		// 홀수일 때
		}else {
			numArr[6] = numArr[6]/2 +1;
		}
		
		
		for(int i : numArr) {
			max = Math.max(max, i);
		}
		
		System.out.println(max);
		
		
	}
}
