package com.onepercent.goaltracker.repositories;

import com.onepercent.goaltracker.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
