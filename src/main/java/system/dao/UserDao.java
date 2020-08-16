package system.dao;

import org.springframework.stereotype.Repository;
import system.model.User;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDao {

    private List<User> users = Arrays.asList(new User("kamila", "1234"),
            new User("user,", "user1"),
            new User("user2", "user2"));

    public List<User> getAllUsers(){
        return users;
    }
}
