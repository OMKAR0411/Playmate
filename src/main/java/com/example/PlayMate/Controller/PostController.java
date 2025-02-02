package com.example.PlayMate.Controller;

import com.example.PlayMate.Entity.Post;
import com.example.PlayMate.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;


    @GetMapping("/{gameId}")
    public List<Post> getPostsByGame(@PathVariable Long gameId) {
        return postService.getPostByGame(gameId);
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<Post> addPost(@PathVariable Long gameId, @RequestBody Post post) {
        try {
            Post createdPost = postService.createPost(gameId, post);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        try {
            Post updatedPost = postService.updatePost(postId, post);
            return ResponseEntity.ok(updatedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a post by its ID
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok("Post deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }
}
