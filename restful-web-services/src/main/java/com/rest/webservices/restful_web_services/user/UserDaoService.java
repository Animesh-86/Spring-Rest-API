package com.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static{
        users.add(new User(1, "Animesh", LocalDate.now().minusYears(5000)));
        users.add(new User(2, "Kajol", LocalDate.now().minusYears(5000)));
        users.add(new User(3, "Aryan", LocalDate.now().minusYears(5000)));
    }

    public List<User> findAll(){
        return users;
    }

    private static int usersCount = 3;

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id){
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public User deleteById(int id) {
        User user = findOne(id);
        if (user != null) {
            users.remove(user);
        }
        return user; // return the deleted user or null if not found
    }
}
