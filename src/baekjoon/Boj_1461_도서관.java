package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Boj_1461_도서관 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, M;
	static int[] books;
	static Stack<Integer> plusBooks = new Stack<>(); // 책의 위치가 양수
	static Queue<Integer> minusBooks = new LinkedList<>();	// 책의 위치가 음수 
	static int moveCnt; // 이동횟수
	static int max = Integer.MIN_VALUE;
	static int result;	// 이동횟수 - 편도값
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		books = new int[N];
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<N; i++) {
			books[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Arrays.sort(books);
		
		for(int i : books) {
			if(i > 0) plusBooks.add(i);
			else {
				minusBooks.add(Math.abs(i));
			}
		}
		
		if(minusBooks.isEmpty()) {
			max = plusBooks.peek();
		} else if(plusBooks.isEmpty()) {
			max = minusBooks.peek();
		}else {
			max = Math.max(minusBooks.peek(), plusBooks.peek());
		}
		
		movePlus();
		moveMinus();
		
		result = moveCnt - max;
		
		System.out.println(result);
	}
	
	static void movePlus() {
		while(!plusBooks.isEmpty()) {
			if(plusBooks.size() >= M) {
				moveCnt += (plusBooks.peek()*2);
				for(int i=0; i<M; i++) {
					plusBooks.pop();
				}
			}else {
				moveCnt += (plusBooks.peek()*2);
				while(!plusBooks.isEmpty()) {
					plusBooks.pop();
				}
			}
		}
	}
	
	static void moveMinus() {
		while(!minusBooks.isEmpty()) {
			if(minusBooks.size() >= M) {
				moveCnt += (minusBooks.peek()*2);
				for(int i=0; i<M; i++) {
					minusBooks.poll();
				}
			}else {
				moveCnt += (minusBooks.peek()*2);
				while(!minusBooks.isEmpty()) {
					minusBooks.poll();
				}
			}
		}
	}
}
