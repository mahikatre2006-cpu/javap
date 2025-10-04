package com.parkify.parking_system;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // <-- ADD THIS IMPORT LINE

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    
    // ADD THIS METHOD LINE INSIDE THE BRACES
    List<Reservation> findByUser(User user); 
    
}