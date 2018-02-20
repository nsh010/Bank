import java.sql.*;
import java.util.*;

public class AccountDaoJtbc implements AccountDao {
    private static ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    @Override
    public ArrayList<Account> getByUser1(String user1){
        try(Connection conn = connectionUtil.getConnection()){
            //prepare query

            String standardQuery = "SELECT * FROM accounts WHERE user1 = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setString(1,user1);

            ResultSet results = ps.executeQuery();
            ArrayList<Account> accounts = new ArrayList<>();
            while (results.next()){
                user1 = results.getString("user1");
                int accountidNum = results.getInt("accountidNum");
                int balance = results.getInt("balance");
                int accountType = results.getInt("accountType");
                int status = results.getInt("status");
                String user2 = results.getString("user2");

                accounts.add(new Account(accountidNum, balance, accountType, status, user1, user2));
            }
            return accounts;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Account> getByUser2(String user2){
        try(Connection conn = connectionUtil.getConnection()){
            //prepare query

            String standardQuery = "SELECT * FROM accounts WHERE user2 = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setString(1,user2);

            ResultSet results = ps.executeQuery();
            ArrayList<Account> accounts = new ArrayList<>();
            while (results.next()){
                user2 = results.getString("user2");
                int accountidNum = results.getInt("accountidNum");
                int balance = results.getInt("balance");
                int accountType = results.getInt("accountType");
                int status = results.getInt("status");
                String user1 = results.getString("user1");

                accounts.add(new Account(accountidNum, balance, accountType, status, user1, user2));
            }
            return accounts;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getByID(int accountidNum){
        try(Connection conn = connectionUtil.getConnection()){
            //prepare query

            String standardQuery = "SELECT * FROM accounts WHERE accountidNum = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setInt(1,accountidNum);

            ResultSet results = ps.executeQuery();
            while (results.next()){
                String user2 = results.getString("user2");
                accountidNum = results.getInt("accountidNum");
                int balance = results.getInt("balance");
                int accountType = results.getInt("accountType");
                int status = results.getInt("status");
                String user1 = results.getString("user1");

                return new Account(accountidNum, balance, accountType, status, user1, user2);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createAccount(Account account) {
        try(Connection conn = connectionUtil.getConnection()){
            String query = "INSERT INTO accounts (accountidNum, balance, accountType, status, user1, user2) VALUES(?,?,?,?,?,?) RETURNING user1";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, account.getAccountidNum());
            ps.setInt(2, account.getBalance());
            ps.setInt(3, account.getAccountType());
            ps.setInt(4, account.getStatus());
            ps.setString(5, account.getUser1());
            ps.setString(6,account.getUser2());

            //set primary key
            ResultSet results =ps.executeQuery();
            if (results.next()){
                account.setUser1(results.getString("user1"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateBalance(Account account) {
        try(Connection conn = connectionUtil.getConnection()){
            String query = "UPDATE accounts SET balance = ? WHERE accountidNum = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, account.getBalance());
            ps.setInt(2,account.getAccountidNum());

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccountType(Account account){
        try(Connection conn = connectionUtil.getConnection()){
            String query = "UPDATE accounts SET accounttype = ? WHERE accountidNum = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, account.getAccountType());
            ps.setInt(2, account.getAccountidNum());

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(Account account){
        try(Connection conn = connectionUtil.getConnection()){
            String query = "UPDATE accounts SET status = ? WHERE accountidNum = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, account.getStatus());
            ps.setInt(2, account.getAccountidNum());

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        try(Connection conn = connectionUtil.getConnection()){
            String query = "DELETE FROM accounts WHERE accountidNum = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, account.getAccountidNum());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





