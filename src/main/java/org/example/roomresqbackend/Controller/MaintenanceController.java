package org.example.roomresqbackend.Controller;

import org.example.roomresqbackend.Model.Maintainance;
import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Repository.UserRepository;
import org.example.roomresqbackend.Service.MaintainenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class MaintenanceController {
    @Autowired
    private MaintainenceService maintenanceService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/maintenance")
    public ResponseEntity<?> maintenance(@RequestBody Maintainance maintenance, @RequestParam String email) {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        maintenanceService.save(maintenance, user);
        return ResponseEntity.ok(maintenance);
    }
}