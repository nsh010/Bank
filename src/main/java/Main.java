
import java.util.*;

public class Main {
    //need an array of employee initalized here
    //new user = admin


    public static void main(String[] args) {
    openBank();
    }

    public static void openBank(){
        int menu;
        do {
            // Setup for Bank usage
            System.out.println("Welcome to Bank of Blockchain!\n" +
                    "Please indicate who you are by entering 1, 2, 3, or 4:\n" +
                    "\t1. Customer\n" +
                    "\t2. Employee\n" +
                    "\t3. Create new Account\n" +
                    "\t4. Exit Program\n");
            // Get Account type
            menu = readMenu();
            switch (menu) {
                case 1:
                    System.out.println("Welcome back Loyal Customer");
                    customerLogin();
                    break;
                case 2:
                    System.out.println("Welcome back to Work");
                    employeeLogin();
                    break;
                case 3:
                    System.out.println("Let's create an account");
                    newAccountPerson();
                    break;
                case 4:
                    System.out.println("Thank you for using Bank of Blockchain, Good Bye");
                    break;
                default:
                    System.out.println("Sorry for the inconvenience, System is down try running program");
                    break;
            }
        }while(menu != 4);
    }

    public static int readMenu() {
        String inputLine = "Enter 1, 2, 3, or 4";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        output = uPin.scannerUserInputInt(inputLine, 1);
        return output;
    }

    public static int readMenutwo() {
        String inputLine = "Enter 1, 2, 3, 4, 5, 6";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        output = uPin.scannerUserInputInt(inputLine, 5);
        return output;
    }

    public static void newAccountPerson(){
        Person newPerson = new Person();
        PersonDaoJtbc p = new PersonDaoJtbc();
        newPerson.setUserName(newPerson.inputUserName());
        newPerson.setPassword(newPerson.inputPassword(2));
        newPerson.setPin(newPerson.inputPin());
        newPerson.setNameFL(newPerson.inputName());
        newPerson.setAccountType(1);
        newPerson.setSsn(newPerson.inputSSN());
        newPerson.setDob(newPerson.inputDOB());//Send it to the pending account table
        newPerson.setAddress(newPerson.inputAddress());
        p.createPerson(newPerson);System.out.println("Pending Company Approval, takes 24 hours, please login in tomorrow, Thank you for your time\nReturning to Main Menu");
        //System.out.println(newPerson.toString());
    }

    public static void customerLogin(){
        Person newPerson = new Person();
        PersonDaoJtbc p = new PersonDaoJtbc();
        for(;;){
            newPerson.setUserName(newPerson.inputUserName());
            String password = newPerson.inputPassword(1);
            newPerson = p.getByUsername(newPerson.getUserName());
            //check if customer is an active user
            if(password.equals(newPerson.getPassword())){
                int check = newPerson.getAccountType();
                if (check == 1) {
                    customerMainMenu(newPerson);
                    break;
                }
            }
            else {
                System.out.println("Try again, account is not in the system or information entered incorrectly");
                continue;
            }
        }
    }

    public static void employeeLogin(){
        Person employee = new Person();
        PersonDaoJtbc p = new PersonDaoJtbc();
        for(;;){
            employee.setUserName(employee.inputUserName());
            String password = employee.inputPassword(1);
            employee = p.getByUsername(employee.getUserName());
            //check if customer is an active user
            if(password.equals(employee.getPassword())){
                int check = employee.getAccountType();
                if(check == 3){
                    adminMainMenu(employee);
                    //admin
                    break;
                }else if(check ==2){
                    employeeMainMenu(employee);
                    //Normal Account
                    break;
                } else{
                    continue;
                }
            }
            else {
                System.out.println("Try again, account is not in the system or information entered incorrectly");
                continue;
            }
        }
    }

