package Revature.Project0.DAO;
import Revature.Project0.Model.Hobby;
import Revature.Project0.Util.ConnectionSingleton;
import java.sql.*;
import java.util.ArrayList;


public class HobbyDAO implements HobbyDAOInterface {
    @Override
    public ArrayList<Hobby> getAllHobbies()  {
        // role_id, String role_title, int role_salary
        ArrayList<Hobby> returnedHobbies = new ArrayList<>();
        try(Connection conn = ConnectionSingleton.getConnection()){

            String query = "Select DISTINCT * FROM hobbies";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(query);

            while(rs.next()){

                returnedHobbies.add(

                        new Hobby(
                                rs.getInt("fk_user_id"),
                                rs.getString("hobby")
                        )
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return returnedHobbies;
    }



    @Override
    public ArrayList<String> getHobbyById(int id) {

        ArrayList<String> returnedList = new ArrayList<>();

        try(Connection conn = ConnectionSingleton.getConnection()){

            String sql = "SELECT * FROM hobbies WHERE fk_user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            //now we can use the id parameter to fill in the variable
            ps.setInt(1, id);

            //Execute the query, save the results in a ResultSet
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                returnedList.add(rs.getString("hobby"));
            }

        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't get Role by ID!");
        }

        return returnedList;
    }

    @Override
    public Hobby insertHobby(Hobby hobby) {
        System.out.println("we are trying to insert: " + hobby);

        Hobby returnedHobby = null;

        try(Connection conn = ConnectionSingleton.getConnection()){

            //create our SQL statement String
            String sql = "INSERT INTO hobbies (fk_user_id, hobby) VALUES (?,?)";

            //Instantiate a PreparedStatement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(sql);

            //fill in each wildcard with the Employee object and ps.setXYZ() methods
            ps.setInt(1, hobby.getFk_user_int());
            ps.setString(2, hobby.getHobby());


            //Now that our SQL command is complete, we can execute it
            ps.executeUpdate();
            //NOTE: executeUpdate() is used for INSERT, UPDATE, DELETE commands
            //...while executeQuery() is used for SELECT (querying the DB)

            //Now we can return the Employee to the user, assuming nothing went wrong
            System.out.println("insert successful");
            returnedHobby = hobby;

        } catch(SQLException e){
            e.printStackTrace(); //Tell us what went wrong
            System.out.println("Failed to insert employee!");
        }

        return returnedHobby;


    }
    @Override
    public Hobby deleteHobby(Hobby hobby) {
        System.out.println("we are trying to delete: " + hobby);
        Hobby returned_Hobby = null;
        try(Connection conn = ConnectionSingleton.getConnection()){

            String query = "DELETE FROM hobbies WHERE fk_user_id = ? AND hobby = ?";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1,hobby.getFk_user_int());
            ps.setString(2,hobby.getHobby());
            ps.executeUpdate();

            System.out.println("delete successful");
            returned_Hobby = hobby;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("failed to delete employee!");
        }
        return returned_Hobby;
    }

}
