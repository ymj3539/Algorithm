
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = input.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		
		func(N, 0, 0);
		System.out.println(sb);
	}
	
	// r과 c는 사각형의 시작점
	static void func(int n, int r, int c) {
		if(OneAndZero(n,r,c)) {
			sb.append(arr[r][c]);
			return;
		}
		
		sb.append("(");
		int mid = n/2;
		
		// 1사분면
		func(mid, r, c);
		
		// 2사분면
		func(mid, r, c+mid);
		
		// 3사분면
		func(mid, r+mid, c);
		
		// 4사분면
		func(mid, r+mid, c+mid);
		
		sb.append(")");
	}
	
	static boolean OneAndZero(int n, int r, int c) {
		int tmp = arr[r][c];
		// 1과 0이 섞였는지 판단하는 로직
		for(int i = r; i<r+n; i++) {
			for(int j=c; j<c+n; j++) {
				if(tmp != arr[i][j]) {
					return false;
				}
				tmp = arr[i][j];
			}
		}
		return true;
	}
}
