package org.example.roomresqbackend.Controller;

import org.example.roomresqbackend.Model.Admin;
import org.example.roomresqbackend.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/admin/completeprofile")
    public ResponseEntity<?> completeProfile(@RequestBody Admin admin) {
        if (admin.getFirebaseUid() == null || admin.getFirebaseUid().isEmpty()) {
            return ResponseEntity.badRequest().body("Firebase UID is required");
        }
        Admin updatedAdmin = adminService.createProfile(admin.getFirebaseUid(), admin.getHostelType(), admin.getBlock());

        return ResponseEntity.ok(admin);
    }
}
