package Revature.Project0.Services;
import Revature.Project0.DAO.UserDAO;
import Revature.Project0.DAO.UserDAOInterface;
import Revature.Project0.Model.User;
import java.util.*;

public class UserService implements UserDAOInterface {
    UserDAO dao = new UserDAO();

    @Override
    public User insertUser(User user) {
        return dao.insertUser(user);
    }

    @Override
    public User updateUser(User user) {
        return dao.updateUser(user);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return dao.getAllUsers();
    }
}
