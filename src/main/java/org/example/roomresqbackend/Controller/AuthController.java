package org.example.roomresqbackend.Controller;

import com.google.firebase.auth.FirebaseAuthException;
import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Service.FireBaseAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private FireBaseAuthService firebaseAuthService;

    @PostMapping("/google-signin")
    public ResponseEntity<?> googleSignIn(@RequestBody Map<String, String> request) {
        try {
            String idToken = request.get("idToken");
            if (idToken == null || idToken.isEmpty()) {
                return ResponseEntity.badRequest().body("ID token is required");
            }

            Object authenticatedUser = firebaseAuthService.authenticateWithGoogle(idToken);

            Map<String, Object> response = new HashMap<>();
            if (authenticatedUser instanceof User) {
                response.put("user", authenticatedUser);
                response.put("role", "student");
            } else {
                response.put("user", authenticatedUser);
                response.put("role", "admin");
            }
            response.put("message", "Google sign-in successful");

            return ResponseEntity.ok(response);

        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during sign-in: " + e.getMessage());
        }
    }
}