package com.example.PlayMate.Repository;

import com.example.PlayMate.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile,Long> {
    Optional<Profile> findByUserId(Long userId); // Fetch profile by user ID

}
