import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static Room[] rooms;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        rooms = new Room[N];

        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(input.readLine());
            int num = Integer.parseInt(tokens.nextToken());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            rooms[num-1] = new Room(start, end);
        }

        Arrays.sort(rooms);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(rooms[0].end);
        for(int i=1; i<N; i++){
            int end = pq.peek();
            if(rooms[i].start >= end){
                pq.poll();
            }
            pq.offer(rooms[i].end);
        }

        System.out.println(pq.size());

    }

    static class Room implements Comparable<Room>{
        int start, end;
        public Room(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Room O){
            if(this.start == O.start) return this.end - O.end;
            return this.start - O.start;
        }
    }
}