package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj_11723 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		int M = Integer.parseInt(input.readLine());
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			String str = tokens.nextToken();
			
			if(str.equals("all")) {
				map.clear();
				
				for(int j=1; j<=20; j++) {
					map.put(j, 1);
				}
				continue;
				
			}else if(str.equals("empty")) {
				map.clear();
				continue;
			}
			
			int num = Integer.parseInt(tokens.nextToken());
			
			if(str.equals("add")) {
				if(!map.containsKey(num)) map.put(num, 1);
				else continue;
				
			}else if(str.equals("remove")) {
				if(map.containsKey(num)) map.remove(num);
				else continue;
				
			}else if(str.equals("check")) {
				sb.append(map.containsKey(num) ? 1  : 0 );
				sb.append("\n");
				continue;
				
			}else if(str.equals("toggle")) {
				if (map.containsKey(num)) {
					map.remove(num);
				} else map.put(num, 1);
				  continue;
			}
		}
		
		System.out.println(sb);
	}
	
	
	
}
