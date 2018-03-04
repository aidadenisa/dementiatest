package dt.service;

import dt.model.User;
import dt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(int userId) {
        return userRepository.findOne(userId);
    }

    public void saveUser(User user) {
        user.setBirthday(new Date());
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }


    public void deleteUser(int userId) {
        userRepository.delete(userId);
    }
}