    public static void employeeMainMenu(Person employee){
        int menu;
        String name  = employee.getUserName();
        do{
            System.out.println("Welcome Back " + name +
                    "\nPlease indicate what you need by entering 1, 2, 3, or 4:\n" +
                    "\t1. Check Personal Info\n\t2. View Accounts\n\t3. Approve/Deny open applications for accounts\n\t4. Exit\n");
            menu = readMenu();
            switch (menu) {
                case 1:
                    System.out.println("Check Personal Info of User");
                    viewPersonal();
                    break;
                case 2:
                    System.out.println("View User Accounts");
                    employeeViewAccount();
                    break;
                case 3:
                    System.out.println("Approve/Deny Accounts");
                    employeeApproveDenyPerson(employee);
                    break;
                case 4:
                    System.out.println("Logging out and entering Main Menu");
                    break;
                default:
                    System.out.println("Sorry for the inconvenience, System is down try running program");
                    break;
            }
        }while (menu !=4);
    }

    public static void viewPersonal(){
        Person viewCurrent = findPerson();
        System.out.println(viewCurrent.toString());
    }

    public static Person findPerson(){
        Person x = new Person();
        PersonDaoJtbc check = new PersonDaoJtbc();
        for(;;) {
            x = check.getByUsername(x.inputUserName());
            if(!x.getUserName().equals(null)){
                break;
            }
            else {
                System.out.println("User Not Found Try again.");
            }
        }
        return x;
    }

    public static void employeeViewAccount(){
        Person viewCurrent = findPerson();
        ArrayList<Account> a1, a2;
        AccountDaoJtbc acc = new AccountDaoJtbc();
        String username = viewCurrent.getUserName();
        a1 = acc.getByUser1(username);
        a2 = acc.getByUser2(username);
        for (Account b : a1){
            System.out.println(b.toString());
        }
        for (Account b : a2){
            System.out.println(b.toString());
        }
    }

    public static void employeeApproveDenyPerson(Person employee){
        Person viewCurrent = findPerson();
        ArrayList<Account> a1, a2;
        AccountDaoJtbc acc = new AccountDaoJtbc();
        String username = viewCurrent.getUserName();
        a1 = acc.getByUser1(username);
        a2 = acc.getByUser2(username);
        for (Account b : a1){
            if(b.getStatus() == 1){
                System.out.println(b.toString());
            }
        }
        for (Account b : a2){
            if(b.getStatus() == 1){
                System.out.println(b.toString());
            }
        }
        Account currentAccount;
        String inputLine = "Please enter the Account Number you want to Approve or Deny";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        int check;
        int temp =1;
        for(;;) {
            output = uPin.scannerUserInputInt(inputLine, 3);
            currentAccount = acc.getByID(output);
            if(!currentAccount.getUser1().equals(null)){
                inputLine = "Enter 1 for Approve or 0 for Deny";
                check = uPin.scannerUserInputInt(inputLine,4);
                switch (check){
                    case 0:
                        System.out.println("Account Denied, Account Deleted from system");
                        temp =0;
                        //admin only
                        //acc.deleteAccount(currentAccount);
                        break;
                    case 1:
                        System.out.println("Approve, Entered into System");
                        currentAccount.switchStatus(employee);
                        if(currentAccount.getStatus() != 0){
                            currentAccount.switchStatus(employee);
                        }
                        acc.updateStatus(currentAccount);
                        temp =0;
                        break;
                    default:
                        System.out.println("Error try again");
                        break;
                }
                if(temp == 0) {
                    break;
                }
            }else {
                System.out.println("Try again Account does not match our Systems");
            }
        }
    }


    public static void adminMainMenu(Person employee){
        int menu;
        String name  = employee.getUserName();
        do{
            System.out.println("Welcome Back " + name +
                    "\nPlease indicate what you need by entering 1, 2, 3, 4, 5, or 6:\n" +
                    "\t1. Check Personal Info\n\t2. View Accounts\n\t3. Approve/Deny open applications for accounts\n\t4. Move Money\n\t5. Cancel Accounts\n\t6. Exit\n");
            menu = readMenutwo();
            switch (menu) {
                case 1:
                    System.out.println("Check Personal Info of User");
                    viewPersonal();
                    break;
                case 2:
                    System.out.println("View User Accounts");
                    employeeViewAccount();
                    break;
                case 3:
                    System.out.println("Approve/Deny Accounts");
                    employeeApproveDenyPerson(employee);
                    break;
                case 4:

                    break;
                case 5:
                    adminApproveDenyPerson(employee);
                    break;
                case 6:
                    System.out.println("Logging out and entering Main Menu");
                    break;
                default:
                    System.out.println("Sorry for the inconvenience, System is down try running program");
                    break;
            }
        }while (menu !=6);
    }

