import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testPassword {

    private static final String INTS = "0123456789";
    private static final String SC = "@_.!#";

    public static void testPasswordStrength(Password password, String filename) {

        try {
            FileReader fr = new FileReader(filename + ".txt");
            Scanner scnr =  new Scanner(fr);
            String testCurr;
            boolean found = false;
            while (scnr.hasNextLine() && !found) {
                testCurr = scnr.nextLine();
                if (testCurr.equalsIgnoreCase(password.getPassword())) {
                    found = true;
                } else if (password.getPassword().indexOf(testCurr) != -1) {
                    
                    String updateCracked = "";
                    for (int j = 0; j < Main.CRACKED.length(); j++) {
                        if (Main.CRACKED.charAt(j) == '*') {
                            if (j == password.getPassword().indexOf(testCurr)) {
                                updateCracked += testCurr;
                                j += testCurr.length() - 1;
                            } else {
                                updateCracked += "*";
                            }
                        } else {
                            updateCracked += Main.CRACKED.charAt(j);
                        }
                    }
                    Main.CRACKED = updateCracked;
                }
                Main.TRY++;
            }
            scnr.close();
        } catch (IOException ioe) {
            System.out.println();
        }
    }

    public static void testPasswordStrengthInt(Password password) {
        String updateCracked = "";
        for (int j = 0; j < Main.CRACKED.length(); j++) {
            if (Main.CRACKED.charAt(j) == '*') {
                boolean found = false;
                for (int i = 0; i < INTS.length() && !found; i++) {
                    if (INTS.charAt(i) == password.getPassword().charAt(j)) {
                        found = true;
                        updateCracked += INTS.charAt(i);
                    }
                }
                if (!found) {
                    updateCracked += Main.CRACKED.charAt(j);
                }
            } else {
                updateCracked += Main.CRACKED.charAt(j);
            }
        }
        Main.CRACKED = updateCracked;
    }
    
    public static void testPasswordStrengthSC(Password password) {
        String updateCracked = "";
        for (int j = 0; j < Main.CRACKED.length(); j++) {
            if (Main.CRACKED.charAt(j) == '*') {
                boolean found = false;
                for (int i = 0; i < SC.length() && !found; i++) {
                    if (SC.charAt(i) == password.getPassword().charAt(j)) {
                        found = true;
                        updateCracked += SC.charAt(i);
                    }
                }
                if (!found) {
                    updateCracked += Main.CRACKED.charAt(j);
                }
            } else {
                updateCracked += Main.CRACKED.charAt(j);
            }
        }
        Main.CRACKED = updateCracked;
    }

    // public static void testPasswordFirstName(PersonData person) {
    //     String updateCracked = "";
    //     System.out.println(Main.CRACKED.length());
    //     for (int j = 0; j < Main.CRACKED.length(); j++) {
    //         if (Main.CRACKED.charAt(j) == '*') {
    //             boolean found = false;
    //             for (int i = 0; i < person.getFirstName().length() && !found; i++) {
    //                 if (person.getFirstName().charAt(i) == person.getFirstName().charAt(j)) {
    //                     found = true;
    //                     updateCracked += person.getFirstName().charAt(i);
    //                 }
    //             }
    //             if (!found) {
    //                 updateCracked += Main.CRACKED.charAt(j);
    //             }
    //         } else {
    //             updateCracked += Main.CRACKED.charAt(j);
    //         }
    //     }
    //     Main.CRACKED = updateCracked;
    //     System.out.println(Main.CRACKED.length());

    // }

    // public static void testPasswordLastName(PersonData person) {
    //     String updateCracked = "";
    //     System.out.println(Main.CRACKED.length());
    //     for (int j = 0; j < Main.CRACKED.length(); j++) {
    //         if (Main.CRACKED.charAt(j) == '*') {
    //             boolean found = false;
    //             for (int i = 0; i < person.getLastName().length() && !found; i++) {
    //                 if (person.getLastName().charAt(i) == person.getLastName().charAt(j)) {
    //                     found = true;
    //                     updateCracked += person.getLastName().charAt(i);
    //                 }
    //             }
    //             if (!found) {
    //                 updateCracked += Main.CRACKED.charAt(j);
    //             }
    //         } else {
    //             updateCracked += Main.CRACKED.charAt(j);
    //         }
    //     }
    //     Main.CRACKED = updateCracked;
    //     System.out.println(Main.CRACKED.length());
    // }

    // public static void testPasswordPreferredName(PersonData person) {
    //     String updateCracked = "";
    //     System.out.println(Main.CRACKED.length());
    //     for (int j = 0; j < Main.CRACKED.length(); j++) {
    //         if (Main.CRACKED.charAt(j) == '*') {
    //             boolean found = false;
    //             for (int i = 0; i < person.getPreferredName().length() && !found; i++) {
    //                 if (person.getPreferredName().charAt(i) == person.getPreferredName().charAt(j)) {
    //                     found = true;
    //                     updateCracked += person.getPreferredName().charAt(i);
    //                 }
    //             }
    //             if (!found) {
    //                 updateCracked += Main.CRACKED.charAt(j);
    //             }
    //         } else {
    //             updateCracked += Main.CRACKED.charAt(j);
    //         }
    //     }
    //     Main.CRACKED = updateCracked;
    //     System.out.println(Main.CRACKED.length());
    // }

    

    // private static void checkLetterCaseSenstivity() {

    // }
}