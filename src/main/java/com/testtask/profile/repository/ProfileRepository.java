package com.testtask.profile.repository;

import com.testtask.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findProfileByEmail(String email);
    Profile findFirstByOrderByCreatedDesc();
    Optional<Profile> findByEmailIgnoreCase(String email);
}
