package at.htl.getAPet.model.SignUp;

import at.htl.getAPet.model.DataAccessException;
import at.htl.getAPet.model.User.User;

import javax.sql.DataSource;
import java.sql.*;

public class SignUpDbRepository implements SignUpRepository{

        private final DataSource dataSource;

    public SignUpDbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User createAccount(String name, String email, String password,String phoneNr) {

            try (Connection connection = dataSource.getConnection()) {

                String sql = "insert into get_a_pet.user (name,email,password,phoneNr) values (?,?,md5(?),?)";
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3,password);
                statement.setString(4,phoneNr);



                if (statement.executeUpdate() == 0) {
                    throw new SQLException("Failed to insert");
                }

                ResultSet keys = statement.getGeneratedKeys();

                if (keys.next()) {
                    return new User(keys.getInt("id"), name, email,password,phoneNr);
                } else {
                    throw new SQLException("Failed to insert");
                }


            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage(), e);
            }


    }
}


