package ru.javamentor.springboot.dao;

import org.springframework.stereotype.Repository;
import ru.javamentor.springboot.model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Can't find User for ID " + id);
        }
        return user;
    }

    @Override
    public void update(long id, User user) {
        User userToBeUpdated = findById(id);
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setEmail(user.getEmail());
    }

    @Override
    public void delete(long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query=entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

}
