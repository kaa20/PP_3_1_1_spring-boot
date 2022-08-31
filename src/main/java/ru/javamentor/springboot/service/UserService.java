package ru.javamentor.springboot.service;



import ru.javamentor.springboot.model.User;

import java.util.List;

public interface UserService {

    void add(User user);
    User findById(long id);
    void update(long id, User user);
    void delete(long id);
    List<User> listUsers();

}
