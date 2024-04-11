import java.util.*;
import java.io.*;
public class Main {
    /*
    * 손해를 덜 보려면 맛 차이가 큰 것에 5000원을 투자해야돼
    * */
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int X = Integer.parseInt(tokens.nextToken());

        int remain_money = X;
        int remain_days = N;
        Menu[] menus = new Menu[N];
        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());
            if(A >= B){
                menus[i] = new Menu(A, B, false);
            }else {
                menus[i] = new Menu(B, A, true);
            }

        }

        Arrays.sort(menus);

        int sum = 0;

        for(int i=0; i<N; i++){
//            System.out.println(menus[i].toString());
            if(menus[i].reverse) {
                sum += menus[i].A;
                remain_money -= 1000;
                continue;
            }
            if(remain_money >= 5000){
                // 이후 남은 일수 * 1000 보다 남은 돈이 같거나 많아야돼
                if((N-i-1) * 1000 <= remain_money - 5000){
                    sum+= menus[i].A;
                    remain_money -= 5000;
                    continue;
                }
            }

            sum += menus[i].B;
            remain_money -= 1000;
        }

        System.out.println(sum);

    }

    static class Menu implements Comparable<Menu>{
        int A, B;
        boolean reverse;

        public Menu(int A, int B, boolean reverse){
            this.A = A;
            this.B = B;
            this.reverse = reverse;
        }

        @Override
        public int compareTo(Menu O){
            return Math.abs(O.A- O.B) - Math.abs(this.A - this.B);
        }

        @Override
        public String toString(){
            return this.A + " " + this.B + " "+this.reverse;
        }
    }
}