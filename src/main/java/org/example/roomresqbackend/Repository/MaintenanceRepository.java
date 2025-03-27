package org.example.roomresqbackend.Repository;

import org.example.roomresqbackend.Model.Maintainance;
import org.example.roomresqbackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintainance,Long> {
    List<Maintainance> findByUser(User user);
}
