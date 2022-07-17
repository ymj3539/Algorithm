package Boj_2504;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		
		Stack<Character> stack = new Stack<>(); //스택 선언
		int tmp = 1; //임시 저장 변수
		int answer = 0; //출력값
		
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == '(') {	// 괄호가 '('일때
				stack.push(ch);
				tmp *= 2;
				
			}else if(ch=='[') {	// 괄호가 '['일때
				stack.push(ch);
				tmp *= 3;
				
			}else if(ch == ')') {	// 괄호가 ')'일때
				if(stack.isEmpty() || stack.peek() != '(') {
					answer = 0;
					break;
				}else if(str.charAt(i-1) == '('){
					answer += tmp;
				}
				// ')'이전의 괄호가 '('가 아닌 경우도 존재하므로 아래의 코드를 else if문 밖으로 빼야된다. 
				stack.pop();
				tmp /= 2;
				
			}else if(ch == ']') {	// 괄호가 ']'일때
				if(stack.isEmpty() || stack.peek() != '[') {
					answer = 0;
					break;
				}else if(str.charAt(i-1) == '['){
					answer += tmp;
				}
				stack.pop();
				tmp /= 3;
			}
		}
		// 위의 과정을 거친 후에도 stack이 비어있지 않으면 올바르지 않은 괄호열이다.
		if(!stack.isEmpty()) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
}
