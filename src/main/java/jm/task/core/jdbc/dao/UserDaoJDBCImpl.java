package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = new Util().getConnection();

    public UserDaoJDBCImpl() {

            }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (Id INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(40) NOT NULL, Lastname VARCHAR(60) NOT NULL, Age INT NOT NULL);";
        try(Statement statement =connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, lastname, age) VALUES (?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,name);
            ps.setString(2,lastName);
            ps.setByte(3,age);
            ps.executeUpdate();

            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try(PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";


        try(Statement statement = connection.createStatement() ) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                users.add(user);

                System.out.println(user.toString());
                            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    public void cleanUsersTable()  {

        String sql = "DELETE FROM users";

        try(PreparedStatement ps =connection.prepareStatement(sql)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
