package com.example.PlayMate.Service;

import com.example.PlayMate.Entity.Game;
import com.example.PlayMate.Repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepo gameRepo;

    // Fetch all active games and convert the images to Base64 strings
    public List<Game> getAll() {
        List<Game> games = gameRepo.findByIsActiveTrue(); // Fetch only active games

        // Iterate over each game and encode image to Base64 if image exists
        for (Game game : games) {
            game.setName(game.getName());
            if (game.getImage() != null) {
                String base64Image = Base64.getEncoder().encodeToString(game.getImage());
                game.setBase64Image(base64Image);
                System.out.println(base64Image);
            }
        }

        return games;
    }

    // Add new game with image
    public Game addGame(Game game, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            game.setImage(imageBytes);  // Store the image as a byte array
        }
        return gameRepo.save(game);
    }

    // Fetch a game by its ID
    public Game gameById(Long id) {
        return gameRepo.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    // Method to hide a game
    public Game hideGame(Long id) {
        Game game = gameRepo.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        game.setActive(false);  // Set the game as hidden
        return gameRepo.save(game);
    }

    // Method to unhide a game
    public Game unhideGame(Long id) {
        Game game = gameRepo.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        game.setActive(true);  // Set isActive to true to unhide the game
        return gameRepo.save(game);  // Save the updated game
    }



    public Game updateGame(Long id, String name, MultipartFile image) {
        // Find the existing game by ID
        Game existingGame = gameRepo.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));

        // Update the name if provided
        if (name != null && !name.isEmpty()) {
            existingGame.setName(name);
        }

        // Handle the image update (if the image is provided)
        if (image != null && !image.isEmpty()) {
            try {
                // Convert the image file to a byte array
                byte[] imageBytes = image.getBytes();

                // Set the byte array as the new image for the game
                existingGame.setImage(imageBytes);

            } catch (IOException e) {
                throw new RuntimeException("Error while processing the image file", e);
            }
        }

        // Save and return the updated game
        return gameRepo.save(existingGame);
    }


}
