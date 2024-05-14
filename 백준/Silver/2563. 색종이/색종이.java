
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int x;
	static int y;
	static boolean[][] map = new boolean[101][101];
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			x = Integer.parseInt(tokens.nextToken());
			y = Integer.parseInt(tokens.nextToken());
			
			for(int j=y; j<y+10; j++) {
				for(int k=x; k<x+10; k++) {
					map[k][j] = true;
				}
			}
		}
		
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				if(map[i][j] == true) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
