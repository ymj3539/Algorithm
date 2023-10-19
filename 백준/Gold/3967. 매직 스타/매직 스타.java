import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    static char[][] newMap;
    static List<Point> list;
    static boolean[] visited = new boolean[12];
    static List<Character> alphabet;
    static StringBuilder sb = new StringBuilder();
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        map = new char[5][9];
        list = new ArrayList<>();
        alphabet = new ArrayList<>();

        for(int r=0; r<5; r++){
            String str = input.readLine();
            for(int c=0; c<9; c++){
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'x') list.add(new Point(r, c));
                else if(map[r][c] != '.') {
                    visited[map[r][c] - 'A'] = true;
                }
            }
        }

        // 사용 가능한 알파벳
        for(int i=0; i<12; i++){
            if(!visited[i]){
                alphabet.add((char)(65 + i));
            }
        }

        perm(0, new char[alphabet.size()], new boolean[alphabet.size()]);

        System.out.println(sb);
    }

    static void perm(int nth, char[] choosed, boolean[] visited){
        if(flag) return;
        if(nth == list.size()){
            // 매직스타 조건 만족하는지 체크
            newMap = new char[5][9];
            for(int r=0; r<5; r++){
                newMap[r] = map[r].clone();
            }
            if(canStar(choosed)){
                for(int r=0; r<5; r++){
                    for(int c=0; c<9; c++){
                        sb.append(newMap[r][c]);
                    }

                    sb.append("\n");
                }
                flag = true;
            }
            return;

        }

        for(int i =0; i<alphabet.size(); i++){
            if(!visited[i]){
                choosed[nth] = alphabet.get(i);
                visited[i] = true;
                perm(nth+1, choosed, visited);
                visited[i] = false;
            }
        }
    }

    static boolean canStar(char[] choosed){
        for(int i=0; i<choosed.length; i++){
            Point cur = list.get(i);
            newMap[cur.r][cur.c] = choosed[i];
        }

        // 매직 스타 체크
        if((newMap[0][4] - 'A' +1) + (newMap[1][3] - 'A' + 1) + (newMap[2][2] -'A' + 1) + (newMap[3][1] -'A' +1) != 26) return false;
        if((newMap[0][4] - 'A' +1) + (newMap[1][5] - 'A' + 1) + (newMap[2][6] -'A' + 1) + (newMap[3][7] -'A' +1) != 26) return false;
        if((newMap[1][1] - 'A' +1) + (newMap[1][3] - 'A' + 1) + (newMap[1][5] -'A' + 1) + (newMap[1][7] -'A' +1) != 26) return false;
        if((newMap[3][1] - 'A' +1) + (newMap[3][3] - 'A' + 1) + (newMap[3][5] -'A' + 1) + (newMap[3][7] -'A' +1) != 26) return false;
        if((newMap[1][1] - 'A' +1) + (newMap[2][2] - 'A' + 1) + (newMap[3][3] -'A' + 1) + (newMap[4][4] -'A' +1) != 26) return false;
        if((newMap[1][7] - 'A' +1) + (newMap[2][6] - 'A' + 1) + (newMap[3][5] -'A' + 1) + (newMap[4][4] -'A' +1) != 26) return false;

        return true;
    }


    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}