import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class User implements Serializable {
    private String userName;
    private String password;

    public User() {
        this.userName = null;
        this.password = null;
    }


    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String inputName(){
        String inputLine = "Please enter a Username:\n\tNote username must be 7 characters long";
        Scanner userInput = new Scanner(System.in);
        ScanInput uName = new ScanInput(userInput);
        String outputLine = uName.scannerUserInputString(inputLine,1);
        return outputLine;
    }

    public String inputPassword(int type){
        String inputLine = "Please enter Password:\n\tNote Password must be 8 characters long";
        Scanner userInput = new Scanner(System.in);
        ScanInput uName = new ScanInput(userInput);
        String outputLine;
        //Get Password
        if(type == 1){
            outputLine = uName.scannerUserInputString(inputLine,3);
        }else {
            //Create Password
            outputLine = uName.scannerUserInputString(inputLine,2);
        }

        return outputLine;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static String createSHA(String password, String salt){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            password = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return password;
    }
}