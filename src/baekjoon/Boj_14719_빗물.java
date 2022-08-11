package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Boj_14719_빗물
 * https://www.acmicpc.net/problem/14719
 * 실패
 */

public class Boj_14719_빗물 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int H;
	static int W;
	static boolean[][] map;
	static int[] block;
	static boolean rainSwitch; // 빗물받기 on/off 
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		H = Integer.parseInt(tokens.nextToken());
		W = Integer.parseInt(tokens.nextToken());
		map = new boolean[H][W];
		
		block = new int[W];
		tokens = new StringTokenizer(input.readLine());
		
		// 입력받으면서 기둥위치 map에 체크
		for(int i=0; i<W; i++) {
			block[i] = Integer.parseInt(tokens.nextToken());
			for(int j=0; j<block[i]; j++) {
				map[j][i] = true;
			}
		}
		
//		for(boolean[] b : map) {
//			System.out.println(Arrays.toString(b));
//		}
		/////// 입력끝
		
		int sum =0;
		
		for(int i =0; i<H; i++) {
			int cnt =0;
			rainSwitch = false;
			for(int j=0; j<W; j++) {
				// 스위치 off일때 빈공간 만나
				if(map[i][j] == false && rainSwitch == false) {
					continue;
					
				// 스위치 off 일 때 블록 만나
				}else if(map[i][j] == true && rainSwitch == false) {
					rainSwitch = true; //스위치 on
					continue;
				
				// 스위치 on 일 때 빈공간 만나
				}else if(map[i][j] == false && rainSwitch) {
					cnt++; // 빗물량++
					continue;
					
				// 스위치 on일 때 블록 만나는 상황에서 담아둔 빗물 있으면 sum에 더해줌
				}else if(map[i][j] == true && rainSwitch && cnt >0) {
					sum += cnt;
					cnt = 0;
//					rainSwitch = false; //스위치 off
				}
			}
		}
		System.out.print(sum);
		
		
	}
}
