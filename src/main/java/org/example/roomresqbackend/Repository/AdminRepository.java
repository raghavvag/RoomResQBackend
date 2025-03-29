package org.example.roomresqbackend.Repository;

import org.example.roomresqbackend.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByFirebaseUid(String firebaseUid);
}