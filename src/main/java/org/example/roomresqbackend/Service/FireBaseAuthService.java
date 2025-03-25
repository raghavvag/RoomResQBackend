package org.example.roomresqbackend.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireBaseAuthService {

    @Autowired
    private UserRepository userRepository;

    public FirebaseToken verifyIdToken(String idToken) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(idToken);
    }

    public UserRecord getUserDetails(String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid);
    }

    public User authenticateWithGoogle(String idToken) throws FirebaseAuthException {
        // Verify the Firebase ID token
        FirebaseToken decodedToken = verifyIdToken(idToken);

        // Get user details
        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();
        String name = decodedToken.getName();

        // Check if the user exists
        User user = userRepository.findByFirebaseUid(uid)
                .orElse(new User());

        // Update user information from Firebase
        user.setFirebaseUid(uid);
        user.setEmail(email);
        user.setName(name);

        // Save the user
        return userRepository.save(user);
    }
}