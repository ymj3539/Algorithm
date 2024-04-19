import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[] nums;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        nums = new int[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(tokens.nextToken());
        }


        tokens = new StringTokenizer(input.readLine());
        int plusCnt = Integer.parseInt(tokens.nextToken());
        int minusCnt = Integer.parseInt(tokens.nextToken());
        int multiCnt = Integer.parseInt(tokens.nextToken());
        int divideCnt = Integer.parseInt(tokens.nextToken());

        dfs(0, plusCnt, minusCnt, multiCnt, divideCnt, new int[N-1]);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int nth, int plusCnt, int minusCnt, int multiCnt, int divideCnt, int[] choosed){
        if(nth == N-1){
            int sum = cal(choosed);
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }

        if(plusCnt > 0){
            choosed[nth] = 0;
            dfs(nth+1, plusCnt-1, minusCnt, multiCnt, divideCnt, choosed);
        }

        if(minusCnt > 0){
            choosed[nth] = 1;
            dfs(nth+1, plusCnt, minusCnt-1, multiCnt, divideCnt, choosed);
        }

        if(multiCnt > 0){
            choosed[nth] = 2;
            dfs(nth+1, plusCnt, minusCnt, multiCnt-1, divideCnt, choosed);
        }

        if(divideCnt > 0){
            choosed[nth] = 3;
            dfs(nth+1, plusCnt, minusCnt, multiCnt, divideCnt-1, choosed);
        }

    }

    static int cal(int[] choosed){
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Integer> operatorStack = new ArrayDeque<>();
        numStack.add(nums[0]);
        for(int i=0; i<choosed.length; i++){
            if(choosed[i] == 0){
                operatorStack.addLast(0);
                numStack.addLast(nums[i+1]);
            }
            else if(choosed[i] == 1) {
                operatorStack.addLast(1);
                numStack.addLast(nums[i+1]);
            }

            else if(choosed[i] == 2){
                numStack.addLast(numStack.pollLast()* nums[i+1]);
            }
            else if(choosed[i] == 3){
                numStack.addLast(numStack.pollLast() / nums[i+1]);
            }
        }

        int sum = numStack.pollFirst();
        while(!operatorStack.isEmpty()){
            int num = numStack.pollFirst();
            int operator = operatorStack.pollFirst();

            if(operator == 0 ) sum += num;
            else if(operator == 1) sum -= num;
        }

        return sum;
    }
}