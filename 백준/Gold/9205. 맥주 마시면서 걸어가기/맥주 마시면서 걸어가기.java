import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static int T, n;
    static Point Sang;
    static List<Point> Stores;
    static Point Rock;
    public static void main(String[] args)throws Exception{
        T = Integer.parseInt(input.readLine());

        for(int t =1; t<=T; t++){
            n = Integer.parseInt(input.readLine());

            tokens = new StringTokenizer(input.readLine());
            Sang = new Point(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));

            Stores = new ArrayList<>();
            for(int i=0; i<n; i++){
                tokens = new StringTokenizer(input.readLine());
                Stores.add(new Point(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken())));
            }

            tokens = new StringTokenizer(input.readLine());
            Rock = new Point(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));

            boolean canGo = bfs();

            sb.append((canGo ? "happy" : "sad") + "\n");
        }

        System.out.println(sb);
        
    }

    static boolean bfs(){
        Queue<Point> queue = new LinkedList();
        queue.offer(Sang);
        boolean[] visited = new boolean[n];

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            int dist = Math.abs(cur.x - Rock.x) + Math.abs(cur.y - Rock.y);
            if(dist <= 1000) return true;

            for(int i=0; i<n; i++){
                Point store = Stores.get(i);

                if(visited[i]) continue;
                int dist2 = Math.abs(cur.x - store.x) + Math.abs(cur.y - store.y);
                if(dist2 <= 1000) {
                    queue.offer(store);
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}