package boj_9012;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			Stack<Character> stack = new Stack<>();
			String answer = "";
			for(int j=0; j<str.length(); j++) {
				char c = str.charAt(j);
				if(c == '(') {
					stack.push(c);
				}else { // c�� ')'�϶�
					if(stack.isEmpty()) { //'('�ε� stack�� ����ٸ� ����
						stack.push(c);
						break;
					}else { // c�� ')'�̰�, stack�� ������� ����
						if(stack.peek() != '(') { // stack.peek�� '('�� �ƴϸ� ����
							break;
						}else {
							stack.pop();
						}
					}	
				}
			}
			if(stack.isEmpty()) {
				bw.write("YES"+'\n');
			}else bw.write("NO"+'\n');
		}
		bw.flush();
	}

}
