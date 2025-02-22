import java.util.ArrayList;
import java.io.*;

public class Question {
   private ArrayList<String> questions;

   public Question(ArrayList<String> questions){
      this.questions = questions;
   }

   public ArrayList<String> ques(){
      try {
      FileReader fr = new FileReader("Questionss.txt");
      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();

      while(line != null){
         questions.add(line);
         line = br.readLine();
      }
      }
      catch( IOException ioe){
      ioe.getMessage();
      }
      return (questions);
   }
}
   
    
