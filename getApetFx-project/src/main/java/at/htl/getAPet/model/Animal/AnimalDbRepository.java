package at.htl.getAPet.model.Animal;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.DataAccessException;
import at.htl.getAPet.model.User.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class AnimalDbRepository implements AnimalRepository {
    private final DataSource dataSource;

    public AnimalDbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Animal> findAll(int startValue) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select id,name,age,gender,species,breed,height,weight,city,ownerId,imageURL from get_a_pet.Animal where ownerId != ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, App.getUser().getId());
            ResultSet results = statement.executeQuery();


            List<Animal> animals = new ArrayList<>();
            while (results.next()) {
                String sqlUser = "select id,name,email,password,phoneNr,lastAnimal from get_a_pet.User where id=?";
                PreparedStatement statementUser = connection.prepareStatement(sqlUser);
                statementUser.setInt(1, results.getInt("ownerId"));
                ResultSet userexe = statementUser.executeQuery();

//                System.out.println(userexe);
                User user = null;
                while (userexe.next()) {
                    user = new User(userexe.getInt("id"), userexe.getString("name"),
                            userexe.getString("email"), userexe.getString("password"),userexe.getString("phoneNr"), userexe.getInt("lastAnimal"));
                }
                animals.add(new Animal(results.getInt("id"),
                        results.getString("name"),
                        results.getInt("age"),
                        Gender.valueOf(results.getString("gender")),
                        results.getString("species"),
                        results.getString("breed"),
                        results.getDouble("height"),
                        results.getDouble("weight"),
                        results.getString("city"),
                        user, results.getString("imageURL")));
            }

            return animals.stream().filter(animal -> animal.getId() >= startValue-1).collect(Collectors.toList());


        } catch (SQLException throwable) {
            throw new DataAccessException(throwable.getMessage(), throwable);
        }

    }

    @Override
    public Optional<Animal> findById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select id,name,age,gender,species,breed,height,weight,city,ownerId,imageURL from get_a_pet.Animal where id=?";
            PreparedStatement statementAnimal = connection.prepareStatement(sql);
            statementAnimal.setInt(1, id);
            ResultSet animal = statementAnimal.executeQuery();


            if (animal.next()) {
                String sqlUser = "select id,name,email,password,phoneNr,lastAnimal from get_a_pet.User where id=?";
                PreparedStatement statementUser = connection.prepareStatement(sqlUser);
                statementUser.setInt(1, animal.getInt("ownerId"));
                ResultSet userexe = statementUser.executeQuery();

                User user = null;
                while (userexe.next()) {
                    user = new User(userexe.getInt("id"), userexe.getString("name"),
                            userexe.getString("email"), userexe.getString("password"),userexe.getString("phoneNr"), userexe.getInt("lastAnimal"));
                }
                return Optional.of(
                        new Animal(id, animal.getString("name"), animal.getInt("age"),
                                Gender.valueOf(animal.getString("gender")), animal.getString("species"),
                                animal.getString("breed"), animal.getDouble("height"), animal.getDouble("weight"),
                                animal.getString("city"),
                                user, animal.getString("imageURL"))
                );
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Animal> findByUser(User user) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "select id,name,age,gender,species,breed,height,weight,city,ownerId,imageURL from get_a_pet.Animal where ownerId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,user.getId());
            ResultSet results = statement.executeQuery();

            List<Animal> animals = new ArrayList<>();
            while(results.next()) {
                animals.add(new Animal(results.getInt("id"),
                        results.getString("name"),
                        results.getInt("age"),
                        Gender.valueOf(results.getString("gender")),
                        results.getString("species"),
                        results.getString("breed"),
                        results.getDouble("height"),
                        results.getDouble("weight"),
                        results.getString("city"),
                        user, results.getString("imageURL")));
            }

            return animals;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public Animal createAnimal(String name, int age, Gender gender, String species, String breed, double height, double weight
            , String city, User owner,String imageURL) {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into get_a_pet.Animal(name,age,gender,species,breed,height,weight,city,ownerId,imageURL) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender.name());
            statement.setString(4, species);
            statement.setString(5, breed);
            statement.setDouble(6, height);
            statement.setDouble(7, weight);
            statement.setString(8, city);
            statement.setInt(9, owner.getId());

            int update = statement.executeUpdate();
            if (update == 0) {
                throw new SQLException("Nothing inserted!");
            }

            ResultSet set = statement.getGeneratedKeys();
            if (set.next()) {
                return new Animal(set.getInt("id"),
                        name, age, gender, species, breed, height, weight, city, owner, imageURL);
            }

        } catch (SQLException throwable) {
            throw new DataAccessException(throwable.getMessage(), throwable);
        }
        return null;
    }

    @Override
    public void update(Animal animal) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "update get_a_pet.Animal set(name,age,gender,species,breed,height,weight,city,ownerId,imageURL) values(?,?,?,?,?,?,?,?,?,?) where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, animal.getName());
            statement.setInt(2, animal.getAge());
            statement.setString(3, String.valueOf(animal.getGender()));
            statement.setString(4, animal.getSpecies());
            statement.setString(5, animal.getBreed());
            statement.setDouble(6, animal.getHeight());
            statement.setDouble(7, animal.getWeight());
            statement.setString(8, animal.getCity());
            statement.setInt(9, animal.getOwner().getId());
            statement.setString(10,animal.getImgURL());
            statement.setInt(11, animal.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Not Updated!");
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void remove(Animal animal) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from get_a_pet.Animal where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, animal.getId());
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Not deleted!");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
