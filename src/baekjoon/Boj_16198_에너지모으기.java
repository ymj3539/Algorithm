package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_16198_에너지모으기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static List<Integer> list;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		// 에너지 구슬 리스트
		list = new ArrayList<>();
		
		tokens = new StringTokenizer(input.readLine());
		
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(tokens.nextToken())); 
		}
		
		
		dfs(0, list);
		System.out.println(max);
	}
	
	static void dfs(int totalE, List<Integer> list) {
		// list 크기가 2가 되면 종료
		if(list.size() < 3) {
			// 에너지 양의 최댓값 비교
			max = Math.max(totalE, max);
			return;
		}
		
		for(int i=0; i<list.size()-2; i++) {
			// list의 앞에서부터 한 칸을 건너뛴 두 개씩 선택
			totalE += (list.get(i) * list.get(i+2)); //에너지 모으기
			int tmp = list.get(i+1); // 제거할 구슬의 에너지 값 임시 저장
			list.remove(i+1); // 구슬 제거
			dfs(totalE, list); // 다음 dfs
			
			// 복귀 작업
			list.add(i+1, tmp); // 리스트가 앞에서부터 순서대로 돌고 있기 때문에 원래 자리에 다시 넣어줘야한다.
			totalE -= (list.get(i) * list.get(i+2));
			
		}
	}
}
