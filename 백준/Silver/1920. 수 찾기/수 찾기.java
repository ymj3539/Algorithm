import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(input.readLine());
		Set<Integer> set = new TreeSet<>();
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<N; i++) {
			
			set.add(Integer.parseInt(tokens.nextToken()));
		}
		
		int M = Integer.parseInt(input.readLine());
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<M; i++) {
			int tmp = Integer.parseInt(tokens.nextToken());
			if(set.contains(tmp)){
				sb.append(1+"\n");
			}else {
				sb.append(0+"\n");
			}
		}
		
		System.out.println(sb);
		
	}
}