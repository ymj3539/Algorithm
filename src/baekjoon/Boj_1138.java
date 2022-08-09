package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Boj_1138 {
	
	static int N;
	static int[] arr;	// �Է� �迭
	static List<Integer> list = new ArrayList<>();	// ��� ����Ʈ
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(input.readLine());
		arr = new int[N+1];
		
		tokens = new StringTokenizer(input.readLine());
		
		for(int i=1; i<=N; i++){
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		// �Է� �迭 �ڿ������� list�� �߰�
		for(int i =N; i>0; i--) {
			if(list.size()<= arr[i]) {
				list.add(i);
			}else {
				list.add(arr[i], i);
			}
		}
		
		for(int i : list) {
			sb.append(i+" ");
		}
		
		System.out.println(sb);
		
		
	}
}
