package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16236_�Ʊ��� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, M;
	static int[][] map;
	static int[] dy = {-1, 0, 0, 1}; // �� �� �� ��
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
				
				// �Ʊ��� ��ü ����
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
		// �غ�
		Queue<Shark> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		Fish target =  null;
		queue.offer(shark);
		visited[shark.r][shark.c] = true;
		
		int depth = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				// ť���� ������
				Shark cur = queue.poll();
				
				// ���Ž��
				for(int i=0; i<dx.length; i++) {
					int nr = cur.r + dy[i];
					int nc = cur.c + dx[i];
					
					if(!isIn(nr, nc)) continue;
					
					if(visited[nr][nc]) continue;
					
					// ����Ⱑ ���� ũ��
					if(cur.size < map[nr][nc]) continue;
					
					// ���� ũ�Ⱑ ���ų� ��ĭ�̸� -> �̵�����! -> ť�� �־�
					if(map[nr][nc] == 0 || cur.size == map[nr][nc]) {
						queue.offer(new Shark (nr, nc, cur.size, cur.fishCnt));
						visited[nr][nc] = true;
					}
					
					// ���� ����Ⱑ ������
					else if(cur.size > map[nr][nc]) {
						Fish fish = new Fish(nr, nc, depth);
						// Ÿ�� ����Ⱑ ���ٸ� -> Ÿ������ ���
						if(target == null) {
							target = fish;
						}
						
						// Ÿ�� ����Ⱑ �ִٸ� �Ÿ� ��
						else {
							// Ÿ�ٹ���Ⱑ �� �ָ��ִٸ� ���� ����⸦ Ÿ������ ����
							if(target.compareTo(fish) > 0) {
								target = fish;
							}
						}
					}
				}
				
				
			}
			// target�� ������ ����������!
			if(target != null) {
				shark.eat(target);
				bfs();
				// Ÿ�� ��ġ�� �� �̵������ϱ� ���� �۾��� �������� �ʾƵ� �� -> ����
				return;
			}
			depth++;
		}
		
		// ������ Ž���ߴµ� Ÿ���� ������ ����
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
			// target ��ġ�� �̵�
			this.r = tr;
			this.c = tc;
			
			// ����� �Ա�
			map[tr][tc] = 0;
			this.fishCnt++;
			
			// ��� ũ�⸸ŭ ����⸦ �Ծ����� ������ ����
			if(this.fishCnt == this.size) {
				this.size++;
				this.fishCnt =0;
			}
			
			// �̵��Ÿ���ŭ �ð��߰�
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
