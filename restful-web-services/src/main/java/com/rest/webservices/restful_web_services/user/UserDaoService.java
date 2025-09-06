package com.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static{
        users.add(new User(1, "Krishna", LocalDate.now().minusYears(5000)));
        users.add(new User(2, "Rukmani", LocalDate.now().minusYears(5000)));
        users.add(new User(3, "Satyabhama", LocalDate.now().minusYears(5000)));
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
}
