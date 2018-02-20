import java.sql.*;
import java.util.*;

public class PersonDaoJtbc implements PersonDao {
    private static ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    @Override
    public Person getByUsername(String userName ) {
        try (Connection conn = connectionUtil.getConnection()) {

            //prepare query
            String standardQuery = "SELECT * FROM persons WHERE userName = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setString(1, userName);


            //get results
            ResultSet results = ps.executeQuery();

            //process the results
            if (results.next()) {

                userName = results.getString("userName");
                String password = results.getString("password");
                int accountType = results.getInt("accounttype");
                int pin = results.getInt("pin");
                String nameFL = results.getString("nameFL");
                String ssn = results.getString("ssn");
                String dob = results.getString("dob");
                String address = results.getString("address");
                return new Person(userName, password, accountType, pin, nameFL, ssn, dob, address);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createPerson(Person person){
        try(Connection conn = connectionUtil.getConnection()){
            String query = "INSERT INTO persons (userName, password, pin, accountType, nameFL, ssn, dob, address) VALUES(?,?,?,?,?,?,?,?) RETURNING  userName";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, person.getUserName());
            ps.setString(2, person.getPassword());
            ps.setInt(3, person.getPin());
            ps.setInt(4, person.getAccountType());
            ps.setString(5, person.getNameFL());
            ps.setString(6, person.getSsn());
            ps.setString(7, person.getDob());
            ps.setString(8,person.getAddress());

            //set primary key
            ResultSet results =ps.executeQuery();
            if (results.next()){
                person.setUserName(results.getString("userName"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePerson(Person person){
        try(Connection conn = connectionUtil.getConnection()){
            String query = "UPDATE persons SET userName = ?, password = ?, pin = ?, accountType = ?, nameFL = ?, ssn = ?, dob = ?, address = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletePerson(Person person){
        try(Connection conn = connectionUtil.getConnection()){
            String query = "DELETE FROM persons WHERE userName = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, person.getUserName());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


