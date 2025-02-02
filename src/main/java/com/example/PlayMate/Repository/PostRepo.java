package com.example.PlayMate.Repository;

import com.example.PlayMate.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByGameId(Long gameId);
}
