package boj_10845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
	public static void main(String arg[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<String> queue = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		int last = 0;
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			String arr[] = new String[2];
			arr = str.split(" ");
			if(arr.length == 2) {
				last = Integer.parseInt(arr[1]);
			}
			
			switch (arr[0]) {
			
			case "push" : 
				queue.add(arr[1]);
				break;
				
			case "pop" : 
				if(queue.isEmpty()) bw.write(String.valueOf(-1)+'\n');
				else {
					bw.write(String.valueOf(queue.poll())+'\n');
				} 
				break;
				
			case "size" :
				bw.write(String.valueOf(queue.size())+'\n');
				break;
			
			case "empty" :
				if(queue.isEmpty()) bw.write(String.valueOf(1)+'\n');
				else bw.write(String.valueOf(0)+'\n');
				break;
			
			case "front" : 
				if(queue.isEmpty()) bw.write(String.valueOf(-1)+'\n');
				else bw.write(String.valueOf(queue.peek())+'\n');
				break;
				
			case "back" :
				if(queue.isEmpty()) bw.write(String.valueOf(-1)+'\n');
				else {
					bw.write(String.valueOf(last)+'\n');
				}
				break;
			}
		}
		bw.flush();
	}
}
