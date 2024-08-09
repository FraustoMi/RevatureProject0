package Revature.Project0.DAO;
import Revature.Project0.Model.User;
import Revature.Project0.Model.Hobby;

import java.util.List;

public interface UserDAOInterface {

    User insertUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();


}
