package com.parkify.parking_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @PostMapping
    public ResponseEntity<?> addSlot(@RequestBody ParkingSlot newSlot) {
        ParkingSlot savedSlot = parkingSlotRepository.save(newSlot);
        return ResponseEntity.ok(savedSlot);
    }

    @GetMapping
    public List<ParkingSlot> getAllSlots() {
        return parkingSlotRepository.findAll();
    }
}
