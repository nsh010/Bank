import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Person implements Serializable {
    private String userName;
    private String password;
    private int pin;
    private int accountType; // pending =0, active = 1, employee = 2, admin = 3
    private String nameFL;
    private String ssn;
    private String dob;
    private String address;

    public Person(){
        this.userName = null;
        this.password = null;
        this.pin = 0;
        this.accountType = 1;
        this.nameFL = null;
        this.ssn = null;
        this.dob = null;
        this.address = null;
    }


    public Person(String userName, String password, int accountType, int pin, String nameFL, String ssn, String dob, String address) {
        this.userName = userName;
        this.password = password;
        this.pin = pin;
        this.accountType = accountType;
        this.nameFL = nameFL;
        this.ssn = ssn;
        this.dob = dob;
        this.address = address;
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

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getNameFL() {
        return nameFL;
    }

    public void setNameFL(String name) {
        this.nameFL = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        ssn = createSHA(ssn, "sat");
        this.ssn = ssn;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String temp = "userName = " + userName +
                "\nnameFL = " + nameFL +
                "\nssn = " + ssn +
                "\ndob = " + dob +
                "\naddress = " + address;
        return temp;
    }

    public int inputPin(){
        String inputLine = "Enter a number for a pin\n\tCan be long as 10 digits or short as 4 digits";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        output = uPin.scannerUserInputInt(inputLine,0);
        return output;
    }

    public String inputName(){
        String inputLine = "Please enter Name: ";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        String output;
        output = uPin.scannerUserInputString(inputLine,0);
        return output;
    }

    public String inputSSN(){
        String inputLine = "Please enter Social Security Number: \nFormat: XXX-XX-XXXX";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        String output;
        output = uPin.scannerUserInputString(inputLine,4);
        return output;
    }

    public String inputDOB(){
        String inputLine = "Please enter Date of Birth: \nFormat: MM/DD/YYYY";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        String output;
        output = uPin.scannerUserInputString(inputLine,5);
        return output;
    }

    public String inputAddress(){
        String inputLine = "Please enter address: ";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        String output;
        output = uPin.scannerUserInputString(inputLine,0);
        return output;
    }

    public String inputUserName(){
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

    public static String createSHA(String password, String salt){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< 10; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            password = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return password;
    }

}