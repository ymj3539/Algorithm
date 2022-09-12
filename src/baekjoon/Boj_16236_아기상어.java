package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16236_아기상어 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, M;
	static int[][] map;
	static int[] dy = {-1, 0, 0, 1}; // 상 좌 우 하
	static int[] dx = {0, -1, 1, 0};
	static Shark shark;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				
				// 아기상어 객체 생성
				if(map[r][c] == 9) {
					shark = new Shark(r, c, 2, 0);
					map[r][c] = 0;
				}
			}
		}
		
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		// 준비물
		Queue<Shark> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		Fish target =  null;
		queue.offer(shark);
		visited[shark.r][shark.c] = true;
		
		int depth = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				// 큐에서 꺼내기
				Shark cur = queue.poll();
				
				// 사방탐색
				for(int i=0; i<dx.length; i++) {
					int nr = cur.r + dy[i];
					int nc = cur.c + dx[i];
					
					if(!isIn(nr, nc)) continue;
					
					if(visited[nr][nc]) continue;
					
					// 물고기가 상어보다 크면
					if(cur.size < map[nr][nc]) continue;
					
					// 상어랑 크기가 같거나 빈칸이면 -> 이동가능! -> 큐에 넣어
					if(map[nr][nc] == 0 || cur.size == map[nr][nc]) {
						queue.offer(new Shark (nr, nc, cur.size, cur.fishCnt));
						visited[nr][nc] = true;
					}
					
					// 상어보다 물고기가 작으면
					else if(cur.size > map[nr][nc]) {
						Fish fish = new Fish(nr, nc, depth);
						// 타겟 물고기가 없다면 -> 타겟으로 등록
						if(target == null) {
							target = fish;
						}
						
						// 타겟 물고기가 있다면 거리 비교
						else {
							// 타겟물고기가 더 멀리있다면 현재 물고기를 타겟으로 변경
							if(target.compareTo(fish) > 0) {
								target = fish;
							}
						}
					}
				}
				
				
			}
			// target이 있으면 먹으러가자!
			if(target != null) {
				shark.eat(target);
				bfs();
				// 타겟 위치로 상어가 이동했으니까 이후 작업은 실행하지 않아도 돼 -> 리턴
				return;
			}
			depth++;
		}
		
		// 끝까지 탐색했는데 타겟이 없으면 리턴
		if(target == null) return;
	}
	
	static class Shark{
		int r, c, size, fishCnt;

		public Shark(int r, int c, int size, int fishCnt) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.fishCnt = fishCnt;
		}
		
		public void eat(Fish target) {
			int tr = target.r;
			int tc = target.c;
			int td = target.dist;
			// target 위치로 이동
			this.r = tr;
			this.c = tc;
			
			// 물고기 먹기
			map[tr][tc] = 0;
			this.fishCnt++;
			
			// 상어 크기만큼 물고기를 먹었으면 사이즈 조정
			if(this.fishCnt == this.size) {
				this.size++;
				this.fishCnt =0;
			}
			
			// 이동거리만큼 시간추가
			ans += td;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ") size: " + size + ", fishCnt:" + fishCnt + "]";
		}
		
	}
	
	static class Fish implements Comparable<Fish>{
		int r, c, dist;

		public Fish(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + "), dist: "+dist;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.dist == o.dist) {
				if(this.r == o.r) {
					return this.c - o.c;
				}else return this.r - o.r;
			}else return this.dist - o.dist;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<N);
	}
	
}
