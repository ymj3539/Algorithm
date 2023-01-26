
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int T;
	static int x1, y1, r1, x2, y2, r2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());
		for(int t=1; t<=T; t++) {
			tokens = new StringTokenizer(input.readLine());
			x1 = Integer.parseInt(tokens.nextToken());
			y1 = Integer.parseInt(tokens.nextToken());
			r1 = Integer.parseInt(tokens.nextToken());
			x2 = Integer.parseInt(tokens.nextToken());
			y2 = Integer.parseInt(tokens.nextToken());
			r2 = Integer.parseInt(tokens.nextToken());
			
			// d는 두 좌표 사이의 거리 제곱값
			double d = Math.pow(x1-x2 , 2) + Math.pow(y1-y2, 2);
			
			int answer = 2;
			
			if(x1 == x2 && y1 == y2) {
				if(r1 == r2) answer = -1;
				else answer = 0;
			}
			else if(d == Math.pow(r1 + r2 , 2)) answer = 1;
			else if(d == Math.pow(r1 - r2 , 2)) answer = 1;
			else if(d > Math.pow(r1 + r2 , 2)) answer = 0;
			else if(d < Math.pow(r1 - r2 , 2)) answer = 0;
			
			sb.append(answer+"\n");
		}
		
		System.out.println(sb);
		
	}
}
