package at.htl.getAPet.model.User;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.DataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDbRepository implements UserRepository {
    DataSource dataSource;

    public UserDbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select id,name, email, Password,phoneNr,likedAnimals,lastAnimal from get_a_pet.User;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (results.next()) {
                users.add(new User(results.getInt("id"), results.getString("name"), results.getString("email"),
                        results.getString("Password"), results.getString("phoneNr"), results.getInt("lastAnimal")));
            }
            return users;
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getMessage(), throwables);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select id,name, email, Password,phoneNr,lastAnimal from get_a_pet.User where id=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Optional.of((new User(result.getInt("id"), result.getString("name"), result.getString("email"),
                        result.getString("Password"), result.getString("phoneNr"), result.getInt("lastAnimal"))));
            }
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getMessage(), throwables);
        }
        return Optional.empty();
    }


    @Override
    public void updateUser(User user) {

        try (Connection connection = dataSource.getConnection()) {

            String sql = "";

            if (findById(user.getId()).get().getPassword().equals(App.getUser().getPassword())) {
                sql = "update get_a_pet.User set name=?,email=?,Password=?,phoneNr=?,lastAnimal=? where id=?";
            } else {
                sql = "update get_a_pet.User set name=?,email=?,Password=md5(?),phoneNr=?,lastAnimal=? where id=?";
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNr());
            statement.setInt(5,App.getLastAnimal());
            statement.setInt(6, user.getId());

            int result = statement.executeUpdate();
            if (result == 0) {
                throw new SQLException("No Update");
            }
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getMessage(), throwables);
        }
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into get_a_pet.User(name,email,Password,phoneNr,lastAnimal) values(name=?,email=?, Password=?,phoneNr=?,lastAnimal=?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNr());
            statement.setInt(5,user.getLastAnimal());
            int result = statement.executeUpdate();

            if (result == 0) {
                throw new SQLException("No Insertion");
            }

        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getMessage(), throwables);
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from get_a_pet.User where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            int result = statement.executeUpdate();
            if (result == 0) {
                throw new SQLException("No Deletion");
            }
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getMessage(), throwables);
        }
    }

    @Override
    public int findLastAnimal(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select lastAnimal from get_a_pet.User where id=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return  result.getInt("lastAnimal");
            }
        } catch (SQLException throwables) {
            throw new DataAccessException(throwables.getMessage(), throwables);
        }
        return 1;
    }


}
