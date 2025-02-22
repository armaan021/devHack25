import java.io.*;
import java.util.Scanner;

public class PasswordHacker {
    private PersonData person;

    public PasswordHacker(PersonData person) {
        this.person = person;
        generatePossiblePasswords(this.person.getFirstName());
        generatePossiblePasswords(this.person.getLastName());
        generatePossiblePasswords(this.person.getHometowm());
        generatePossiblePasswords(this.person.getPreferredName());
        generatePossiblePasswordsString(this.person.getFirstName(), this.person.getLastName());
    }
    
    public static void passwordPossibilities() {
        generatePossiblePasswords(null);
        modifyCommonPasswords("10k-most-common");
        
    }

    public static void modifyCommonPasswords(String filename) {
        String abc = "0123456789abcdefghijklmnopqrstuvwxyz";

        try {
            for (int i = 0; i < abc.length(); i++) {
                FileWriter fw = new FileWriter("./AlphabeticalOrder/" + abc.charAt(i) + "_updated.txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.append(readFile(filename, abc.charAt(i)));
                pw.close();
            }
        } catch (IOException ioe){
            System.out.println("File not found.");
        }
    }

    public static String readFile(String filename, char k) {
        String result = "";
        try {
            FileReader fw = new FileReader(filename + ".txt");
            Scanner scnr = new Scanner(fw);
            
            String line;
            while (scnr.hasNextLine()) {
                line = scnr.nextLine();
                if (line.charAt(0) == k) {
                    result += line + "\n";
                }
            }
            
            scnr.close();
        } catch (IOException ioe) {

        }
        return result;
    }

    public static void generatePossiblePasswords(String word) {
        try {
            FileWriter fw = new FileWriter("./PersonalPasswordDataList/" + "personalPasswordPossibilities_" + word + ".txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.append(combinations(word));
            pw.close();
        } catch (IOException ioe){
            System.out.println("File not found.");
        }
    }
    public static void generatePossiblePasswordsString(String word, String word2) {
        try {
            FileWriter fw = new FileWriter("./PersonalPasswordDataList/" + "personalPasswordPossibilities_" + word + "_" + word2 + ".txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.append(combinationsStrings(word, word2));
            pw.close();
        } catch (IOException ioe){
            System.out.println("File not found.");
        }
    }

    private static String combinations(String name) {
        String combinations = "";
        char[] chars  = name.toCharArray();

        for (int i = 0; i < Math.pow(2, chars.length); i++) {
            char[] result = new char[chars.length];

            for (int n = 0; n < chars.length; n++) {
                result[n] = isBitSet(i, n) ? Character.toUpperCase(chars[n])
                                           : Character.toLowerCase(chars[n]);
            }

            combinations += new String(result) + "\n";
        }

        return combinations;
    }

    private static String combinationsStrings(String s1, String s2) {
        String result = "";
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                String word = s1.substring(0, i + 1) + s2.substring(j);
                result += combinations(word);
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {
                String word = s2.substring(0, i + 1) + s1.substring(j);
                result += combinations(word);
            }
        }
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                String word = s1.substring(i) + s2.substring(0, j+1);
                result += combinations(word);
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {
                String word = s2.substring(i) + s1.substring(0, j+1);
                result += combinations(word);
            }
        }
        return result;
    }

    private static boolean isBitSet(int i, int n) {
        return ((i & (1 << n)) != 0);
    }
}