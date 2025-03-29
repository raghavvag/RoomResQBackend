package org.example.roomresqbackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

    @Column(name = "email")
    private String email;

    @Column(unique = true, nullable = false)
    private String firebaseUid;

    private String name;
    private String hostelType;
    private String block;
    private String photoUrl;
}