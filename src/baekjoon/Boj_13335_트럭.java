package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_13335_Æ®·° {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int n, w, L;
	static int[] truck;
	static int time;
	static int sum;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		n = Integer.parseInt(tokens.nextToken());
		w = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(input.readLine());
		
		truck = new int[n];
		for(int i =0; i<n; i++) {
			truck[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i=0; i<w; i++) {
			queue.offer(0);
		}
		
		
		int index = 0;
		while(!queue.isEmpty()) {
			sum -= queue.poll(); 
			time++;	
			
			if(queue.size()<w && index < n) {
				if((sum+truck[index]) <= L) {
					queue.offer(truck[index]);
					sum += truck[index];
					index++;
				}else {
					queue.offer(0);
				}
			}
		}
		
		
		System.out.println(time);
		
		
	}
	
}
