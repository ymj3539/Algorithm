package Boj_3085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int N;
	static char[][] arr1;
	static int Max = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		arr1 = new char[N][N];
		for(int i=0; i<N; i++) {
			String str = bf.readLine();
			for(int j=0; j<N; j++) {
				arr1[i][j] = str.charAt(j); 
			}
		}
		
		// �� Ž��
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				// ������ �� ���� �ٸ��� ��ȯ
				if(arr1[i][j] != arr1[i][j+1]) {
					char tmp = arr1[i][j];
					arr1[i][j] = arr1[i][j+1];
					arr1[i][j+1] = tmp;
					search(arr1);
					
					tmp = arr1[i][j];
					arr1[i][j] = arr1[i][j+1];
					arr1[i][j+1] = tmp; 
				}
			}
		}
		
		// �� Ž��
		for(int i=0; i<N-1; i++) {
			for(int j=0; j<N; j++) {
				// ������ �� ���� �ٸ��� ��ȯ
				if(arr1[i][j] != arr1[i+1][j]) {
	 				char tmp = arr1[i][j];
					arr1[i][j] = arr1[i+1][j];
					arr1[i+1][j] = tmp;
					search(arr1);
					
					tmp = arr1[i][j];
					arr1[i][j] = arr1[i+1][j];
					arr1[i+1][j] = tmp;
					}
			}
		}
		
		
		System.out.println(Max);
	}
	
	public static void search(char[][] arr) {
		// ���� �� �� ã��
		for(int i=0; i<N; i++) {
			int count = 1;
			for(int j=0; j<N-1; j++) {
				if(arr[i][j] == arr[i][j+1]) {
					count++;
				}else {
					count = 1;
				}
				Max = Math.max(Max, count);
			}
		}
		
		// ���� �� �� ã��
		for(int i=0; i<N; i++) {
			int count = 1;
			for(int j=0; j<N-1; j++) {
				if(arr[j][i] == arr[j+1][i]) {
					count++;
				}else {
					count = 1;
				}
				Max = Math.max(Max, count);
			}
		}
	}

}

