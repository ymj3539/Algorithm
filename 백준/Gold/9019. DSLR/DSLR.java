import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int A, B;
    static boolean Flag;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(input.readLine());

        for(int t=0; t<T; t++){
            tokens = new StringTokenizer(input.readLine());
            Flag = false;

            A = Integer.parseInt(tokens.nextToken());
            B = Integer.parseInt(tokens.nextToken());
            bfs();

        }
        System.out.println(answer);
    }

    static void bfs(){
        Queue<Register> queue = new LinkedList<>();
        queue.offer(new Register(A, ""));
        boolean[] visited = new boolean[10000];
        visited[A] = true;

        while(!queue.isEmpty()){
            Register cur = queue.poll();
            if(cur.num == B) answer.append(cur.str+"\n");

            // D 연산
            int newNum = calD(cur.num);
            if(!visited[newNum]) {
                queue.offer(new Register(newNum, cur.str+"D"));
                visited[newNum] = true;
            }

            // S 연산
            newNum = calS(cur.num);
            if(!visited[newNum]) {
                queue.offer(new Register(newNum, cur.str+"S"));
                visited[newNum] = true;
            }

            // L연산
            newNum = calL(cur.num);
            if(!visited[newNum]) {
                queue.offer(new Register(newNum, cur.str+"L"));
                visited[newNum] = true;
            }

            // R 연산
            newNum = calR(cur.num);
            if(!visited[newNum]) {
                queue.offer(new Register(newNum, cur.str+"R"));
                visited[newNum] = true;
            }
        }
    }

    static int calR(int num){
        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        int d4 = 0;
        if(num >=1000){
            d1 = num/1000;
            num -= d1*1000;
        }
        if(num >= 100){
            d2 = num/100;
            num -= d2*100;
        }
        if(num >= 10){
            d3 = num/10;
            num -= d3*10;
        }
        d4 = num;
        return (d4*1000 + d1*100 + d2*10 + d3);
    }

    static int calL(int num){
        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        int d4 = 0;
        if(num >=1000){
            d1 = num/1000;
            num -= d1*1000;
        }
        if(num >= 100){
            d2 = num/100;
            num -= d2*100;
        }
        if(num >= 10){
            d3 = num/10;
            num -= d3*10;
        }
        d4 = num;
        return (d2*1000 + d3*100 + d4*10 + d1);
    }

    static int calD(int num){
        return (2*num % 10000);
    }

    static int calS(int num){
        num -= 1;
        if(num == -1) return 9999;
        return num;
    }

    static class Register{
        int num;
        String str;
        public Register(int num, String str){
            this.num = num;
            this.str = str;
        }
    }

}