package PP231.service;

import PP231.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void deleteUser(int id);

    List<User> getAllUsers();

    User getUserById(int id);
}
