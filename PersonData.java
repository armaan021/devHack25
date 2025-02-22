import java.io.*;

public class PersonData {
    private String lastName;
    private String firstName;
    private String preferredName;

    public PersonData(String first, String last, String preferred) {
        this.firstName = first;
        this.lastName = last;
        this.preferredName = preferred;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPreferredName() {
        return this.preferredName;
    }
    
    public void generatePossiblePasswords() {
        try {
            FileWriter fw = new FileWriter("personalPasswords.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i <= 9; i++) {
                
            }

            pw.close();
        } catch (IOException ioe) {

        }
    }


}