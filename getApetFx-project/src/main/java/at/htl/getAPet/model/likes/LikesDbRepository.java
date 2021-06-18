package at.htl.getAPet.model.likes;

import at.htl.getAPet.model.Animal.Animal;
import at.htl.getAPet.model.DataAccessException;
import at.htl.getAPet.model.User.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LikesDbRepository implements LikesRepository {

    private final DataSource dataSource;

    public LikesDbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void insertInformation(User user, Animal animal) {
        try (Connection connection = dataSource.getConnection()) {

            String sql = "insert into likes (id_user,id_animal) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, animal.getId());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Failed to insert");
            }


        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Integer> getLikesPerUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select id_user,id_animal from likes where id_user=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());

            ResultSet res = statement.executeQuery();

            List<Integer> likedAnimals = new ArrayList<>();

            while (res.next()) {
                likedAnimals.add(res.getInt("id_animal"));
            }
            return likedAnimals.stream().distinct().collect(Collectors.toList());


        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
