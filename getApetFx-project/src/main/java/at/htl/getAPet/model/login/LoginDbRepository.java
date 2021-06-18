package at.htl.getAPet.model.login;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.DataAccessException;
import at.htl.getAPet.model.User.User;

import javax.sql.DataSource;
import java.sql.*;

public class LoginDbRepository implements LoginRepository {

    private final DataSource dataSource;

    public LoginDbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean login(String name, String password) {
        try (Connection connection = dataSource.getConnection()) {

            String sqlString = "SELECT id,name,email,password,phoneNr from get_a_pet.user WHERE name=? and password=md5(?)";

            PreparedStatement login = connection.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
            login.setString(1, name);
            login.setString(2, password);
            ResultSet results = login.executeQuery();


            if (results.next()) {

                System.out.println("logged in");
                App.setUser(new User(results.getInt("id"), results.getString("name"), results.getString("email"), results.getString("password"),results.getString("phoneNr")));

                return true;
            } else {

                System.out.println("not logged in");
                return false;
            }


        } catch (SQLException throwable) {
            throw new DataAccessException(throwable.getMessage(), throwable);
        }

    }


}
