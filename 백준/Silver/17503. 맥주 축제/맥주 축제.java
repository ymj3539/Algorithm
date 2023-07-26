import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    /*
    * N : 축제 기간(총 맥주병 수)
    * M : 선호도의 합
    * K : 맥주 종류 수
    * */
    static int N, M, K;
    static Beer[] beers;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        beers = new Beer[K];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<K; i++){
            tokens = new StringTokenizer(input.readLine());
            beers[i] = new Beer(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
        }

        Arrays.sort(beers);

        long totalLike = 0;
        int ans = -1;

        for(int i=0; i<K; i++){
            pq.offer(beers[i].like);
            totalLike += beers[i].like;

            if(pq.size() > N){
                totalLike -= pq.poll();
            }

            if(pq.size() == N){
                // 선호도 체크

                if(totalLike >= M) {
                    ans = beers[i].alcohol;
                    break;
                }
            }

        }

        System.out.println(ans);

    }

    static class Beer implements Comparable<Beer>{
        int like;
        int alcohol;
        public Beer(int like, int alcohol){
            this.like = like;
            this.alcohol = alcohol;
        }


        @Override
        public int compareTo(Beer o) {
            if(this.alcohol != o.alcohol){
                return this.alcohol - o.alcohol;
            }
            return this.like - o.like;

        }

    }
}