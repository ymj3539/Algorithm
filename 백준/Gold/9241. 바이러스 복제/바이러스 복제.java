import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        String before = input.readLine();
        String after = input.readLine();

        int bLen = before.length();
        int aLen = after.length();

        int min = Math.min(bLen, aLen);

        int leftIdx = min;
        for(int i=0; i<min; i++){
            if(before.charAt(i) != after.charAt(i)){
                leftIdx = i;
                break;
            }
        }



        int rightIdx = min;
        for(int i=0; i<min; i++){
            if(before.charAt(bLen -1 - i) != after.charAt(aLen - 1 -i)){
                rightIdx = i;
                break;
            }
        }

        if(leftIdx != 0 && leftIdx == rightIdx){
            System.out.println(aLen-min);
        }
        else {
//            System.out.println(aLen+" "+rightIdx+" "+leftIdx);
            System.out.println(aLen-rightIdx - leftIdx);
        }
    }
}