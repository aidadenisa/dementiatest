package dt.service;

import dt.model.UserAccount;
import dt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<UserAccount> getAllUsers() {
        List<UserAccount> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public UserAccount getUser(long userId) {
        return userRepository.findOne(userId);
    }

    public UserAccount getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void saveUser(UserAccount user) {
//        user.setBirthday(new Date());
        userRepository.save(user);
    }

    public void updateUser(UserAccount user) {
        userRepository.save(user);
    }


    public void deleteUser(long userId) {
        userRepository.delete(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getEmail(), user.getHash(), emptyList());
    }
}
