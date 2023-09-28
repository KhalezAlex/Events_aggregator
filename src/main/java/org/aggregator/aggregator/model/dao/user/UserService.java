package org.aggregator.aggregator.model.dao.user;

import org.aggregator.aggregator.model.entities.User;

import java.util.List;
import java.util.Optional;

public class UserService implements IDaoUser{
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(Integer id) {
        return null;
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return Optional.empty();
    }

    @Override
    public User findUserByUsername(String name) {
        return null;
    }

    @Override
    public void saveAdmin(User user) {

    }

}
