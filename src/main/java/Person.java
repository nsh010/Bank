import
        java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class Person implements Serializable {
    private User user;
    private String[] accounts;
    private int pin;
    private String name;
    private String ssn;
    private String dob;
    private String address;

    public Person(){
        this.user = new User();
        this.accounts = null;
        this.pin = 0;
        this.name = null;
        this.ssn = null;
        this.dob = null;
        this.address = null;
    }

    public Person(User x){
        this.user = x;
        this.accounts = null;
        this.pin = 0;
        this.name = null;
        this.ssn = null;
        this.dob = null;
        this.address = null;
    }

    public Person(User user, String[] accounts, int pin, String name, String ssn, String dob, String address) {
        this.user = user;
        this.accounts = accounts;
        this.pin = pin;
        this.name = name;
        this.ssn = ssn;
        this.dob = dob;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getAccounts() {
        return accounts;
    }

    public void setAccounts(String[] accounts) {
        this.accounts = accounts;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
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
        return "Person{" +
                "user=" + user.toString() +
                ", accounts=" + Arrays.toString(accounts) +
                ", pin=" + pin +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                '}';
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


}
