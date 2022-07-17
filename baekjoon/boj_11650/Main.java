package boj_11650;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int arr[][] = new int[N][N];
		String arr1[] = new String[N];
		
		for(int i=0; i<N; i++) {
			arr1 = (br.readLine()).split(" ");
			arr[i][0] = Integer.parseInt(arr1[0]);
			arr[i][1] = Integer.parseInt(arr1[1]);
		}
		
		Arrays.sort(arr, (e1, e2) -> {
			if(e1[0] == e2[0]) {
				return e1[1] - e2[1];
			}else {
				return e1[0] - e2[0];
			}
		});
		
		for(int i=0; i<N; i++) {
			System.out.println(arr[i][0]+" "+arr[i][1]);
		}
		
	}
}
