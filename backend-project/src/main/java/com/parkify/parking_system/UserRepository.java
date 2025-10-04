package com.parkify.parking_system;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// This interface gives us all the standard database methods for the User table.
public interface UserRepository extends JpaRepository<User, Integer> {

    // We can add custom methods too. Spring will understand this one means:
    // "Find a user by their username".
    Optional<User> findByUsername(String username);
}