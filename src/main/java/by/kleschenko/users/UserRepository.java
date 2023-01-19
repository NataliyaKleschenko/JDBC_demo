package by.kleschenko.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.kleschenko.dbconnection.AbstractConnection.closeConnection;
import static by.kleschenko.dbconnection.AbstractConnection.getConnection;

public class UserRepository implements UserCRUDInterface<User, String> {
    @Override
    public boolean create(User user) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement prs = connection.prepareStatement("INSERT INTO user (login, password) VALUES (?,?)");
            prs.setString(1, user.getLogin());
            prs.setString(2, user.getPassword());
            prs.execute();
            return true;
        } catch (SQLException e) {
            throw new Exception("User not created", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public User read(String userName) throws Exception {
        User user = new User();
        try {
            Connection connection = getConnection();
            PreparedStatement prs = connection.prepareStatement("SELECT id, login, password FROM user WHERE login=?");
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new Exception("Failed to read user", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<User> readAll() throws Exception {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement prs = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new Exception("Failed to read users", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean delete(String login) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement prs = connection.prepareStatement("DELETE FROM user WHERE login=?");
            prs.setString(1, login);
            prs.execute();
            return true;
        } catch (SQLException e) {
            throw new Exception("User not deleted", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(String login, String newPassword) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement prs = connection.prepareStatement("UPDATE user SET password=? WHERE login=?");
            prs.setString(1, newPassword);
            prs.setString(2, login);
            prs.execute();
            return true;
        } catch (SQLException e) {
            throw new Exception("User not updated", e);
        } finally {
            closeConnection();
        }
    }
}
