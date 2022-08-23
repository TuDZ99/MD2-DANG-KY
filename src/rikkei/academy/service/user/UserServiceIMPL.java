package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.model.User;
import java.util.List;

public class UserServiceIMPL implements IUserService{

    static String PATH_USER = "C:\\Users\\Thanh Tu\\IdeaProjects\\Role\\src\\rikkei\\academy\\database\\user.txt";

    static Config<List<User>> config = new Config<>();

    public static List<User> userList = config.read(PATH_USER);

    static {

    }
    @Override
    public List<User> findAll() {
        config.write(PATH_USER,userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        config.write(PATH_USER,userList);
    }

    @Override
    public boolean existsByUsername(String username) {
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user : userList){
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
}
