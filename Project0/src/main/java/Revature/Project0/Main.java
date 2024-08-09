package Revature.Project0;

import Revature.Project0.Controllers.HobbyController;
import Revature.Project0.Controllers.UserController;
import Revature.Project0.DAO.UserDAO;
import Revature.Project0.Util.ConnectionSingleton;
import Revature.Project0.DAO.HobbyDAO;
import Revature.Project0.Model.Hobby;
import Revature.Project0.Model.User;

import io.javalin.Javalin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        /* This is a "try with resources" block
        A resource is opened up within the parentheses of the try block
        If the resource is successfully created, the rest of the try block runs
        A big benefit of this is that the resource will close after the try block finishes
        This is helpful for code cleanup and preventing memory leaks */
        try(Connection conn = ConnectionSingleton.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL :)");
        } catch(SQLException e){
            e.printStackTrace(); //tell us what went wrong
            System.out.println("connection failed :(");
        }

        //Typical Javalin setup syntax
        var app = Javalin.create(/*any extra configs would go here*/)
                .start(3000);
               // .get("/booya", ctx -> ctx.result("Hello Postman!")); //callable resource just for fun

        /*We need .start() to start our Javalin server on port 3000
         You can choose any port, I chose 3000 because probably nothing is running on it
         a port is like a parking space for an application, where messages, etc. can find it*/


        //ENDPOINT HANDLERS----------------------

        app.get("/booya", ctx -> ctx.result("Yoga"));

        //TODO: a bunch of notes

        Hobby abc = new Hobby(2,"Table Tennis");
        User tempUser = new User(4,"Greg","Sweeney","Sweeng","SWE");
        HobbyDAO a = new HobbyDAO();
        UserDAO userD = new UserDAO();

        //instantiate Controllers so we can access their Handlers
        HobbyController hc = new HobbyController();
        UserController uc = new UserController();

        app.get("/Users",uc.getUsersHandler);

        app.post("/Users",uc.insertUserHandler);

        app.patch("Users", uc.updateUserHandler);

        app.get("/Hobbies",hc.getHobbiesHandler);

        app.get("/Hobbies/{id}",hc.getHobbyById);

        app.post("/Hobbies",hc.insertHobbyHandler);
        app.delete("/Hobbies",hc.deleteHobbyHandler);







    }
}