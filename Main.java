import java.util.ArrayList;
import java.util.Scanner;
public class Main {

      public static int TRY = 0;
      public static String CRACKED = "";

      public static void main(String[] args){
            Scanner scnr = new Scanner(System.in);
            PasswordHacker.modifyCommonPasswords("commonPasswords");

            System.out.println("---- WELCOME TO CYBER AWARENESS ----\n" 
            + "This is a password security challenge:\n" 
            + "\t> You are going to see a login page.\n" 
            + "\t> You will be asked to submit a login form.\n" 
            + "\t> You will be ranked on the strength of your password.");

            System.out.print("Enter your last name: ");
            String lastName = scnr.nextLine();
            System.out.print("Enter your first name: ");
            String firstName = scnr.nextLine();
            System.out.print("Enter your preferred name: ");
            String preferredName = scnr.nextLine();
            System.out.print("Enter your Date of Birth(YYYY-MM-DD): ");
            String dob = scnr.nextLine();
            System.out.print("Enter your Hometown: ");
            String hometown = scnr.nextLine();

            PersonData person = new PersonData(firstName, lastName, preferredName, dob, hometown);
            PasswordHacker hacker = new PasswordHacker(person);
            
            Password pass;
            boolean valid = true;
            

            do {
                  System.out.print("Enter your password: ");
                  String password = scnr.nextLine();
                  pass = new Password(password);
                  
            } while(pass.checkLength());


            Password passConfirm;
            do {
                  System.out.print("Confirm your password: ");
                  String passwordConfir = scnr.nextLine();
                  passConfirm = new Password(passwordConfir);
                  
            } while(pass.isEqual(passConfirm));


            ArrayList<String> questions = new ArrayList<String>();
            Question ques = new Question(questions);
            ques.ques();
            System.out.println("So now the computer will try to guess your password !!!!");
            System.out.println(questions.get(0));
            
            int num = validIntLength(scnr);
            
            
            for(int i = 0; i < num; i++){
                  CRACKED  += "*";
            }
            
            testPassword.testPasswordStrength(pass, "10k-most-common");
            System.out.println("Password cracked: " + CRACKED);

            System.out.println(questions.get(1));
            if (containsName(scnr) == 1) {
                  if (pass.getPassword().indexOf(firstName) != -1) {
                        String updateCracked = "";
                        String testCurr = person.getFirstName();
                          for (int j = 0; j < Main.CRACKED.length(); j++) {
                              if (Main.CRACKED.charAt(j) == '*') {
                                  if (j == pass.getPassword().indexOf(testCurr)) {
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
            }
            System.out.println("Password cracked: " + CRACKED);
            
            
            System.out.println(questions.get(2));
            if (containsName(scnr) == 1) {
                  if (pass.getPassword().indexOf(lastName) != -1) {
                        String updateCracked = "";
                        String testCurr = person.getLastName();
                          for (int j = 0; j < Main.CRACKED.length(); j++) {
                              if (Main.CRACKED.charAt(j) == '*') {
                                  if (j == pass.getPassword().indexOf(testCurr)) {
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
            }
            System.out.println("Password cracked: " + CRACKED);
            
            
            System.out.println(questions.get(3));
            if (containsName(scnr) == 1) {
                  if (pass.getPassword().indexOf(preferredName) != -1) {
                        String updateCracked = "";
                        String testCurr = person.getPreferredName();
                          for (int j = 0; j < Main.CRACKED.length(); j++) {
                              if (Main.CRACKED.charAt(j) == '*') {
                                  if (j == pass.getPassword().indexOf(testCurr)) {
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
            }
            System.out.println("Password cracked: " + CRACKED);


            System.out.println(questions.get(4));
            if (containsName(scnr) == 1) {
                  
            }
      }








      public static int validIntLength(Scanner scnr){
            String input;
            int num = -1;

            while (num < 0) {
                  System.out.print("Enter your answer: ");
                  input = scnr.nextLine();
                  
                  try {
                        num = Integer.parseInt(input);
                  } catch(NumberFormatException nfe) {
                        System.out.println("Please enter a number.");
                  }
            }
            return num; 
      }
      
      public static int containsName(Scanner scnr){
            String input;
            int num = -1;

            while (num == -1) {
                  System.out.print("Enter your answer(Y/N): ");
                  input = scnr.nextLine();
                  
                  if (input.equalsIgnoreCase("y")) {
                        num = 1;
                  } else if (input.equalsIgnoreCase("n")){
                        num = 0;
                  }
            }
            return num; 
      }
      
}


     


