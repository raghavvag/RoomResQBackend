package org.example.roomresqbackend.Service;

import jakarta.transaction.Transactional;
import org.example.roomresqbackend.Model.Maintainance;
import org.example.roomresqbackend.Model.User;
import org.example.roomresqbackend.Repository.MaintenanceRepository;
import org.example.roomresqbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintainenceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Maintainance maintenance, User user) {

        maintenance.setBlock(user.getBlock());
        maintenance.setUser(user);
        maintenance.setStatus("pending");
        maintenanceRepository.save(maintenance);
    }

    public Optional<Object> getMaintenanceById(String id) {
        return maintenanceRepository.findById(Long.parseLong(id))
                .map(maintenance -> {
                    // Convert to a suitable response object if needed
                    return maintenance;
                });
    }

    @Transactional
    public List<Maintainance> getMaintenancesByUser(User user) {
        return maintenanceRepository.findByUser(user);
    }

    public Optional<Object> update(String id, String status) {
        Optional<Maintainance> optionalMaintainance = maintenanceRepository.findById(Long.parseLong(id));
        if (optionalMaintainance.isPresent()) {
            Maintainance m = optionalMaintainance.get();
            m.setStatus(status);
            return Optional.of(maintenanceRepository.save(m));
        } else {
            return Optional.empty();
        }
    }


}
