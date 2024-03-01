import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static char[] alpha = new char[26];
    public static void main(String[] args) throws Exception{

        // 단어별 알파벳 갯수 저장 배열
        int[][] words_alpha_cnt = new int[200000][26];
        int n = 0;

        // 단어장
        List<String> word_list = new ArrayList<>();

        // 1. 단어 입력받기
        while(true){
            String str = input.readLine();
            if(str.equals("-")) break;
            // 단어별 알파벳 갯수 카운트
            for(int i=0; i<str.length(); i++){
                words_alpha_cnt[n][str.charAt(i) - 'A']++;
            }
            // 단어장에 추가
            word_list.add(str);
            n++;
        }

        StringBuilder sb = new StringBuilder();

        // 2. 퍼즐판 입력받기
        while (true) {
            String str = input.readLine();
            if(str.equals("#")) break;

            // 퍼즐에 있는 알파벳 갯수 카운트
            int[] puz_alpha = new int[26];
            for(int i=0; i<str.length(); i++) {
                puz_alpha[str.charAt(i) -'A']++;
            }

            int[] letters_cnt = new int[26]; // 유효단어들의 알파벳 갯수 total 합

            // 3. 단어장의 단어와 퍼즐 문자 비교
            for(int i=0; i<n; i++){
                boolean isValid = true;
                for(int j=0; j<26; j++){
                    if(words_alpha_cnt[i][j] > puz_alpha[j]){
                        isValid = false;
                        break;
                    }
                }
                // 퍼즐에 있는 문자들로 해당 단어 만들 수 있으면 유효단어 배열에 추가
                // 4. 유효단어가 가진 문자 갯수 체크 (중복체크 X)
                if(isValid){
                    for(int j=0; j<26; j++){
                        if(words_alpha_cnt[i][j] != 0) letters_cnt[j]++;
                    }
                }
            }




            // 제일 적게 사용된 문자와 제일 많이 사용된 문자 체크
            int max_cnt = Integer.MIN_VALUE;
            int min_cnt = Integer.MAX_VALUE;
            List<Character> max_list = new ArrayList<>();
            List<Character> min_list = new ArrayList<>();

            for(int i=0; i<26; i++){
                if(puz_alpha[i] == 0) continue;
                int cnt = letters_cnt[i];
                char letter = (char)(i+'A');

                // 최솟값 체크
                if(cnt < min_cnt){
                    min_list = new ArrayList<>();
                    min_list.add(letter);
                    min_cnt = cnt;
                }else if(cnt == min_cnt){
                    min_list.add(letter);
                }

                // 최대값 체크
                if(cnt > max_cnt){
                    max_list = new ArrayList<>();
                    max_list.add(letter);
                    max_cnt = cnt;
                }else if(cnt == max_cnt){
                    max_list.add(letter);
                }
            }

            Collections.sort(max_list);
            Collections.sort(min_list);

            StringBuilder min_sb = new StringBuilder();
            for(Character ch : min_list){
                min_sb.append(ch);
            }

            StringBuilder max_sb = new StringBuilder();
            for(Character ch : max_list){
                max_sb.append(ch);
            }

            sb.append(min_sb+" "+min_cnt+" "+max_sb+" "+max_cnt+"\n");

        }

        System.out.println(sb);
    }

}