import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, K;
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[][] food;
    static int[][] map;
    static Queue<Tree> trees;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());   // 땅 크기
        M = Integer.parseInt(tokens.nextToken());   // 나무 갯수
        K = Integer.parseInt(tokens.nextToken());   // k년

        food = new int[N+1][N+1];
        map = new int[N+1][N+1];

        // 양분의 양 입력
        for(int r=1; r<=N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=1; c<=N; c++){
                food[r][c] = Integer.parseInt(tokens.nextToken());
                map[r][c] = 5;
            }
        }

        trees = new PriorityQueue();
        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            int year = Integer.parseInt(tokens.nextToken());
            trees.offer(new Tree(x, y, year));
        }

        // K년 시뮬레이션
        int k = 0;
        while(k<K){
            // 정렬
            PriorityQueue<Tree> newPQ = new PriorityQueue<>();
            Queue<Tree> dead = new LinkedList<>();
            Queue<Tree> fall = new LinkedList<>();
            // 봄
            int size = trees.size();
            while(size-->0){
                Tree cur = trees.poll();

                // 양분먹기
                if(cur.year <= map[cur.r][cur.c]){
                    map[cur.r][cur.c] -= cur.year;
                    cur.year++;
                    newPQ.offer(new Tree(cur.r, cur.c, cur.year));
                    if(cur.year % 5 ==0) fall.offer(new Tree(cur.r, cur.c, cur.year));
                }else {
                    // 양분 못먹고 죽음
                    dead.offer(new Tree(cur.r, cur.c, cur.year));
                }
            }


            // 여름
            // 죽은 나무가 양분으로 변함
            size = dead.size();
            while(size-->0){
                Tree cur = dead.poll();
                map[cur.r][cur.c] += (cur.year/2);
            }


            // 가을
            // 나이가 5의 배수인 나무만
            size = fall.size();
            while(size-->0){
                Tree cur = fall.poll();
                for(int i=0; i<8; i++){
                    int dr = cur.r + dy[i];
                    int dc = cur.c + dx[i];

                    if(isIn(dr, dc)){
                        newPQ.offer(new Tree(dr, dc, 1));
                    }
                }
            }


            // 겨울
            // 양분 추가
            for(int r=1; r<=N; r++){
                for(int c=1; c<=N; c++){
                    map[r][c] += food[r][c];
                }
            }

//            //////////
//            System.out.println("겨울");
//            size1 = trees.size();
//            while(size1-->0){
//                Tree cur = trees.poll();
//                System.out.println(cur);
//                trees.offer(new Tree(cur.r, cur.c, cur.year, true));
//            }

//            for(int[] i : map) {
//                System.out.println(Arrays.toString(i));
//            }
//            System.out.println("----------------");

            // pq 갱신
            trees = newPQ;
            k++;
        }


        System.out.println(trees.size());

    }

    static boolean isIn(int r, int c){
        return r>=1 && r<=N && c>=1 && c<=N;
    }

    static class Tree implements Comparable<Tree>{
        int r, c, year;
        public Tree(int r, int c, int year){
            this.r = r;
            this.c = c;
            this.year = year;
        }

        @Override
        public int compareTo(Tree O){
            return this.year - O.year;
        }

    }
}