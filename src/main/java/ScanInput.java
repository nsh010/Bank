import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScanInput {
    private Scanner scan;

    public ScanInput(Scanner scan) {
        this.scan = scan;
    }

    public int scannerUserInputInt(String input, int type){
        int n;
        for(;;) {
            // make sure its a number
            try {
                System.out.println(input);
                n = scan.nextInt();
                if(constraintInt(n,type)){
                    // stop loop because user entered a valid #
                    break;
                }
                else{
                    System.out.println("Criteria doesn't match");
                    continue;
                }
            } catch (InputMismatchException exception) {
                // not a valid number, discard input and continue
                System.out.println("Try again");
                scan.next();
            }
        }
        // return user input #
        return n;
    }

    public boolean constraintInt(int input, int x){
        switch (x){
            case 0:
                if(input>1000 && input<1000000000){
                    return true;
                }
                else return false;
            case 1:
                if(input == 1 || input == 2 || input == 3|| input ==4){
                    return true;
                }
                else return false;
            case 2:
                if(input == 1 || input == 2 || input == 3){
                    return true;
                }
                else return false;
            case 3:
                return true;
            default:
                return false;
        }
    }

    public String scannerUserInputString(String input, int type){
        String word;
        for(;;){
            try {
                System.out.println(input);
                word = scan.next();
                if(constraintString(word,type)){
                    break;
                }
                else{
                    System.out.println("Try Again");
                }
            }catch(NoSuchElementException e){
                System.out.println("Try again");
                scan.next();
            }
        }
        return word;
    }

    public boolean constraintString( String word, int x){
        switch (x){
            case 0:
                if(word != null){
                    return true;
                }else {
                    return false;
                }
            case 1:
                //check text to see if it 7 characters long
                if(word.length() >= 7){
                    return true;
                }
                System.out.println("Username doesn't meet the criteria");
                return false;
            case 2:
                //System.out.println("im in 2");
                if(constraintString(word,3)){
                    String check;
                    System.out.println("Enter password again: ");
                    check = scan.next();
                    if(constraintString(check,3)){
                        if(word.equals(check)){
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
                else return false;
            case 3:
                if (word.length() < 8) {
                    System.out.println("Password does not meet criteria");
                    return false;
                }
                else{
                    return true;
                }

            case 4:
                if(word.matches("\\d{3}-\\d{2}-\\d{4}")){
                    return true;
                }
                else {
                    return false;
                }
            case 5:
                if(word.matches("\\d{2}/\\d{2}/\\d{4}") && dateCheck(word)){
                    return true;
                }
                else {
                    return false;
                }

            default:
                return false;
        }
    }
    private static boolean dateCheck(String date){
        SimpleDateFormat checker = new SimpleDateFormat("MM/dd/yyyy");
        checker.setLenient(false);
        try{
            Date parse = checker.parse(date);

        } catch (ParseException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

}