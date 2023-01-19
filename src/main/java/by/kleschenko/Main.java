package by.kleschenko;

import by.kleschenko.users.User;
import by.kleschenko.users.UserRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //User user = new User("Bob", "7777");
        UserRepository userRepository = new UserRepository();
        //userRepository.create(user);
        final List<User> users = userRepository.readAll();
        System.out.println(users);
    }
}
