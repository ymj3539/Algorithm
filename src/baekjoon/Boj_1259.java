package baekjoon;

import java.util.Scanner;

public class Boj_1259 {
	
	static String str;
	static String str2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			str = sc.nextLine();
			
			if(!str.equals("0")) {
				sb.setLength(0);
				
				for(int i=str.length()-1; i>=0; i--) {
					str2 = sb.append(str.charAt(i)).toString();
				}
				
				if(str.equals(str2)) {
					System.out.println("yes");
				}else {
					System.out.println("no");
				}
			}else break;
		}
		
		
	}
}
