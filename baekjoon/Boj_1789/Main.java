package Boj_1789;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long number = sc.nextLong();
		sc.close();
		
		long sum = 0;
		long count = 0;
		while(number>=sum) {
			count++;
			sum += count;
		}
		System.out.println(count-1);
	}

}
