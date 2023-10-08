package org.aggregator.aggregator.model.dao.user;

import org.aggregator.aggregator.model.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String name);
    User findUserByUsername(String name);
}
