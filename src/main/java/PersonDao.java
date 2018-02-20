import java.sql.*;
import java.util.*;

public interface PersonDao{
    Person getByUsername(String userName);
    void createPerson (Person person);
    void updatePerson(Person person);
    void deletePerson(Person person);
}



