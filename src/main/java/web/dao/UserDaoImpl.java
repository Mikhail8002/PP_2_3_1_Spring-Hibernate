package web.dao;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Repository

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional
    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<User> userById = findById(id);
        userById.ifPresent(user -> entityManager.remove(user));
    }
}
