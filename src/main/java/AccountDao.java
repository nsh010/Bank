import java.sql.*;
import java.util.*;

public interface AccountDao {
    ArrayList<Account> getByUser1(String user1);
    ArrayList<Account> getByUser2(String user2);
    Account getByID(int accountidNum);
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
}
