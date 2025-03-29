package org.example.roomresqbackend.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import jakarta.transaction.Transactional;
import org.example.roomresqbackend.Model.Admin;
import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Repository.AdminRepository;
import org.example.roomresqbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireBaseAuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    public FirebaseToken verifyIdToken(String idToken) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(idToken);
    }

    public UserRecord getUserDetails(String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid);
    }


    @Transactional
    public Object authenticateWithGoogle(String idToken) throws FirebaseAuthException {

        FirebaseToken decodedToken = verifyIdToken(idToken);


        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();
        String name = decodedToken.getName();
        UserRecord userRecord = getUserDetails(uid);
        String photoUrl = userRecord.getPhotoUrl();


        if (email != null && email.contains("@vitstudent.ac.in")) {

            User user = userRepository.findByFirebaseUid(uid)
                    .orElse(new User());

            user.setFirebaseUid(uid);
            user.setEmail(email);
            user.setName(name);
            user.setPhotoUrl(photoUrl);

            return userRepository.save(user);
        } else {

            Admin admin = adminRepository.findByFirebaseUid(uid)
                    .orElse(new Admin());

            admin.setFirebaseUid(uid);
            admin.setEmail(email);
            admin.setName(name);
            admin.setPhotoUrl(photoUrl);

            return adminRepository.save(admin);
        }
    }
}