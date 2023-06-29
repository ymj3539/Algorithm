import java.util.*;
import java.io.*;
/*
Boj_21776_가희와읽기쓰기놀이
 */
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, C;
    static List<Integer>[] players;
    static List<CardCommand>[] card;
    static TreeSet<String> treeSet = new TreeSet<>();
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        players = new List[N];
        card = new List[C+1];
        int[] player_card_cnt = new int[N];  // 각 플레이어가 가진 카드 수(순열 구하기용)

        // 각각 플레이어가 낸 카드
        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(input.readLine());
            int card_cnt = Integer.parseInt(tokens.nextToken());
            player_card_cnt[i] = card_cnt;
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<card_cnt; j++){
                list.add(Integer.parseInt(tokens.nextToken()));
            }
            players[i] = list;
        }

        // 각각의 카드에 해당하는 명령어
        for(int i=1; i<=C; i++){
            String input_str = input.readLine();
            String[] card_str = input_str.split(",");
            int size = card_str.length;

            List<CardCommand> list = new ArrayList<>();
            for(int j=0; j<size; j++ ){
                String[] tmp_str = card_str[j].split(" ");
                list.add(new CardCommand(tmp_str[0], tmp_str[1]));
            }

            card[i] = list;
        }


        // 플레이어 순서 정하기

        perm(0, new int[C], player_card_cnt);

        StringBuilder sb= new StringBuilder();
        Iterator<String> iter = treeSet.iterator();
        while(iter.hasNext()) {
            sb.append(iter.next() +"\n");
        }

        System.out.println(sb);

    }

    static void perm(int nth, int[] choosed, int[] visited){
        if(nth == C){
            // 순서에 맞는 카드 채워넣기
            int[] newChoosed = new int[C];
            for(int i=0; i<N; i++){
                int index =0;
                for(int j=0; j<C; j++){
                    if(choosed[j] == i){
//                        System.out.println("i: "+i+", j: "+j+", index: "+index);
                        newChoosed[j] = players[i].get(index);
                        index++;
                    }
                }
            }


            // 연산 수행
            String game_result = calculate(newChoosed);
            if(game_result.length() == 0){
                game_result = "EMPTY";
            }
            treeSet.add(game_result);
            return;
        }

        for(int i=0; i<N; i++){
            if(visited[i] <=0 ) continue;
            choosed[nth] = i;
            visited[i]--;
            perm(nth+1, choosed, visited);
            visited[i]++;
        }
    }

    static String calculate(int[] choosed){
        String str = "";
        int size = choosed.length;
        // 선택된 카드 순서대로 실행
        for(int i=0; i<size; i++){
                int idx = choosed[i];
                List<CardCommand> cardCommands = card[idx];
                int size2 = cardCommands.size();
                // 카드 순서대로 명령 실행
                for(int k=0; k<size2; k++){
                    CardCommand cur = cardCommands.get(k);
                    if(cur.command.equals("ADD")){
                        str = add_method(str, cur.letter);
                    }else {
                        String result = del_method(str, Integer.parseInt(cur.letter));
                        if(result.equals("ERROR")) {
                            return "ERROR";
                        }else {
                            str = result;
                        }
                    }
                }
        }

        return str;
    }

    static String del_method(String str, int index){
        if(str.length() <= index){
            return "ERROR";
        }else {
            // 해당 index에 위치하는 글자 삭제
            String newStr = str.substring(0,index) + str.substring(index+1, str.length());
            return newStr;
        }
    }

    static String add_method(String str, String letter){
        return str+letter;
    }

    static class CardCommand{
        String command;
        String letter;

        public CardCommand(String command, String letter){
            this.command = command;
            this.letter = letter;
        }
    }

}