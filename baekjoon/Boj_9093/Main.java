package Boj_9093;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		while(N > 0) {
			Stack<Character> stack = new Stack<>();
			String str = br.readLine()+'\n';
			for(int i=0; i<str.length(); i++) {
				char c = str.charAt(i);
				if(c == ' ' || c == '\n') {
					while(!stack.isEmpty()) {
						bw.write(stack.pop());
					}
					bw.write(c);
				}else {
					stack.push(c);
				}
				
			}
			N--;
		}
		bw.flush();
	}
}
