package boj_1158;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String arr[] = str.split(" ");
		int N = Integer.parseInt(arr[0]);
		int K = Integer.parseInt(arr[1]);
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i =1; i<=N; i++) {
			queue.add(i);
		}
		
		bw.write("<");
		
		while(!queue.isEmpty()) {
			for(int i =0; i<K-1; i++) {
				queue.add(queue.poll());
			}
			if(queue.size()>1) {
				bw.write(String.valueOf(queue.poll())+", ");
			}else {
				bw.write(String.valueOf(queue.poll()));
			}
			
		}
		bw.write(">");
		bw.flush();
	}

}
