import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(input.readLine());
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			queue.offer(Integer.parseInt(input.readLine()));
		}
		
		Stack<Integer> stack = new Stack<>();
		
		
		for(int i=1; i<=n; i++) {
			// stack에 추가
			stack.push(i);
			sb.append("+").append("\n");
			
			// 스택 head에 있는 수가 만들어야 되는 수열의 수와 일치하면 pop
			while(true) {
				if(!stack.isEmpty() && !queue.isEmpty() && stack.peek().intValue() == queue.peek().intValue()) {
					stack.pop();
					queue.poll();
					sb.append("-").append("\n");
				}else break;
			}
			
		}

		
		if(!queue.isEmpty()) {
			System.out.println("NO");
		}else System.out.print(sb);
		
	}
}