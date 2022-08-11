package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Boj_14719_����
 * https://www.acmicpc.net/problem/14719
 * ����
 */

public class Boj_14719_���� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int H;
	static int W;
	static boolean[][] map;
	static int[] block;
	static boolean rainSwitch; // �����ޱ� on/off 
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		H = Integer.parseInt(tokens.nextToken());
		W = Integer.parseInt(tokens.nextToken());
		map = new boolean[H][W];
		
		block = new int[W];
		tokens = new StringTokenizer(input.readLine());
		
		// �Է¹����鼭 �����ġ map�� üũ
		for(int i=0; i<W; i++) {
			block[i] = Integer.parseInt(tokens.nextToken());
			for(int j=0; j<block[i]; j++) {
				map[j][i] = true;
			}
		}
		
//		for(boolean[] b : map) {
//			System.out.println(Arrays.toString(b));
//		}
		/////// �Է³�
		
		int sum =0;
		
		for(int i =0; i<H; i++) {
			int cnt =0;
			rainSwitch = false;
			for(int j=0; j<W; j++) {
				// ����ġ off�϶� ����� ����
				if(map[i][j] == false && rainSwitch == false) {
					continue;
					
				// ����ġ off �� �� ��� ����
				}else if(map[i][j] == true && rainSwitch == false) {
					rainSwitch = true; //����ġ on
					continue;
				
				// ����ġ on �� �� ����� ����
				}else if(map[i][j] == false && rainSwitch) {
					cnt++; // ������++
					continue;
					
				// ����ġ on�� �� ��� ������ ��Ȳ���� ��Ƶ� ���� ������ sum�� ������
				}else if(map[i][j] == true && rainSwitch && cnt >0) {
					sum += cnt;
					cnt = 0;
//					rainSwitch = false; //����ġ off
				}
			}
		}
		System.out.print(sum);
		
		
	}
}
