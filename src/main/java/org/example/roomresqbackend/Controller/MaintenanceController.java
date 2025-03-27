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
import java.util.List;

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
//for single maintenance
    @GetMapping("/maintenance/{id}")
    public ResponseEntity<?> getMaintenance(@PathVariable String id) {
        return maintenanceService.getMaintenanceById(id)
                .map(maintenance -> ResponseEntity.ok(maintenance))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maintenance request not found"));
    }
//for all maintenances
    @GetMapping("/maintenance/user")
    public ResponseEntity<?> getUserMaintenances(@RequestParam String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<Maintainance> maintenances = maintenanceService.getMaintenancesByUser(user);
        return ResponseEntity.ok(maintenances);
    }
}