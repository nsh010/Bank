import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountidnum;
    private double balance;
    private int accountType; // 0 checking, 1 equals savings, 2 equals joint
    private ArrayList<String> accoutuserwithaccess;
    private int status; // 0-open 1-closed

    public Account(int accountidnum, double balance, int accountType, ArrayList<String> accoutuserwithaccess, int status) {
        this.accountidnum = accountidnum;
        this.balance = balance;
        this.accountType = accountType;
        this.accoutuserwithaccess = accoutuserwithaccess;
        this.status = status;
    }

    public Account(){
        this.accountidnum = 0;
        this.balance = 0;
        this.accountType = 0;
        this.accoutuserwithaccess = new ArrayList<>();
        this.status = status;
    }

    public int getAccountidnum() {
        return accountidnum;
    }

    public void setAccountidnum(int accountidnum) {
        this.accountidnum = accountidnum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccoutType() {
        return accountType;
    }

    public void setAccoutType(int accoutType) {
        this.accountType = accoutType;
    }

    public List<String> getAccoutuserwithaccess() {
        return accoutuserwithaccess;
    }

    public void setAccoutuserwithaccess(ArrayList<String> accoutuserwithaccess) {
        this.accoutuserwithaccess = accoutuserwithaccess;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static boolean checkAccess(Person x, int accountNum){
        if(x.getAccounts().contains(accountNum)){
            // query the database for the information
            return true;
        }
        else{
            System.out.println("Do not have access privileges to the Account");
            return false;
        }
    }

    public void withdraw(Person x, int accountNum, double request) {
        if (checkAccess(x, accountNum)) {
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

    public void deposit(Person x, int accountNum, double request){
        if (checkAccess(x, accountNum)) {
            if (request < 0) {
                System.out.println("please enter Positive Numbers only");
            } else {
                this.balance += request;
            }
        }
    }

    public void transfer(Person x, int accountNum, Account getMoney, double money){
        if(money < 0){
            System.out.println("please enter Positive Numbers only");
        }else if(money > this.balance){
            System.out.println("Can't transfer money not enough funds");
        }else if(money == 0){
            System.out.println("No Transfer needed");
        }else if(checkAccess(x, accountNum)){ //check to use if user can move money into account
            this.balance -= money;
            getMoney.balance += money;
        }
    }

    public void switchStatus(Person admin){
        if(admin.getAccountType() == 2 ||admin.getAccountType() == 3){
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

}
