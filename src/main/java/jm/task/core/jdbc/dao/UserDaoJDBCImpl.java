package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private static final Connection conn = Util.getInstance().getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            conn.createStatement().executeUpdate("create table if not exists Users(    \n" +
                    "    id bigint primary key AUTO_INCREMENT,\n" +
                    "\n" +
                    "    \n" +
                    "    name text,\n" +
                    "\n" +
                    "    \n" +
                    "    lastName text,\n" +
                    "\n" +
                    "    age tinyint)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            conn.createStatement().executeUpdate("drop table if exists Users");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement saveUser = conn.prepareStatement("insert into Users(name, lastName, age) values (?, ?, ?)")) {
            saveUser.setString(1, name);
            saveUser.setString(2, lastName);
            saveUser.setByte(3, age);
            saveUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement removeUserById = conn.prepareStatement("delete from Users where id = ?")) {
            removeUserById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = conn.prepareStatement("select * from Users").executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            conn.prepareStatement("delete from Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
