package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Boj_12761_���ٸ� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int A, B, N, M, answer;
	static int[] visited;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		A = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());	// ���� ��ġ
		M = Integer.parseInt(tokens.nextToken());	// �ֹ� ��ġ
		
		visited = new int[100001];
		visited[N] = 1;
		
		bfs();
		
		System.out.println(answer);
	}
	
	static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		int cnt = 0;
		queue.offer(new int[] {N,cnt});
		
		
		while(!queue.isEmpty()){
			int[] current = queue.poll();
			int sum = current[0];
			cnt = current[1];
			if(sum == M) {
				answer = cnt;
				return;
			}
			
			// +1ĭ �̵�
			if(isIn(sum+1,cnt)) {
				visited[sum+1] = cnt+1;
				queue.offer(new int[] {sum+1, cnt+1});
			}
			
			// -1ĭ �̵�
			if(isIn(sum-1,cnt)) {
				visited[sum-1] = cnt+1;
				queue.offer(new int[] {sum-1, cnt+1});
			}
			
			// +Aĭ �̵�
			if(isIn(sum+A,cnt)) {
				visited[sum+A] = cnt+1;
				queue.offer(new int[] {sum+A, cnt+1});
			}
			
			// -Aĭ �̵�
			if(isIn(sum-A,cnt)) {
				visited[sum-A] = cnt+1;
				queue.offer(new int[] {sum-A, cnt+1});
			}
			
			// +Bĭ �̵�
			if(isIn(sum+B,cnt)) {
				visited[sum+B] = cnt+1;
				queue.offer(new int[] {sum+B, cnt+1});
			}
			
			// -Bĭ �̵�
			if(isIn(sum-B,cnt)) {
				visited[sum-B] = cnt+1;
				queue.offer(new int[] {sum-B, cnt+1});
			}
			
			// *Aĭ �̵�
			if(isIn(sum*A,cnt)) {
				visited[sum*A] = cnt+1;
				queue.offer(new int[] {sum*A, cnt+1});
			}
			
			// *Bĭ �̵�
			if(isIn(sum*B,cnt)) {
				visited[sum*B] = cnt+1;
				queue.offer(new int[] {sum*B, cnt+1});
			}
			
			
		}
	}
	
	// �ѹ��� �湮�� ���� ���ų�, �湮�Ϸ��� ���� cnt���� �� cnt�� �� ������ �湮
	static boolean isIn(int num, int cnt) {
		return (num >= 0 && num <= 100000 && (visited[num] == 0 || cnt+1 < visited[num]) );
	}
}
