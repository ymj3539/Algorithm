
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int L, C;
	static String[] letters;
	static StringBuilder sb = new StringBuilder();
	static List<String> ans = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		tokens = new StringTokenizer(input.readLine());
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		letters = new String[C];
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<C; i++) {
			letters[i] = tokens.nextToken();
		}
		
		comb(0, new String[L], 0);
		Collections.sort(ans);
		for(int i=0; i<ans.size(); i++) {
			sb.append(ans.get(i)+"\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void comb(int nth, String[] choosed, int index) {
		if(nth == choosed.length) {
			//정렬
			String[] copy = Arrays.copyOf(choosed, L);
			Arrays.sort(copy);
			// 출력
			String code = "";
			int consonant_cnt = 0;
			int vowel_cnt = 0;
			for(int j=0; j<copy.length; j++) {
				code = code+(copy[j]);
				if(copy[j].equals("a")|| copy[j].equals("e")|| copy[j].equals("i")|| copy[j].equals("o")|| copy[j].equals("u")) {
					vowel_cnt++;
				}else {
					consonant_cnt++;
				}
			}
			
			if(vowel_cnt<1 || consonant_cnt<2) return;
			
			ans.add(code);
			return;
		}
		
		for(int i=index; i<letters.length; i++) {
			choosed[nth] = letters[i];
			comb(nth+1, choosed, i+1);
		}
	}
}
