package com.onepercent.goaltracker.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    UUID id;
    String title;
    String description;
    @OneToMany(mappedBy = "goal", cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST
    })
    List<Task> tasks;
    LocalDateTime created;
    LocalDateTime updated;
}
