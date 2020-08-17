package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    public boolean checkUser(User user){
        int counter = userDao.checkUser(user);
        if (counter == 0) return false;
        else return true;
    }

    public int insertUser(User user) throws SQLException {
        if (!this.checkUser(user)) return userDao.insertUser(user);
        else {
            List<User> list = userDao.select();
            for (User user1: list) {
                System.out.println(user1);
            }
            return 0;
        }
    }
}

//slack
//127.0.0.53