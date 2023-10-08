import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    /*
    * N : 웹페이지의 종류의 수
    * Q : 사용자가 수행하는 작업의 개수
    * C : 최대 캐시 용량
    * */
    static int N, Q, C;
    static int[] CAP;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        Q = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        CAP = new int[N+1];

        tokens = new StringTokenizer(input.readLine());
        for(int i=1; i<=N; i++){
            CAP[i] = Integer.parseInt(tokens.nextToken());
        }

        Deque<Integer> b_stack = new ArrayDeque<>();
        Deque<Integer> f_stack = new ArrayDeque<>();

        int cur = -1;
        int b_cache = 0;
        int f_cache = 0;

        for(int i=0; i<Q; i++){
            tokens = new StringTokenizer(input.readLine());
            char task = tokens.nextToken().charAt(0);
            if(task == 'B'){
                // stack에 1개 이상의 페이지가 저장되어 있을 때만
                if(!b_stack.isEmpty()){
                    // 현재 페이지 -> 앞으로가기 공간에 저장
                    f_stack.offer(cur);
                    f_cache += CAP[cur];
                    // 뒤로 가기 공간의 가장 최근 페이지에 접속
                    cur = b_stack.removeLast();
                    b_cache -= CAP[cur];
                }
            }else if(task == 'F'){
                if(!f_stack.isEmpty()){
                    // 현재 페이지 -> 뒤로가기 공간에 저장
                    b_stack.offer(cur);
                    b_cache += CAP[cur];
                    // 앞으로 가기 공간의 가장 최근 페이지에 접속
                    cur = f_stack.removeLast();
                    f_cache -= CAP[cur];
                }
            }else if(task == 'A'){
                // 접속할 웹 페이지 번호
                int page = Integer.parseInt(tokens.nextToken());
                // 1. 앞으로 가기 공간에 저장된 페이지 모두 삭제
                f_stack.clear();
                f_cache = 0;
                // 2. 현재 페이지 -> 뒤로 가기에 추가
                if(cur != -1){
                    b_stack.offer(cur);
                    b_cache += CAP[cur];
                }
                cur = page;

                // 3. 캐시 용량 초과
                while(f_cache+b_cache+CAP[cur] > C){
                    int tmp = b_stack.removeFirst();
                    b_cache -= CAP[tmp];
                }

            }else if(task == 'C'){
                Deque<Integer> new_stack = new ArrayDeque<>();
                while(b_stack.size() > 1){
                    int tmp = b_stack.removeFirst();
                    if(tmp == b_stack.peekFirst()){
                        b_cache -= CAP[tmp];
                    }else{
                        new_stack.offerLast(tmp);
                    }
                }
                if(!b_stack.isEmpty()){
                    int tmp = b_stack.removeFirst();
                    new_stack.offerLast(tmp);
                }
                b_stack = new_stack;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cur+"\n");
        if(b_stack.isEmpty()){
            sb.append(-1);
        }else {
            while(!b_stack.isEmpty()){
                sb.append(b_stack.removeLast()+" ");
            }
        }
        sb.append("\n");
        if(f_stack.isEmpty()){
            sb.append(-1);
        }else {
            while(!f_stack.isEmpty()){
                sb.append(f_stack.removeLast()+" ");
            }
        }

        System.out.println(sb);
    }
}