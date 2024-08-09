package Revature.Project0.Services;

import Revature.Project0.DAO.HobbyDAO;
import Revature.Project0.DAO.HobbyDAOInterface;
import Revature.Project0.Model.Hobby;

import java.util.*;

public class HobbyService implements HobbyDAOInterface {

    HobbyDAO inst = new HobbyDAO();
    @Override
    public ArrayList<Hobby> getAllHobbies() {
        return inst.getAllHobbies();
    }

    @Override
    public ArrayList<String> getHobbyById(int id) {
        return inst.getHobbyById(id);
    }

    @Override
    public Hobby insertHobby(Hobby hobby) {
        return inst.insertHobby(hobby);
    }

    @Override
    public Hobby deleteHobby(Hobby hobby) {
        return inst.deleteHobby(hobby);
    }
}
