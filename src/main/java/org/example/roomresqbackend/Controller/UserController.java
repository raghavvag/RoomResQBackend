package org.example.roomresqbackend.Controller;

import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Repository.UserRepository;
import org.example.roomresqbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("user/completeprofile")
    public ResponseEntity<?> completeProfile(@RequestBody User user) {
        if(user.getFirebaseUid() == null || user.getFirebaseUid().isEmpty()) {
            return ResponseEntity.badRequest().body("Firebase UID is required");
        }
        User updatedUser = userService.createprofile(user.getFirebaseUid(), user.getPhonenumber(),
                user.getRoomNo(), user.getHostelType(), user.getBlock(), user.getRegNo());

        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getUserByFirebaseUid(@PathVariable String uid) {
        return userRepository.findByFirebaseUid(uid)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }


}