package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Swea_2382_�̻����ݸ� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, K;
	static List<Group> list;
	static int[] dx = {0, 0, 0, -1, 1}; // �� �� �� ��
	static int[] dy = {0, -1, 1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			
			int r, c, cnt, dir;
			list = new LinkedList<>();
			
			for(int i=0; i<K; i++) {
				tokens = new StringTokenizer(input.readLine());
				r = Integer.parseInt(tokens.nextToken());
				c = Integer.parseInt(tokens.nextToken());
				cnt = Integer.parseInt(tokens.nextToken());
				dir = Integer.parseInt(tokens.nextToken());
				list.add(new Group(r, c, cnt, dir));
			}
			
			// �־��� �ð���ŭ ������
			for(int t=0; t<M; t++) {
				moveGroup();
			}
			
			int sum = 0;
			for(int i =0; i<list.size(); i++) {
				sum += list.get(i).cnt;
			}
			
			sb.append("#"+ test_case +" "+ sum+"\n");
		}
		System.out.println(sb);
	}
	
	static void moveGroup() {
		Collections.sort(list);
		boolean[][] visited = new boolean[N][N];
		List<Group> nextList = new LinkedList<>();
		
		// ���� �̵���Ű��
		for(int i = 0; i<list.size(); i++) {
			// �ϳ��� �̵�
			Group cur = list.get(i);
			int nr = cur.r + dy[cur.dir];
			int nc = cur.c + dx[cur.dir];
			
			// ��ǰ ĥ���� ���� �������� ��
			if(isEnd(nr, nc)) {
				cur.cnt = cur.cnt/2;
				
				// �� -> ��
				if(cur.dir == 1) {
					cur.dir = 2;
				}
				// �� -> ��
				else if(cur.dir == 2) {
					cur.dir = 1;
				}
				// �� -> ��
				else if(cur.dir == 3) {
					cur.dir = 4;
				}
				// �� -> ��
				else if(cur.dir == 4) {
					cur.dir = 3;
				}
				
			}
			
			// �̵� �� �� �� �̻��� ������ �� ���� ���̴� ���
			if(visited[nr][nc]) {
				for(int j=0; j<nextList.size(); j++) {
					int lr = nextList.get(j).r;
					int lc = nextList.get(j).c;
					
					// list���� ��ǥ�� �ش��ϴ� ���� ã��
					// ���� ���� -> ���� ���� �� �����ֱ�
					if(lr == nr && lc == nc) {
						// �̻��� �� ��ġ��
						nextList.get(j).cnt += cur.cnt;
						cur.cnt = 0;
						continue;
					}
				}
			}
			
			// �̻��� cnt�� 0�� �ƴѰ͸� �� ����Ʈ�� �߰�
			if(cur.cnt != 0) {
				visited[nr][nc] = true;
				nextList.add(new Group(nr, nc, cur.cnt, cur.dir));
			}
			
		}
		
		list = nextList;
	}
	
	static class Group implements Comparable<Group>{
		int r, c, cnt, dir;

		public Group(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ", " + cnt + ", " + dir + ")";
		}

		@Override
		public int compareTo(Group o) {
			return (this.cnt - o.cnt)*-1;
		}
	}
	
	// ��ǰ�� ĥ���� ���� �����ߴ��� �Ǵ�
	static boolean isEnd(int r, int c) {
		return (r == 0 || c == 0 || r == N-1 || c == N-1);
	}
}
