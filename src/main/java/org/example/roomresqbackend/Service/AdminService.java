package org.example.roomresqbackend.Service;

import org.example.roomresqbackend.Model.Admin;
import org.example.roomresqbackend.Model.Maintainance;
import org.example.roomresqbackend.Repository.AdminRepository;
import org.example.roomresqbackend.Repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public Admin createProfile(String firebaseUid, String hostelType, String block) {
        Admin admin = adminRepository.findByFirebaseUid(firebaseUid)
                .orElse(new Admin());

        admin.setFirebaseUid(firebaseUid);
        admin.setHostelType(hostelType);
        admin.setBlock(block);

        return adminRepository.save(admin);
    }
    public List<Maintainance> getMaintainance(String Block) {
        return maintenanceRepository.findByBlock(Block);


    }
}
