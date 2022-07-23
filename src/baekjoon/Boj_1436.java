package baekjoon;

import java.util.Scanner;

public class Boj_1436 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int index =0;
		
		String str = null;
		String [] arr = new String[N];
		
		int i =0;
		while(index < N) {
			if(Integer.toString(i).contains("666")) {
				str = Integer.toString(i);
				arr[index++] = str;
			}
			i++;
			if(index == N) {
				break;
			}
		}
		
		System.out.println(arr[index-1]);
		
	}

}
