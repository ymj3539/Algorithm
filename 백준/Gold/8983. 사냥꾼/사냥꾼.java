import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int M, N, L;
    static int[] Shooting_place;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());

        Shooting_place = new int[M];

        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<M; i++){
            Shooting_place[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(Shooting_place);

        int total = 0;

        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());

            total += find(x, y);
        }

        System.out.println(total);
    }

    static int find(int a, int b){
        int left = 0;
        int right = Shooting_place.length-1;
        int mid = (left + right)/2;

        while(left <= right){
            int x = Shooting_place[mid];    // 사대의 x 좌표
            int dist = Math.abs(x - a) + b; // 사대와 동물 사이의 거리
            if(dist <= L){
                return 1;
            }else if(dist > L){
                if(x < a){
                    left = mid + 1;
                    mid = (left + right) / 2;
                }else if(x > a){
                    right = mid - 1;
                    mid = (left + right) /2;
                }else return 0;
            }
        }

        return 0;
    }


}