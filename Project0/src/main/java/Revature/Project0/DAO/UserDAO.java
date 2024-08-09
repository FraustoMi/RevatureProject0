package Revature.Project0.DAO;

import Revature.Project0.Model.User;
import Revature.Project0.Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface {


    @Override
    public User insertUser(User user) {
        /*
         id | first_name | last_name | username |  pass_word
        */

        String Query = "UPDATE USERS SET FIRST_NAME = ?, LAST_NAME = ?, USERNAME = ?, PASS_WORD = ? " +
                "WHERE ID = ?";
        System.out.println("we are trying to insert: " + user);
        User returnedUser = null;
        try(Connection conn = ConnectionSingleton.getConnection()){
            //create our SQL statement String
            String query = "INSERT INTO users (first_name,last_name,username,pass_word) VALUES (?,?,?,?)";

            //Instantiate a PreparedStatement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1,user.getFirstname());
            ps.setString(2,user.getLastname());
            ps.setString(3,user.getUsername());
            ps.setString(4,user.getPassword());
            ps.executeUpdate();


        //Now we can return the Employee to the user, assuming nothing went wrong
            System.out.println("insert successful");
            returnedUser = user;

        } catch(SQLException e){
            e.printStackTrace(); //Tell us what went wrong
            System.out.println("Failed to insert employee!");
        }

        return returnedUser;
    }

    @Override
    public User updateUser(User user) {


        String query = "UPDATE USERS SET FIRST_NAME = ?, LAST_NAME = ?, USERNAME = ?, PASS_WORD = ? " +
                "WHERE ID = ?";
        System.out.println("we are trying to update: " + user);
        User returnedUser = null;
        try(Connection conn = ConnectionSingleton.getConnection()){
            //create our SQL statement String

            //Instantiate a PreparedStatement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1,user.getFirstname());
            ps.setString(2,user.getLastname());
            ps.setString(3,user.getUsername());
            ps.setString(4,user.getPassword());
            ps.setInt(5,user.getId());
            ps.executeUpdate();


            //Now we can return the Employee to the user, assuming nothing went wrong
            System.out.println("update successful");
            returnedUser = user;

        } catch(SQLException e){
            e.printStackTrace(); //Tell us what went wrong
            System.out.println("Failed to update employee!");
        }

        return returnedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> returnedUsers = new ArrayList<>();
        try(Connection conn = ConnectionSingleton.getConnection()){

            String query = "Select DISTINCT * FROM USERS";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(query);

            while(rs.next()){

                returnedUsers.add(
                        new User(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("username"),
                                rs.getString("pass_word")
                        )
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return returnedUsers;

    }
}
