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

public class Swea_2382_미생물격리 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, K;
	static List<Group> list;
	static int[] dx = {0, 0, 0, -1, 1}; // 상 하 좌 우
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
			
			// 주어진 시간만큼 돌리기
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
		
		// 군집 이동시키기
		for(int i = 0; i<list.size(); i++) {
			// 하나씩 이동
			Group cur = list.get(i);
			int nr = cur.r + dy[cur.dir];
			int nc = cur.c + dx[cur.dir];
			
			// 약품 칠해진 곳에 도착했을 때
			if(isEnd(nr, nc)) {
				cur.cnt = cur.cnt/2;
				
				// 상 -> 하
				if(cur.dir == 1) {
					cur.dir = 2;
				}
				// 하 -> 상
				else if(cur.dir == 2) {
					cur.dir = 1;
				}
				// 좌 -> 우
				else if(cur.dir == 3) {
					cur.dir = 4;
				}
				// 우 -> 좌
				else if(cur.dir == 4) {
					cur.dir = 3;
				}
				
			}
			
			// 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우
			if(visited[nr][nc]) {
				for(int j=0; j<nextList.size(); j++) {
					int lr = nextList.get(j).r;
					int lc = nextList.get(j).c;
					
					// list에서 좌표에 해당하는 군집 찾아
					// 현재 군집 -> 이전 군집 에 합쳐주기
					if(lr == nr && lc == nc) {
						// 미생물 수 합치기
						nextList.get(j).cnt += cur.cnt;
						cur.cnt = 0;
						continue;
					}
				}
			}
			
			// 미생물 cnt가 0이 아닌것만 새 리스트에 추가
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
	
	// 약품이 칠해진 셀에 도착했는지 판단
	static boolean isEnd(int r, int c) {
		return (r == 0 || c == 0 || r == N-1 || c == N-1);
	}
}
