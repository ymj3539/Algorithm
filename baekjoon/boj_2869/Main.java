package boj_2869;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String arr[] = str.split(" ");
		int A = Integer.parseInt(arr[0]);
		int B = Integer.parseInt(arr[1]);
		int V = Integer.parseInt(arr[2]);
		
	
		int day = 0;
		day = (V-A) / (A-B);
		if((V-A) %(A-B) == 0) {
			day++;
		}else day +=2;
	
		
		System.out.println(day);
	}

}
