package com.onepercent.goaltracker.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    UUID id;
    @Column(nullable = false)
    String title;
    String description;
    LocalDateTime created;
    LocalDateTime updated;
    @Column(nullable = false)
    UUID userId;
}
