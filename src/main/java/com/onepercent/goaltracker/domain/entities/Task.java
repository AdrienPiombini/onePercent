package com.onepercent.goaltracker.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    UUID id;
    String title;
    String description;
    TaskStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    Goal goal;
}
