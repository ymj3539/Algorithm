import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int T;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());
		sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			tokens = new StringTokenizer(input.readLine());
			int H = Integer.parseInt(tokens.nextToken());
			int W = Integer.parseInt(tokens.nextToken());
			int N = Integer.parseInt(tokens.nextToken());
			
			int cnt = 0;
			String answer = null;
			outer: for(int w=1; w<=W; w++) {
				for(int h=1; h<=H; h++) {
					cnt++;
					if(cnt == N) {
						if(w<10) {
							answer = h+"0"+w;
						}else {
							answer = Integer.toString(h)+Integer.toString(w);
						}
						break outer;
					}
				}
			}
			
			sb.append(answer+'\n');
		}
		System.out.println(sb);
	}
}