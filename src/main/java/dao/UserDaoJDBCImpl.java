package dao;

import model.User;
import util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        final String CREATE_TABLE_users="CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastname VARCHAR(50), " +
                " age TINYINT, " +
                " PRIMARY KEY (id))";
        try(Connection connection = Util.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(CREATE_TABLE_users)) {
            prepStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException sqlE) {
            dropUsersTable();
            createUsersTable();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement("DROP TABLE IF EXISTS users")) {
            prepStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException sqlE) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String INSERT_USER="INSERT INTO users (name,lastname,age) VALUES (?,?,?)";
        try(Connection connection = Util.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(INSERT_USER)) {
            prepStatement.setString(1,name);
            prepStatement.setString(2,lastName);
            prepStatement.setString(3,Byte.toString(age));
            prepStatement.executeUpdate();
            connection.commit();
            System.out.printf("User с именем – %s %s добавлен в базу данных\n",name,lastName);
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM users WHERE users.id=id")) {
            prepStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersArrayList= new ArrayList<>();

        try(Connection connection = Util.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM users")) {

            ResultSet resultSet= prepStatement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String name=resultSet.getString("name");
                String lastname=resultSet.getString("lastname");
                Byte age=resultSet.getByte("age");
                usersArrayList.add(new User(name,lastname,age));
            }
            connection.commit();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return usersArrayList;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM users")) {
            prepStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
}
