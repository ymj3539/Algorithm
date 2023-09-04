import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    static int[][] students;
    static int[][] stu_map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        map = new int[N+1][N+1];
        students = new int[N*N+1][4];
        stu_map = new int[N*N+1][2]; // 학생 번호, 학생 좌표{r,c}

        for(int i=1; i<=N*N; i++){
            tokens = new StringTokenizer(input.readLine());
            int student = Integer.parseInt(tokens.nextToken());
            students[student][0] = Integer.parseInt(tokens.nextToken());
            students[student][1] = Integer.parseInt(tokens.nextToken());
            students[student][2] = Integer.parseInt(tokens.nextToken());
            students[student][3] = Integer.parseInt(tokens.nextToken());
            case1(student);
        }


        int total = 0;
        // 만족도 계산
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                int point = isGood(r,c);
                if(point == 1) total += 1;
                else if(point == 2) total += 10;
                else if(point == 3) total += 100;
                else if(point == 4) total += 1000;
            }
        }

        System.out.println(total);

    }

    static int isGood(int r,int c){
        int stu_num = map[r][c];
        int cnt = 0;
        for(int i=0; i<4; i++){
            int dr = r+dy[i];
            int dc = c+dx[i];
            if(!isIn(dr,dc)) continue;

            for(int j=0; j<4; j++){
               if(students[stu_num][j] == map[dr][dc]) cnt++;
            }
        }
        return cnt;
    }



    static void case1(int stu_num){
        int[][] cnt_map = new int[N+1][N+1];
        int[][] empty_map = new int[N+1][N+1];
        int cnt_max = Integer.MIN_VALUE;
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                // 비어있는 칸이면
                if(map[r][c] == 0){
                    // 인접한 곳에 선호하는 숫자가 있는지 확인
                    int cnt = 0;
                    int empty = 0;

                    for(int i=0; i<4; i++){
                        int dr = r + dy[i];
                        int dc = c + dx[i];

                        if(!isIn(dr,dc)) continue;
                        if(map[dr][dc] == 0) {
                            empty++;
                            continue;
                        }

                        for(int j=0; j<4; j++){
                            if(map[dr][dc] == students[stu_num][j]) cnt++;
                        }
                    }

                    cnt_max = Math.max(cnt_max, cnt);
                    cnt_map[r][c] = cnt;
                    empty_map[r][c] = empty;
                }
            }
        }


        // 선호하는 학생이 제일 많이 인접한 곳 찾기
        Queue<int[]> cnt_q = new LinkedList();

        PriorityQueue<int[]> empty_q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }else{
                    return o1[0] - o2[0];
                }
            }
        });

        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                // map에 이미 숫자가 있는 경우 다시 걸러줘야돼!!!
                if(cnt_map[r][c] == cnt_max && map[r][c] == 0) cnt_q.offer(new int[] {r, c});
            }
        }

        if(cnt_q.size() > 1){
            // case 2번 조건으로 넘어가

            int max = Integer.MIN_VALUE;
            while(!cnt_q.isEmpty()){
                // empty값 비교
                int[] cur = cnt_q.poll();
                int cr = cur[0];
                int cc = cur[1];
                if(empty_map[cr][cc] > max){
                    max = empty_map[cr][cc];
                    empty_q.clear();
                    empty_q.offer(new int[] {cr, cc});
                }else if(empty_map[cr][cc] == max){
                    empty_q.offer(new int[] {cr, cc});
                }
            }
            if(!empty_q.isEmpty()){
                map[empty_q.peek()[0]][empty_q.peek()[1]] = stu_num;
            }
        }else if(cnt_q.size() == 1){
            // 조건 맞는게 1개면 좌표에 해당 학생 번호 넣기
            map[cnt_q.peek()[0]][cnt_q.peek()[1]] = stu_num;
        }

    }



    static boolean isIn(int r, int c){
        return (r>=1 && r<=N && c>=1 && c<=N);
    }
}