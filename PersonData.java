import java.io.*;

public class PersonData {
    private String lastName;
    private String firstName;
    private String preferredName;
    private String hometown;

    public PersonData(String first, String last, String preferred, String hometown) {
        this.firstName = first;
        this.lastName = last;
        this.preferredName = preferred;
        this.hometown = hometown;
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

    public String getHometowm() {
        return this.hometown;
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