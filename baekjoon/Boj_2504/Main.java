package Boj_2504;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		
		Stack<Character> stack = new Stack<>(); //���� ����
		int tmp = 1; //�ӽ� ���� ����
		int answer = 0; //��°�
		
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == '(') {	// ��ȣ�� '('�϶�
				stack.push(ch);
				tmp *= 2;
				
			}else if(ch=='[') {	// ��ȣ�� '['�϶�
				stack.push(ch);
				tmp *= 3;
				
			}else if(ch == ')') {	// ��ȣ�� ')'�϶�
				if(stack.isEmpty() || stack.peek() != '(') {
					answer = 0;
					break;
				}else if(str.charAt(i-1) == '('){
					answer += tmp;
				}
				// ')'������ ��ȣ�� '('�� �ƴ� ��쵵 �����ϹǷ� �Ʒ��� �ڵ带 else if�� ������ ���ߵȴ�. 
				stack.pop();
				tmp /= 2;
				
			}else if(ch == ']') {	// ��ȣ�� ']'�϶�
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
		// ���� ������ ��ģ �Ŀ��� stack�� ������� ������ �ùٸ��� ���� ��ȣ���̴�.
		if(!stack.isEmpty()) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
}
