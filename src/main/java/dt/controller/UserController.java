package dt.controller;

import dt.model.UserAccount;
import dt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/users")
    public List<UserAccount> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/users/{userId}")
    public UserAccount getUser(@PathVariable int userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/users")
    public void saveUser(@RequestBody UserAccount user) {
        userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT ,value = "/users")
    public void updateUser(@RequestBody UserAccount user) {
        userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE ,value = "/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

//    @RequestMapping(method = RequestMethod.POST, value="/users/login")
//    public void loginUser(@RequestBody UserAccount user) {
//        if(userService.getUserByEmail(user.getEmail()) != null) {
//
//        } else {
//            user.setHash(bCryptPasswordEncoder.encode(user.getHash()));
//            userService.saveUser(user);
//        }
//    }

//    @RequestMapping(method = RequestMethod.POST, value="/users/login")
//    public void loginUser(@RequestBody UserAccount user) {
////        user.setHash(bCryptPasswordEncoder.encode(user.getHash()));
////        userService.saveUser(user);
//    }

    @RequestMapping(method = RequestMethod.POST, value="/register")
    public void registerUser(@RequestBody UserAccount user) {
        user.setHash(bCryptPasswordEncoder.encode(user.getHash()));
        userService.saveUser(user);
    }

//    @RequestMapping("/users/")

}
