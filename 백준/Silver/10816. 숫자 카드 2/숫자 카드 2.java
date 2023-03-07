import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		tokens = new StringTokenizer(input.readLine());
		Map<Integer, Integer> map = new TreeMap<>();
		for(int i =0; i<N; i++) {
			int tmp = Integer.parseInt(tokens.nextToken());
			if(map.containsKey(tmp)) {
				int cnt = map.get(tmp);
				map.put(tmp, ++cnt);
			}else {
				map.put(tmp, 1);
			}
			
		}
		
		M = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		for(int i =0; i<M; i++) {
			int tmp = Integer.parseInt(tokens.nextToken());
			if(map.get(tmp) != null) {
				sb.append(map.get(tmp)+" ");
			}else {
				sb.append(0+" ");
			}
			
		}
		
		System.out.println(sb);
	}
}