    public static void customerMainMenu(Person newPerson){
        int menu;
        String name = newPerson.getUserName();
        do{
            System.out.println("Welcome Back " + name +
                    "\nPlease indicate what you need by entering 1, 2, 3, or 4:\n" +
                    "\t1. Create new Account\n\t2. View Accounts\n\t3. View Personal information\n\t4. Exit\n");
            menu = readMenu();
            switch (menu) {
                case 1:
                    System.out.println("Create new Account");
                    newAccount(newPerson);
                    break;
                case 2:
                    System.out.println("View Accounts");
                    viewAccounts(newPerson);
                    break;
                case 3:
                    System.out.println("View Personal Information\n\tNote you will not be able to change information, must contact Admin");
                    System.out.println(newPerson.toString());
                    break;
                case 4:
                    System.out.println("Logging out and entering Main Menu");
                    break;
                default:
                    System.out.println("Sorry for the inconvenience, System is down try running program");
                    break;
            }
        }while(menu != 4);
    }

    public static void viewAccounts(Person x){
        //figure out the accounts person x can access then print them all
        //Then run a for loop of the accounts
        // if null return message of no accounts available
        ArrayList<Account> a1, a2;
        AccountDaoJtbc acc = new AccountDaoJtbc();
        Account currentAccount = new Account();
        String username = x.getUserName();
        a1 = acc.getByUser1(username);
        a2 = acc.getByUser2(username);
        for (Account b : a1){
            if(b.getStatus() == 0){
                System.out.println(b.toString());
            }
        }
        for (Account b : a2){
            if(b.getStatus() == 0){
                System.out.println(b.toString());
            }
        }

        String inputLine = "Please enter the Account Number you want to enter into";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int tigger =1;
        int output;
        for(;;){
            output = uPin.scannerUserInputInt(inputLine,3);
            // create for loop of accounts to make sure that match
            for (Account b : a1){
                if(b.getAccountidNum() == output){
                    currentAccount = b;
                    tigger = 0;
                    break;
                }
            }
            for (Account b : a2){
                if(b.getAccountidNum() == output){
                    currentAccount = b;
                    tigger = 0;
                    break;
                }
            }
            if(tigger == 0){
                break;
            }
            System.out.println("Input doesn't match your Account Number Try Again");
        }
        moveMoney(x,currentAccount);

        // get complete account information
    }

    public static void moveMoney(Person y, Account x){
        AccountDaoJtbc acc = new AccountDaoJtbc();
        String inputLine = "Please enter action you would like to complete:\nEnter 1, 2, 3, or 4\n\t1. Deposit\n\t2. Withdraw\n\t3. Transfer\n\t4. Exit\n";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        int transfer;
        int money;
        for(;;) {
            output = uPin.scannerUserInputInt(inputLine, 1);
            if(output == 4){
                break;
            }
            money = uPin.scannerUserInputInt("Enter Amount: ",3);
            switch (output){
                case 1:
                    x.deposit(y,x,money);
                    acc.updateBalance(x);
                    break;
                case 2:
                    x.withdraw(y,x,money);
                    acc.updateBalance(x);
                    break;
                case 3:
                    Account transferAccount;
                    for(;;) {
                        transfer = uPin.scannerUserInputInt("Please enter Account number to Transfer to: ", 3);
                        transferAccount = acc.getByID(transfer);
                        //Check if the entered id is a valid one
                        if(transferAccount == null) {
                            System.out.println("Account not found");
                            continue;
                        }else if(x.getUser1().equals(y.getUserName()) || x.getUser2().equals(y.getUserName())){
                            break;
                        }
                    }
                    // get account
                    x.transfer(y,x,transferAccount,money);
                    acc.updateBalance(x);
                    acc.updateBalance(transferAccount);
                    break;
                default:
                    System.out.println("Error try again");
                    break;
            }
        }
    }


