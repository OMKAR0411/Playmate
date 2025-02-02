package com.example.PlayMate.Service;

import com.example.PlayMate.Entity.Game;
import com.example.PlayMate.Entity.Post;
import com.example.PlayMate.Entity.User;
import com.example.PlayMate.Repository.GameRepo;
import com.example.PlayMate.Repository.PostRepo;
import com.example.PlayMate.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepository userRepo; // To fetch user details



    // Create a post with game and user association
    public Post createPost(Long gameId, Post post) {
        // Find the associated game
        Game game = gameRepo.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with ID: " + gameId));

        // Fetch the logged-in user's username from the SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user entity by username
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        // Set game and user in the post
        post.setGame(game);
        post.setCreatedBy(user);

        // Save the post
        return postRepo.save(post);
    }



    // Retrieve all posts for a specific game
    public List<Post> getPostByGame(Long gameId) {
        return postRepo.findByGameId(gameId);
    }

    // Delete a post by its ID
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    // Update an existing post's content and system type
    public Post updatePost(Long postId, Post updatedPost) {
        Post existingPost = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        existingPost.setContent(updatedPost.getContent());
        existingPost.setSystemType(updatedPost.getSystemType());

        return postRepo.save(existingPost);
    }
}
