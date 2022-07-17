package boj_2839;

import java.util.*;

public class Main {	
	public static void main(String args[])  {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		int count =0;
		int sum = 0;
		
		while(sum != N) {
			if(N%5 == 0) {
				count += N/5;
				break;
			}else {
				N -= 3;
				count++;
			}
			
			if(N<0) {
				count = -1;
				break;
			}
		}
		System.out.println(count);
	
	
	}
}
