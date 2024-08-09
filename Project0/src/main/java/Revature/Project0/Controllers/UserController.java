package Revature.Project0.Controllers;


import Revature.Project0.Model.User;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;

import Revature.Project0.Services.UserService;
import io.javalin.http.Handler;

public class UserController {

    UserService userServ = new UserService();


    public Handler getUsersHandler = ctx -> {
        System.out.println("we are inside get users handle");

        //Get an ArrayList of employees, populated by the getEmployees service method
        ArrayList<User> employees = userServ.getAllUsers();

        //PROBLEM: we can't send plain Java in an HTTP Response - it only takes JSON

        //We can use the .json() method to convert this ArrayList to JSON
        //NOTE: This method also returns the object once the code block is done
        ctx.json(employees);

        //We can also set the HTTP Response status code with ctx.status()
        ctx.status(200);
    };

    public Handler insertUserHandler = ctx -> {
        try {

            String stringBody = ctx.body();
            User user = getUserFromJSONInsert(stringBody);
            User insertedUser = userServ.insertUser(user);
            ctx.json(insertedUser);

        } catch (Exception e) {
            System.out.println("something went wrong in InsertUserHandler");
            ctx.status(400);
        }
        //We can also set the HTTP Response status code with ctx.status()
        ctx.status(200);
    };

    public Handler updateUserHandler = ctx -> {
        try {
            String stringBody = ctx.body();
            User user = getUserFromJSONUpdate(stringBody);
            User updatedUser = userServ.updateUser(user);
            ctx.json(updatedUser);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong in the updatedUserHandler");
            ctx.status(400);
        }
        ctx.status(200);
    };


    private static User getUserFromJSONInsert(String stringJSON) {
        JSONObject jsonObject = new JSONObject(stringJSON);
        String firstname = jsonObject.getString("firstname");
        String lastname = jsonObject.getString("lastname");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        User the_user = new User(
                firstname, lastname, username, password
        );
        return the_user;
    }


    private static User getUserFromJSONUpdate(String stringJSON) {
        try {
            JSONObject jsonObject = new JSONObject(stringJSON);

            int id = jsonObject.getInt("id");
            String firstname = jsonObject.getString("firstname");
            String lastname = jsonObject.getString("lastname");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            User the_user = new User(
                    id, firstname, lastname, username, password
            );
            return the_user;
        } catch (JSONException e) {
            System.out.println("wrong input sent in update request");
            return null;
        }

    }
}