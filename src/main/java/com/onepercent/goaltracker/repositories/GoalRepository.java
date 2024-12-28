package com.onepercent.goaltracker.repositories;

import com.onepercent.goaltracker.domain.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {
}