    public static void newAccount(Person x){
        AccountDaoJtbc acc = new AccountDaoJtbc();
        PersonDaoJtbc pre = new PersonDaoJtbc();
        Person temp;
        String inputLine = "Please enter Account Type:\nEnter 1, 2, or 3\n\t1.Checking\n\t2.Savings\n\t3.Joint\n";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        output = uPin.scannerUserInputInt(inputLine,2);
        //create new Account
        Account newAccout = new Account();
        //setup new account
        newAccout.setUser1(x.getUserName());
        newAccout.setAccountType(output);
        Random rand = new Random();
        int num = rand.nextInt(500) + 1;
        newAccout.setAccountidNum(num);
        // for joint classes
        if(output == 3)
        {
            String nameFL;
            int secondPin;
            Person secondPerson = new Person();
            System.out.println("In order to complete the creation of the Joint account please enter the other User's username and pin");
            for(;;) {
                nameFL = secondPerson.inputUserName();
                temp = pre.getByUsername(nameFL);
                secondPin = secondPerson.inputPin();
                //check if the account exist
                if(temp.getPin() == secondPin ){
                    break;
                }
                else{
                    System.out.println("Joint account not created please try again");
                }
            }
            //Add to account
            newAccout.setUser2(nameFL);
        }
        acc.createAccount(newAccout);
        System.out.println("New Bank Account Created pending Approval");
    }

    public static void adminApproveDenyPerson(Person employee){
        Person viewCurrent = findPerson();
        ArrayList<Account> a1, a2;
        AccountDaoJtbc acc = new AccountDaoJtbc();
        String username = viewCurrent.getUserName();
        a1 = acc.getByUser1(username);
        a2 = acc.getByUser2(username);
        for (Account b : a1){
            if(b.getStatus() == 1){
                System.out.println(b.toString());
            }
        }
        for (Account b : a2){
            if(b.getStatus() == 1){
                System.out.println(b.toString());
            }
        }
        Account currentAccount;
        String inputLine = "Please enter the Account Number you want to delete";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        int check;
        int temp = 1;
        for(;;) {
            output = uPin.scannerUserInputInt(inputLine, 3);
            currentAccount = acc.getByID(output);
            if(!currentAccount.getUser1().equals(null)){
                inputLine = "Enter 1 for not Deleting or 0 for Deleting";
                check = uPin.scannerUserInputInt(inputLine,4);
                switch (check){
                    case 0:
                        System.out.println("Cancelled");
                        //admin only
                        acc.deleteAccount(currentAccount);
                        temp = 0;
                        break;
                    case 1:
                        System.out.println("Not Cancelled");
                        temp =0;
                        break;
                    default:
                        System.out.println("Error try again");
                        break;
                }
                if(temp == 0 ) {
                    break;
                }
            }else {
                System.out.println("Try again Account does not match our Systems");
            }
        }
    }
    public static void adminMoveMoney(){
        Person y = findPerson();
        Account x = new Account();
        String inputLineAcc = "Please Enter the account number you want to ";


        AccountDaoJtbc acc = new AccountDaoJtbc();
        String inputLine = "Please enter action you would like to complete:\nEnter 1, 2, 3, or 4\n\t1. Deposit\n\t2. Withdraw\n\t3. Transfer\n\t4. Exit\n";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        int transfer;
        int money;
        for(;;) {
            output = uPin.scannerUserInputInt(inputLine, 1);
            if(output == 4){
                break;
            }
            money = uPin.scannerUserInputInt("Enter Amount: ",3);
            switch (output){
                case 1:
                    x.deposit(y,x,money);
                    acc.updateBalance(x);
                    break;
                case 2:
                    x.withdraw(y,x,money);
                    acc.updateBalance(x);
                    break;
                case 3:
                    Account transferAccount;
                    for(;;) {
                        transfer = uPin.scannerUserInputInt("Please enter Account number to Transfer to: ", 3);
                        transferAccount = acc.getByID(transfer);
                        //Check if the entered id is a valid one
                        if(transferAccount == null) {
                            System.out.println("Account not found");
                            continue;
                        }else if(x.getUser1().equals(y.getUserName()) || x.getUser2().equals(y.getUserName())){
                            break;
                        }
                    }
                    // get account
                    x.transfer(y,x,transferAccount,money);
                    acc.updateBalance(x);
                    acc.updateBalance(transferAccount);
                    break;
                default:
                    System.out.println("Error try again");
                    break;
            }
        }
    }

}
