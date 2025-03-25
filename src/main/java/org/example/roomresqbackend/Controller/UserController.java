package org.example.roomresqbackend.Controller;

import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/completeprofile")
    public ResponseEntity<?> completeProfile(@RequestBody User user) {
        if(user.getFirebaseUid() == null || user.getFirebaseUid().isEmpty()) {
            return ResponseEntity.badRequest().body("Firebase UID is required");
        }
        userService.createprofile(user.getFirebaseUid(), user.getPhonenumber(), user.getRoomNo(), user.getHostelType(), user.getBlock(), user.getRegNo());


        return ResponseEntity.ok("Profile completed");

    }
}
