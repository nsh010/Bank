
import java.util.Scanner;

public class Main {
    //need an array of employee initalized here
    //new user = admin


    public static void main(String[] args) {
        /*
        User noop = new User();
        noop.setUserName(noop.inputName(noop));
        noop.setPassword(noop.inputPassword(noop,2));
        System.out.println(noop.toString());
        */

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
                    newAccount();
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

    public static void newAccount(){
        User newUser = new User();
        Person newPerson = new Person();
        newUser.setUserName(newUser.inputName());
        newUser.setPassword(newUser.inputPassword(2));
        newPerson.setUser(newUser);
        newPerson.setPin(newPerson.inputPin());
        newPerson.setName(newPerson.inputName());
        newPerson.setSsn(newPerson.inputSSN());
        newPerson.setDob(newPerson.inputDOB());
        newPerson.setAddress(newPerson.inputAddress());
        //Send it to the pending account table

        System.out.println("Pending Company Approval, takes 24 hours, please login in tomorrow, Thank you for your time\nReturning to Main Menu");
        //System.out.println(newPerson.toString());
    }

    public static void customerLogin(){
        User customer = new User();
        for(;;){
            customer.setUserName(customer.inputName());
            customer.setPassword(customer.inputPassword(1));
            //check if customer is an active user
            if(true){
                customerMainMenu(customer);
                break;
            }
            else {
                System.out.println("Try again, account is not in the system or information entered incorrectly");
                continue;
            }
        }

    }

    public static void employeeLogin(){
        User employee = new User();
        for(;;){
            employee.setUserName(employee.inputName());
            employee.setPassword(employee.inputPassword(1));
            //check if employee is an active user
            if(true){
                break;
            }
            else {
                System.out.println("Try again, account is not in the system or information entered incorrectly");
                continue;
            }
        }

    }

    public static void customerMainMenu(User newPerson){
        int menu;
        Person currentPerson = new Person();
        currentPerson.setUser(newPerson);
        String name = newPerson.getUserName();
        do{
            System.out.println("Welcome Back " + name +
                    "\nPlease indicate what you need by entering 1, 2, 3, or 4:\n" +
                    "\t1. Create new Account\n\t2. View Accounts\n\t3. View Personal information\n\t 4. Exit\n");
            menu = readMenu();
            switch (menu) {
                case 1:
                    System.out.println("Create new Account");
                    break;
                case 2:
                    System.out.println("View Accounts");
                    break;
                case 3:
                    System.out.println("View Personal Information\n\tNote you will not be able to change information, must contact Admin");
                    System.out.println(currentPerson.toString());
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

    public static void newAccount(Person x){
        String inputLine = "Please enter Account Type:\nEnter 1, 2, or 3\n\t1.Checking\n\t2.Savings\n\t3.Joint\n";
        Scanner userInput = new Scanner(System.in);
        ScanInput uPin = new ScanInput(userInput);
        int output;
        output = uPin.scannerUserInputInt(inputLine,0);
    }
}
