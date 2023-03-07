package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_18111_마인크래프트 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, B;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int height;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				
			}
		}
		
		height = 0;
		int h = 0; // 높이
		for(int i=0; i<=256; i++) {
			int b = B;
			h = i;
			int time = 0;
			// 집터 다 돌기
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					// 높이가 같을 때
					if(map[r][c] == h) continue;
					// 땅 높이가 주어진 높이보다 클 때(블록제거 후 인벤에 추가)
					else if(map[r][c] > h) {
						time += ((map[r][c] - h) * 2); // 시간 계산
						b += (map[r][c] - h);
					}
				}
			}
			
			boolean flag = true;
			
			outer : for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					// 높이가 같을 때
					if(map[r][c] == h) continue;
					// 땅 높이가 주어진 높이보다 작을 때(인벤에서 블록 꺼내기)
					else if(map[r][c] < h) {
						if(b < (h - map[r][c])) {
							flag = false;
							break outer; // 인벤이 비었으면 넘어가
						}else {
							// 인벤이 비어있지 않으면 2번 작업 가능
							time += (h - map[r][c]); // 시간 계산
							b -= (h - map[r][c]);
						}
						
					}
				}
			}
			
			// 시간 계산
			if(flag) {
				if(min > time) {
					min = time;
					height = h;
				}else if(min == time) {
					height = Math.max(height, h);
				}
			}
		}
		
		System.out.println(min+" "+height);
	}
}
