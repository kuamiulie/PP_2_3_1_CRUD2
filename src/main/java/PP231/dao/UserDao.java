package PP231.dao;

import PP231.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void deleteUser(int id);

    List<User> getAllUsers();

    User getUserById(int id);
}
