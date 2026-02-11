package teccr.justdoitcloud.repository;

import org.springframework.stereotype.Repository;
import teccr.justdoitcloud.data.Task;
import teccr.justdoitcloud.data.User;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple in-memory repository implementation for initial testing.
 * This is a temporary implementation until a persistence layer is added.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<Task> defaultTasks = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize default tasks list
        Task task = new Task("Comprar Leche", LocalDateTime.now(), null, Task.Status.DONE);
        defaultTasks.add(task);
        task = new Task("Reparacion de sistema de frenos del carro", LocalDateTime.now(),
                LocalDateTime.now().plusDays(3).toLocalDate(), Task.Status.INPROGRESS);
        defaultTasks.add(task);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return Optional.empty();
        }
        String trimmedUserName = userName.trim();
        User user = new User(trimmedUserName, toDisplayName(trimmedUserName), toEmail(trimmedUserName), User.Type.REGULAR);
        defaultTasks.forEach(user::addTask);
        return Optional.of(user);
    }

    private String toDisplayName(String userName) {
        // Simple logic to convert username to display name (e.g., "christine_mcvie" -> "Christine Mcvie")
        String[] parts = userName.split("_");
        StringBuilder displayName = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                displayName.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1))
                        .append(" ");
            }
        }
        return displayName.toString().trim();
    }

    private String toEmail(String userName) {
        // Simple logic to convert username to email (e.g., "christine_mcvie" -> "christine_mcvie@gmail.com")
        return userName + "@gmail.com";
    }
}
