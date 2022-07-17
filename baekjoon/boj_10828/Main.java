package boj_10828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Stack<String> stack = new Stack<>();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			String arr[] = new String[2];
			arr = str.split(" ");
			switch (arr[0]) {
			
			case "push" : {
				stack.push(arr[1]);
				break;
			}case "top" : {
				if(stack.empty()) {
					bw.write(String.valueOf(-1)+'\n');
				}else {
					bw.write(String.valueOf(stack.peek())+'\n');
				}	
				break;
			}case "size" : {
				bw.write(String.valueOf(stack.size())+'\n');
				break;
			}case "empty" : {
				if(stack.empty()) {
					bw.write(String.valueOf(1)+'\n');
				}else bw.write(String.valueOf(0)+'\n');
				break;
			}case "pop" : {
				if(stack.empty()) {
					bw.write(String.valueOf(-1)+'\n');
				}else bw.write(String.valueOf(stack.pop())+'\n');
				break;
			}
			}
			
		}
		bw.flush();
	
	}
}
