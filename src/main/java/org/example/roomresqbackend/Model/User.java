package org.example.roomresqbackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name ="email")
    private String email;

    @Column(unique = true,nullable = false)
    private String firebaseUid;

    private String name;
    private String phonenumber;
    private String regNo;
    private String hostelType;
    private String block;
    private String roomNo;
    private String photoUrl;


}