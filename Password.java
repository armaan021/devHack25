public class Password{
    private String password;
    

    public Password(String password){
        this.password = password;
    }

    public String getPassword(){
        return (this.password);
    }

    
    

    public boolean checkLength() throws IllegalArgumentException{
        boolean valid = false;
        if(this.password.length() < 8){
            System.out.println("Invalid Password : Password should be greater than 8 characters");
            System.out.println("Please try again");

            valid = true;
        }
        return valid;
    }

    public boolean isEqual(Password password) throws IllegalArgumentException{
        boolean valid = true;
        if(this.password.equals(password.getPassword())){
            System.out.println("Password Matched");
            valid = false;
        }
        else{
            System.out.println("Password did not match");
            System.out.println("Please try again");
        }
        return valid;
    }

    /* public boolean checkUpperCase(String password) throws IllegalArgumentException{ // Check if password contains atleast one uppercase letter (but for future use)
        boolean valid = true;
        if(password.equals(password.toLowerCase())){
            System.out.println("Invalid Password : Password should contain atleast one uppercase letter");
            valid = false;
        }
        return valid;
    }

    public boolean checkLowerCase(String password) throws IllegalArgumentException{// Check if password contains atleast one lowercase letter (but for future use)
        boolean valid = true;
        if(password.equals(password.toUpperCase())){
            System.out.println("Invalid Password : Password should contain atleast one lowercase letter");
            valid = false;
        }
        return valid;
    }

    public boolean checkSpecialCharacter(String password) throws IllegalArgumentException{ // Check if password contains atleast one special character (but for future use)
        boolean valid = true;
        if(password.matches("[a-zA-Z0-9 ]*")){
            System.out.println("Invalid Password : Password should contain atleast one special character");
            valid = false;
        }
        return valid;
    }

    public boolean checkNumber(String password) throws IllegalArgumentException{ // check if password contains atleast one number (but for future use)
        boolean valid = true;
        if(!password.matches(".*\\d.*")){
            System.out.println("Invalid Password : Password should contain atleast one number");
            valid = false;
        }
        return valid;
    } */

    public String toString(){
        return("Password : " + this.password);
    }
}