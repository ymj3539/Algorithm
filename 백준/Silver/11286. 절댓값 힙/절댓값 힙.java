import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, x;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int n1 = Math.abs(o1);
				int n2 = Math.abs(o2);
				
				// o1 o2 절댓값이 다를 때
				if(n1 != n2) {
					return n1 - n2;
				}
				// o1 o2 절댓값이 같을 때
				else {
					return o1 - o2;
				}
			}
			
		});
		
		
		for(int i = 1; i<=N; i++ ) {
			x = Integer.parseInt(input.readLine());
			
			
			// 0일 때 -> 출력하고 제거
			if(x == 0) {
				if(pq.isEmpty()) sb.append(0+"\n");
				else sb.append(pq.poll()+"\n");
			} 
			
			// 0이 아닐 때 추가
			else {
				pq.offer(x);
			}
		}
		
		System.out.println(sb);
	}
	
}