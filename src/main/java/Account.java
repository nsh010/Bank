import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountidNum;
    private int balance;
    private int accountType; // 0 checking, 1 equals savings, 2 equals joint
    private int status; // 0-open 1-closed
    private String User1;
    private String User2;

    public Account(int accountidNum, int balance, int accountType, int status, String user1, String user2) {
        this.accountidNum = accountidNum;
        this.balance = balance;
        this.accountType = accountType;
        this.status = status;
        this.User1 = user1;
        this.User2 = user2;
    }

    public Account(){
        this.accountidNum = 0;
        this.balance = 0;
        this.accountType = 0;
        this.status = 1;
        this.User1 = null;
        this.User2 = null;
    }

    public int getAccountidNum() {
        return accountidNum;
    }

    public void setAccountidNum(int accountidNum) {
        this.accountidNum = accountidNum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser1() {
        return User1;
    }

    public void setUser1(String user1) {
        User1 = user1;
    }

    public String getUser2() {
        return User2;
    }

    public void setUser2(String user2) {
        User2 = user2;
    }

    public static boolean checkAccess(Person x, Account y){
        if((x.getUserName().equals(y.getUser1()) || x.getUserName().equals(y.getUser2())))
        {
            return true;
        }
        else{
            System.out.println("Do not have access privileges to the Account");
            return false;
        }
    }

    public void withdraw(Person x, Account y, int request) {
        if (checkAccess(x, y)) {
            if (request > this.balance) { // overdrawing check
                System.out.println("Overdrawing account try again");
            }
            if (request < 0) {
                System.out.println("please enter Positive Numbers only");
            } else {
                this.balance = this.balance - request;
            }
        }
    }

    public void deposit(Person x, Account y, int request){
        if (checkAccess(x, y)) {
            if (request < 0) {
                System.out.println("please enter Positive Numbers only");
            } else {
                this.balance += request;
            }
        }
    }

    public void transfer(Person x, Account y, Account getMoney, int money){
        if(money < 0){
            System.out.println("please enter Positive Numbers only");
        }else if(money > this.balance){
            System.out.println("Can't transfer money not enough funds");
        }else if(money == 0){
            System.out.println("No Transfer needed");
        }else if(checkAccess(x, y)){ //check to use if user can move money into account
            this.balance -= money;
            getMoney.balance += money;
        }
    }

    public void switchStatus(Person admin){
        if(admin.getAccountType() == 2 || admin.getAccountType() == 3){
            if(this.accountType == 1)
            {
                this.accountType = 0;
            }
            if (this.accountType == 0){

                this.accountType = 1;
            }
        }
        else {
            System.out.println("No access to change account");
        }
    }

    @Override
    public String toString() {
        return "accountidNum = " + accountidNum +
                "\nbalance = " + balance +
                "\naccountType = " + accountType +
                "\nstatus = " + status +
                "\nUser1 = " + User1 +
                "\nUser2 = " + User2 + "\n";
    }
}