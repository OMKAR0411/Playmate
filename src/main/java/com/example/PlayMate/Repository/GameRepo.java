package com.example.PlayMate.Repository;

import com.example.PlayMate.Entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<Game, Long> {
    List<Game> findByIsActiveTrue(); // Custom query to find active games
}
