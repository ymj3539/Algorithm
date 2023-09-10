import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int R, C, M;
	static Shark[][] map;
	static Shark[][] copyMap;
	static int[] dx = {0, 0, 0, 1, -1};	// 위(1) 아래(2) 오른쪽(3) 왼쪽(4)
	static int[] dy = {0, -1, 1, 0, 0};
	static int ans;	// 잡은 상어크기 합
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new Shark[R+1][C+1];
		copyMap = new Shark[R+1][C+1];
		for(int i = 0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			int size = Integer.parseInt(tokens.nextToken());
			
			map[r][c] = new Shark(r, c, s, d, size);
		}
		
		// 낚시꾼이 이동
		for(int col=1; col<=C; col++) {
			// 낚시
			fishing(col);	
		}
		System.out.println(ans);
	}
	
	private static void fishing(int c) {
		// 열탐색
		for(int r =1; r<=R; r++) {
			// 상어가 있으면
			if(map[r][c] != null) {
				Shark shark = map[r][c];
				// 상어 크기 add
				ans += shark.size;
				// 상어 잡은 곳 빈칸만들기
				map[r][c] = null;
				break;
			}
		}
		
		// 상어 이동
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j] != null) {
					// 이동
					Shark movedShark = move(map[i][j]);
					// 이동전 자리는 null로 비워주기
					map[i][j] = null;
					
					// 이동할 자리에 상어가 없으면
					if(copyMap[movedShark.r][movedShark.c] == null) {
						copyMap[movedShark.r][movedShark.c] = movedShark; // 복제맵에 표시하기
					
					// 만약 이동할 자리에 이미 상어가 있으면
					}else if(copyMap[movedShark.r][movedShark.c].size < movedShark.size) {
							copyMap[movedShark.r][movedShark.c] = movedShark;
					}
				}
			}
		}
		// 지도 swap
		Shark[][] tmp = map;
		map = copyMap;
		copyMap = tmp;
		
	}
	
	private static Shark move(Shark shark) {
		int dr = shark.r;
		int dc = shark.c;
		for(int i=0; i<shark.s; i++) {
			dr += dy[shark.d];
			dc += dx[shark.d];
			
			// 범위 벗어나면 방향 전환
			if(!isIn(dr, dc)) {
				if(shark.d <=2) {
					shark.d = 3 - shark.d;
				}else {
					shark.d = 7 - shark.d;
				}
				dr += (dy[shark.d]*2);
				dc += (dx[shark.d]*2);
			}
		}
		
		shark.r = dr;
		shark.c = dc;
		
		return shark;
	}
	
	static boolean isIn(int r, int c) {
		return (r>0 && c>0 && r<=R && c<=C);
	}

	static class Shark implements Comparable<Shark>{
		int r, c, s, d, size;

		public Shark(int r, int c, int s, int d, int size) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.size = size;
			
			// 한싸이클이 넘어가면 무시하기
			if(this.d>2) {
				this.s %= (C-1)*2;
			}else {
				this.s %= (R-1)*2;
			}
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ", " + s + ", " + d + ", " + size + ")";
		}

		@Override
		public int compareTo(Shark o) {
			return (this.size - o.size);
		}
		
	}
	
}
