import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int max = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
	
		Egg[] eggs = new Egg[N]; // 계란 배열
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int S = Integer.parseInt(tokens.nextToken());
			int W = Integer.parseInt(tokens.nextToken());
			
			eggs[i] = new Egg(S, W);
		}
		
		// 계란 순열
		dfs(0, eggs, new boolean[N]);
		System.out.println(max);
		
	}
	
	static void dfs(int cur, Egg[] choosed, boolean[] broken) {
		if(cur == N) {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				if(broken[i]) cnt++;
			}
			
			max=Math.max(max, cnt);
			return;
		}
		
		
		for(int i=0; i<N; i++) {
			// 본인이고 아니고, 깨지지 않은 것 중 하나 골라
			if(cur == i) continue;
			if(!broken[i]  && !broken[cur]) {
				// 계란 깨기
				int egg1_S = choosed[cur].S;
				int egg2_S = choosed[i].S;
				
				choosed[cur].S = choosed[cur].S - choosed[i].W;
				choosed[i].S =  choosed[i].S - choosed[cur].W;
				if(choosed[cur].S <= 0) {
					broken[cur] = true;
				}
				
				if(choosed[i].S <=0 ) {
					broken[i] = true;
				}
				
				dfs(cur+1, choosed, broken);
				
				// 원복
				if(choosed[cur].S <= 0) {
					broken[cur] = false;
				}
				if(choosed[i].S <=0 ) {
					broken[i] = false;
				}
				choosed[cur].S = egg1_S;
				choosed[i].S = egg2_S;
			}else {
				dfs(cur+1, choosed, broken);
				
			}
		}
	}
	
	static class Egg{
		int S, W;

		public Egg(int s, int w) {
			super();
			S = s;
			W = w;
		}

		
	}
}