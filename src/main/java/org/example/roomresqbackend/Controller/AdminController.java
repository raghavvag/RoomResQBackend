package org.example.roomresqbackend.Controller;

import org.example.roomresqbackend.Model.Admin;
import org.example.roomresqbackend.Model.Maintainance;
import org.example.roomresqbackend.Repository.AdminRepository;
import org.example.roomresqbackend.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminService;
@Autowired
private AdminRepository adminRepository;
    @PostMapping("/admin/completeprofile")
    public ResponseEntity<?> completeProfile(@RequestBody Admin admin) {
        if (admin.getFirebaseUid() == null || admin.getFirebaseUid().isEmpty()) {
            return ResponseEntity.badRequest().body("Firebase UID is required");
        }
        Admin updatedAdmin = adminService.createProfile(admin.getFirebaseUid(), admin.getHostelType(), admin.getBlock());

        return ResponseEntity.ok(admin);
    }

    @GetMapping("/admin/{block}")
    public ResponseEntity<?> getAdminByBlock(@PathVariable String block) {
        List<Maintainance> maintainances = adminService.getMaintainance(block);
        if (maintainances.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maintainances);
    }

    @GetMapping("/admin/profile/{uid}")
    public ResponseEntity<?> getAdminByFirebaseUid(@PathVariable String uid) {
        return adminRepository.findAdminByFirebaseUid(uid)
                .map(admin -> ResponseEntity.ok(admin))
                .orElse(ResponseEntity.notFound().build());
    }

}