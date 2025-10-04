package com.parkify.parking_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    // This is the endpoint to create a new reservation
    @PostMapping
    public ResponseEntity<?> makeReservation(@RequestBody Map<String, Object> payload) {
        Integer userId = Integer.parseInt(payload.get("userId").toString());
        Integer slotId = Integer.parseInt(payload.get("slotId").toString());
        String vehicleNumber = (String) payload.get("vehicleNumber");
        LocalDateTime startTime = LocalDateTime.parse((String) payload.get("startTime"));
        LocalDateTime endTime = LocalDateTime.parse((String) payload.get("endTime"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        ParkingSlot slot = parkingSlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Error: Slot not found."));

        Reservation newReservation = new Reservation();
        newReservation.setUser(user);
        newReservation.setParkingSlot(slot);
        newReservation.setVehicleNumber(vehicleNumber);
        newReservation.setStartTime(startTime);
        newReservation.setEndTime(endTime);

        reservationRepository.save(newReservation);

        return ResponseEntity.ok("Reservation created successfully!");
    }

    // This is the new endpoint for the history page
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
    
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return ResponseEntity.ok(reservations);
    }
}