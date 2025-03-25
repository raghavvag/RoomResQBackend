package org.example.roomresqbackend.Service;

import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createprofile(String firebaseUid, String phonenumber, String roomNo, String hostelType, String Block, String regNo) {
        User user = userRepository.findByFirebaseUid(firebaseUid)
                .orElse(new User());

        user.setFirebaseUid(firebaseUid);
        user.setPhonenumber(phonenumber);
        user.setRoomNo(roomNo);
        user.setHostelType(hostelType);
        user.setBlock(Block);
        user.setRegNo(regNo);

        return userRepository.save(user);
    }
}