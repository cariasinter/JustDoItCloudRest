package teccr.justdoitcloud.repository;

import org.springframework.stereotype.Repository;
import teccr.justdoitcloud.data.User;

import java.util.Optional;

/**
 * Repository interface for User-related operations.
 * Implementations will be provided later (in-memory, JPA, etc.).
 */
@Repository
public interface UserRepository {

    /**
     * Find a user by their userName.
     *
     * @param userName the username to look up
     * @return an Optional containing the User if found, otherwise empty
     */
    Optional<User> findByUserName(String userName);

    // Additional repository methods (save, findAll, existsBy..., etc.)
    // will be added when a persistence strategy is chosen.
}
