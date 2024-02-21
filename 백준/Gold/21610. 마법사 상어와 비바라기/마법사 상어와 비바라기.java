import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] A;
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static List<int[]> clouds;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        A = new int[N+1][N+1];

        for(int r=1; r<=N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=1; c<=N; c++){
                A[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        // 비바라기 시전
        clouds = new ArrayList<>();
        clouds.add(new int[] {N,1});
        clouds.add(new int[] {N,2});
        clouds.add(new int[] {N-1, 1});
        clouds.add(new int[] {N-1, 2});

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int d = Integer.parseInt(tokens.nextToken());
            int s = Integer.parseInt(tokens.nextToken());

            move(d, s);
        }

        int water = 0;
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                water += A[r][c];
            }
        }

        System.out.println(water);
    }

    static void move(int d, int s){
        boolean[][] isCloud = new boolean[N+1][N+1];

        // 1. 모든 구름 d방향으로 s칸 이동
        for(int i=0; i<clouds.size(); i++){

            clouds.get(i)[0] += dy[d] * (s%N);
            clouds.get(i)[1] += dx[d] * (s%N);

            // 좌표 조정
            if(clouds.get(i)[0] > N) clouds.get(i)[0] -= N;
            else if(clouds.get(i)[0] <1) clouds.get(i)[0] += N;

            if(clouds.get(i)[1] > N) clouds.get(i)[1] -= N;
            else if(clouds.get(i)[1] <1) clouds.get(i)[1] += N;

            isCloud[clouds.get(i)[0]][clouds.get(i)[1]] = true;

            if(!isIn(clouds.get(i)[0], clouds.get(i)[1])) {
                clouds.remove(i);
                i--;
                continue;
            }

            // 2. 구름이 있는 칸의 바구니에 저장된 물의 양 1증가
            A[clouds.get(i)[0]][clouds.get(i)[1]]++;
        }

        // 4. 물복사버그
        for(int i=0; i<clouds.size(); i++){
            // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물의 양이 증가
            int r = clouds.get(i)[0];
            int c = clouds.get(i)[1];
            int cnt = 0;
            for(int dir=2; dir<=8; dir+=2){
                int dr = r + dy[dir];
                int dc = c + dx[dir];

                if(!isIn(dr, dc)) continue;
                if(A[dr][dc]>=1) cnt++;
            }
            A[r][c] += cnt;
        }

        // 3. 구름이 모두 사라짐
        clouds.clear();

        // 5. 구름 생성
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                if(isCloud[r][c]) continue;
                if(A[r][c] >=2) {
                    clouds.add(new int[] {r,c});
                    A[r][c] -= 2;
                }
            }
        }

    }

    static boolean isIn(int r, int c){
        return r>=1 && c>=1 && r<=N && c<=N;
    }

}