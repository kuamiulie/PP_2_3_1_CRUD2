package PP231.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import PP231.dao.UserDao;
import PP231.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);

    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
