import java.util.*;

public class hashData {
    public static void main(String[] args) {
        String word = "bhangua!1";

        HashMap<Character, Integer> hMap = new HashMap<Character, Integer>();
        for (int i = 0; i < word.length(); i++) {
            Integer temp = hMap.get(word.charAt(i));
            System.out.println(temp);
        }
        System.out.println(word.hashCode());
    }
}
