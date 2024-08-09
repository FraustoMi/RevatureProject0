package Revature.Project0.Controllers;
import Revature.Project0.Controllers.HobbyController;
import Revature.Project0.Model.Hobby;

import Revature.Project0.Services.HobbyService;
import Revature.Project0.Util.ConnectionSingleton;


import Revature.Project0.DAO.HobbyDAO;

import io.javalin.http.Handler;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HobbyController {
    HobbyService hs = new HobbyService();

    public Handler getHobbiesHandler = ctx -> {
        System.out.println("we are inside get users handle");

        //Get an ArrayList of employees, populated by the getEmployees service method
        ArrayList<Hobby> employees = hs.getAllHobbies();

        //PROBLEM: we can't send plain Java in an HTTP Response - it only takes JSON

        //We can use the .json() method to convert this ArrayList to JSON
        //NOTE: This method also returns the object once the code block is done
        ctx.json(employees);

        //We can also set the HTTP Response status code with ctx.status()
        ctx.status(200);
    };

    public Handler getHobbyById = ctx -> {

        int this_ID = Integer.parseInt(ctx.pathParam("id"));

        ArrayList<String> curr = hs.getHobbyById(this_ID);

        ctx.json(curr);

        ctx.status(200);
    };

    public Handler insertHobbyHandler = ctx -> {
        try {

            String stringBody = ctx.body();
            Hobby hob = getHobbyFromJSON(stringBody);
            Hobby insertedHobby = hs.insertHobby(hob);
            ctx.json(insertedHobby);

        } catch (Exception e) {
            System.out.println("something went wrong in Hobby InsertUserHandler");
            e.printStackTrace();
            ctx.status(400);
        }
        //We can also set the HTTP Response status code with ctx.status()
        ctx.status(200);
    };

    public Handler deleteHobbyHandler = ctx -> {

        try {

            Hobby hob = getHobbyFromJSON(ctx.body());
            Hobby deletedHobby = hs.deleteHobby(hob);
            ctx.json(deletedHobby);

        }catch (Exception e){
            System.out.println("something occurred in Hobby delete");
            e.printStackTrace();
        }
    };


    private static Hobby getHobbyFromJSON(String stringJSON) {
        JSONObject jsonObject = new JSONObject(stringJSON);
        int id = jsonObject.getInt("fk_user_int");
        String hobby = jsonObject.getString("hobby");
        Hobby the_Hobby = new Hobby(id,hobby);
        return the_Hobby;
    }




}
