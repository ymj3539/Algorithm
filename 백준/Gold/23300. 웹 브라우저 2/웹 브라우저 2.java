import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, Q;
    static Deque<Integer> back;
    static Deque<Integer> front;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        Q = Integer.parseInt(tokens.nextToken());

        back = new ArrayDeque<>();
        front = new ArrayDeque<>();

        int cur = -1;

        for(int i=0; i<Q; i++){
            tokens = new StringTokenizer(input.readLine());
            char q = tokens.nextToken().charAt(0);
            // 1. 뒤로 가기 명령
            if(q == 'B'){
                if(!back.isEmpty()){
                    // 현재 보고 있던 웹페이지 앞으로 가기 공간에 저장
                    front.addLast(cur);

                    // 뒤로 가기 공간에서 가장 최근 페이지에 접속
                    cur = back.removeLast();
                }
            }
            // 2. 앞으로 가기 명령
            else if(q == 'F'){
                if(!front.isEmpty()){
                    // 현재 페이지 -> 뒤로 가기 공간에 저장
                    back.addLast(cur);
                    // 앞으로 가기 공간에서 가장 최근 페이지에 접속
                    cur = front.removeLast();

                }
            }
            // 3. 페이지 이동
            else if(q=='A'){
                int page = Integer.parseInt(tokens.nextToken());
                // 앞으로 가기 공간 모두 삭제
                front.clear();

                // 현재 페이지 -> 뒤로 가기 공간에 추가
                if(cur != -1){
                    back.addLast(cur);
                }

                // page로 접속
                cur = page;
            }

            // 4. 압축
            else if(q=='C'){
                Deque<Integer> new_back = new ArrayDeque<>();
                if(back.size() >= 2){
                    while(back.size() >= 2){
                        int num = back.removeFirst();
                        if(num != back.peekFirst()){
                            new_back.addLast(num);
                        }
                    }
                }

                if(!back.isEmpty()){
                    int num = back.removeFirst();
                    new_back.addLast(num);
                }

                back = new_back;
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append(cur+"\n");

        if(back.isEmpty()) sb.append(-1);
        else {
            while(!back.isEmpty()){
                sb.append(back.removeLast()+" ");
            }
        }
        sb.append("\n");

        if(front.isEmpty()) sb.append(-1);
        else {
            while(!front.isEmpty()){
                sb.append(front.removeLast()+" ");
            }
        }


        System.out.println(sb);
    }
}