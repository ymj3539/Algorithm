import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static Map<Integer, Station> stationInfo;
    static Map<Integer, Integer> map = new HashMap<>();
    static List<Station>[] stationLinkedlist = new List[101];
    static List<Integer>[] stationLinelist = new List[101];
    static int END;

    static int N;
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        for(int i=0; i<101; i++){
            stationLinkedlist[i] = new ArrayList<>();
            stationLinelist[i] = new ArrayList<>();
        }

        int tmpIdx = 1;

        for(int i=1; i<=N; i++){
            tokens = new StringTokenizer(input.readLine());
            int K = Integer.parseInt(tokens.nextToken());

            int pre = -1;
            for(int k=0; k<K; k++){
                int cur = Integer.parseInt(tokens.nextToken());
                if(!map.containsKey(cur)){
                    if(cur == 0) {
                        map.put(0, 0);
                        cur = 0;
                    }
                    else {
                        map.put(cur, tmpIdx);
                        cur = tmpIdx;
                        tmpIdx++;
                    }
                }else {
                    cur = map.get(cur);
                }

                if(pre != -1){
                    stationLinkedlist[pre].add(new Station(cur, i, 0));
                    stationLinkedlist[cur].add(new Station(pre, i, 0));
                }
                pre = cur;
                stationLinelist[cur].add(i);
            }
        }

        END = map.get(Integer.parseInt(input.readLine()));

        bfs();
        System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
    }

    static void bfs(){
        Queue<Station> queue = new LinkedList<>();
        int[][] visited = new int[11][101];
        for(int[] i : visited){
            Arrays.fill(i, Integer.MAX_VALUE);
        }
        for(int i=0; i<stationLinelist[0].size(); i++){
            queue.offer(new Station(0, stationLinelist[0].get(i), 0));
            visited[stationLinelist[0].get(i)][0] = 0;
        }


        while(!queue.isEmpty()){
            Station cur = queue.poll();

            if(cur.num == END) MIN = Math.min(MIN, cur.Tcnt);

            for(int i=0; i<stationLinkedlist[cur.num].size(); i++){
                Station next = stationLinkedlist[cur.num].get(i);
                if(visited[next.line][next.num] > cur.Tcnt) {
                    if(next.line == cur.line) {
                        visited[next.line][next.num] = cur.Tcnt;
                        queue.offer(new Station(next.num, next.line, cur.Tcnt));
                    }else {
                        visited[next.line][next.num] = cur.Tcnt + 1;
                        queue.offer(new Station(next.num, next.line, cur.Tcnt +1));
                    }
                }
            }
        }

    }

    static class Station{
        int num, line, Tcnt;
        public Station(int num, int line, int Tcnt){
            this.num = num;
            this.line = line;
            this.Tcnt = Tcnt;
        }

    }
}