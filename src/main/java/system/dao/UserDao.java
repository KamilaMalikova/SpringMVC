package system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import system.model.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@Repository
public class UserDao {
    private List<User> users = Arrays.asList(new User("kamila", "1234"),
            new User("user,", "user1"),
            new User("user2", "user2"),
            new User("admin", "admin"));
    @Autowired
    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public int checkUser(User user){
        String sql = "select COUNT(id)  from userlist where username = '"+user.getName()+"' and password = '"+user.getPassword()+"';";
        return template.queryForObject(sql, int.class);

    }


    public List<User> select(){
        String sql = "select id, username, password  from userlist;";
        System.out.println(sql);
        users = template.query(sql, (rs, rowNum) ->

                new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
        return users;
    }

    public User select(User user){
        String sql = "select id, username, password  from userlist where username = '"+user.getName()+"' and password = '"+user.getPassword()+"';";
        System.out.println(sql);
        return template.queryForObject(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
    }

    public int insertUser(User user){
        String sql = "Insert into userlist " +
                "values(null, '" + user.getName()+
                "', '"+user.getPassword()+
                "', now()) ;";
        System.out.println(sql);
        return  template.update(sql);
    }

    public int update(User oldUser, User newUser){
        String sql = "Update userlist set username = '"+newUser.getName()+"', password = '"+newUser.getPassword()+
                "' where username = '"+oldUser.getName()+"' and password = '"+oldUser.getPassword()+"';";
        return template.update(sql);
    }
    public int delete(User user){
        String sql = "Delete from userlist where username = '"+user.getName()+"' and password = '"+user.getPassword()+"'; ";
        return template.update(sql);
    }
}
