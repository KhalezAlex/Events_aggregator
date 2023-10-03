package org.aggregator.aggregator.model.dao.user;

import org.aggregator.aggregator.model.dao.IDaoDB;
import org.aggregator.aggregator.model.dto.RegistrationUserDto;
import org.aggregator.aggregator.model.entities.User;

import java.util.Optional;

public interface IDaoUser extends IDaoDB<User> {
    Optional<User> findUserByName(String name);
    User findUserByUsername(String name);
    void saveAdmin(User user);
}
