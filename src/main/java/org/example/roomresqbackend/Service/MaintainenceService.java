package org.example.roomresqbackend.Service;

import org.example.roomresqbackend.Model.Maintainance;
import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintainenceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    public void save(Maintainance maintenance, User user) {
        maintenance.setUser(user);
        maintenance.setStatus("pending");
        maintenanceRepository.save(maintenance);

    }
}
