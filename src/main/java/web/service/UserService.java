package web.service;

import java.util.List;

import web.model.User;

public interface UserService {
    public User getUser(Integer id);

    public List<User> getUsers();

    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);
}
