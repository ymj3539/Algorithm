import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][] map;
    public static void main(String[] args) throws Exception{
         tokens = new StringTokenizer(input.readLine());
         int R = Integer.parseInt(tokens.nextToken());
         int C = Integer.parseInt(tokens.nextToken());
         int K = Integer.parseInt(tokens.nextToken());

         map = new int[3][3];
         for(int r=0; r<3; r++){
             tokens = new StringTokenizer(input.readLine());
             for(int c=0; c<3; c++) {
                 map[r][c] = Integer.parseInt(tokens.nextToken());
             }
         }

         int answer = -1;
         int sec = 0;

         while(sec<=100){
             // r,c 위치값이 k인지 확인
             if(map.length>=R && map[0].length >=C){
                 if(map[R-1][C-1] == K) {
                     answer = sec;
                     break;
                 }
             }


             // 1. 행 갯수와 열 갯수 확인
             int rCnt = map.length;
             int cCnt = map[0].length;
             // (행 개수 >= 열 개수) -> R 연산
             if(rCnt >= cCnt){
                 // R 연산
                 R();
             }
             // (행 개수 < 열 개수) -> C 연산
             else {
                 C();
             }
             sec++;
         }

         System.out.println(answer);

    }

    static void R(){
        List[] listArr = new List[map.length];
        int maxSize = Integer.MIN_VALUE;
        for(int r=0; r<map.length; r++){
            Map<Integer, Integer> Hashmap = new HashMap<>();
            listArr[r] = new ArrayList<>();
            for(int c=0; c<map[0].length; c++){
                if(map[r][c] == 0) continue;
                if(Hashmap.get(map[r][c]) != null){
                    Hashmap.put(map[r][c], Hashmap.get(map[r][c])+1);
                }else Hashmap.put(map[r][c], 1);
            }

            for(Integer key : Hashmap.keySet()){
                listArr[r].add(new Point(key, Hashmap.get(key)));
            }

            // 정렬
            Collections.sort(listArr[r]);

            // Max size
            maxSize = Math.max(maxSize, listArr[r].size());
        }

        int[][] copymap = new int[map.length][maxSize * 2];

        outer : for(int r=0; r<map.length; r++){
            List<Point> list = listArr[r];
            int c = 0;
            for(Point p : list){
                copymap[r][c++] = p.num;
                copymap[r][c++] = p.cnt;

                if(c>=100) break outer;
            }
        }

        map = copymap;
    }

    static void C(){
        List[] listArr = new List[map[0].length];
        int maxSize = Integer.MIN_VALUE;
        for(int c=0; c<map[0].length; c++){
            Map<Integer, Integer> Hashmap = new HashMap<>();
            listArr[c] = new ArrayList<>();
            for(int r=0; r<map.length; r++){
                if(map[r][c] == 0) continue;
                if(Hashmap.get(map[r][c]) != null){
                    Hashmap.put(map[r][c], Hashmap.get(map[r][c])+1);
                }else Hashmap.put(map[r][c], 1);
            }

            for(Integer key : Hashmap.keySet()){
                listArr[c].add(new Point(key, Hashmap.get(key)));
            }

            // 정렬
            Collections.sort(listArr[c]);

            // Max size
            maxSize = Math.max(maxSize, listArr[c].size());
        }

        int[][] copymap = new int[maxSize*2][map[0].length];

        outer : for(int c=0; c<map[0].length; c++){
            List<Point> list = listArr[c];
            int r = 0;
            for(Point p : list){
                copymap[r++][c] = p.num;
                copymap[r++][c] = p.cnt;
                if(r>=100) break outer;
            }
        }

        map = copymap;
    }

    static class Point implements Comparable<Point>{
        int num, cnt;
        public Point(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point p1){
            if(this.cnt == p1.cnt){
                return this.num - p1.num;
            }else return this.cnt - p1.cnt;
        }
    }
}