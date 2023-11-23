package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
     public User getUser(Integer id);

     public List<User> getUsers();

     public void createUser(User user);

     public void updateUser(User user);

     public void deleteUser(User user);

}
