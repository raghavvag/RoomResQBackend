package org.example.roomresqbackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Maintenace")
public class Maintainance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketid;
    private String issue;
    private String description;
    private String urgency;
    private String status;
    private LocalDateTime date;
    private LocalDateTime time;
    private String category;
    @Lob
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "email")
    private User user;
    private String block;


}
