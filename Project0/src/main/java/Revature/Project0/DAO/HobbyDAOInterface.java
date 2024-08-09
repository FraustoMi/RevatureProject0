package Revature.Project0.DAO;
import Revature.Project0.Model.Hobby;

import java.sql.SQLException;
import java.util.List;

public interface HobbyDAOInterface {




    List<Hobby> getAllHobbies();


    List<String> getHobbyById(int id);


    Hobby insertHobby(Hobby hobby);

    Hobby deleteHobby(Hobby hobby);


}




