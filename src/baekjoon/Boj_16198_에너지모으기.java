package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_16198_������������ {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static List<Integer> list;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		// ������ ���� ����Ʈ
		list = new ArrayList<>();
		
		tokens = new StringTokenizer(input.readLine());
		
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(tokens.nextToken())); 
		}
		
		
		dfs(0, list);
		System.out.println(max);
	}
	
	static void dfs(int totalE, List<Integer> list) {
		// list ũ�Ⱑ 2�� �Ǹ� ����
		if(list.size() < 3) {
			// ������ ���� �ִ� ��
			max = Math.max(totalE, max);
			return;
		}
		
		for(int i=0; i<list.size()-2; i++) {
			// list�� �տ������� �� ĭ�� �ǳʶ� �� ���� ����
			totalE += (list.get(i) * list.get(i+2)); //������ ������
			int tmp = list.get(i+1); // ������ ������ ������ �� �ӽ� ����
			list.remove(i+1); // ���� ����
			dfs(totalE, list); // ���� dfs
			
			// ���� �۾�
			list.add(i+1, tmp); // ����Ʈ�� �տ������� ������� ���� �ֱ� ������ ���� �ڸ��� �ٽ� �־�����Ѵ�.
			totalE -= (list.get(i) * list.get(i+2));
			
		}
	}
}
