package com.example.PlayMate.Controller;

import com.example.PlayMate.Entity.Game;
import com.example.PlayMate.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/api/games")
    public List<Game> getAllGames() {
        return gameService.getAll(); // This will now return only active games
    }

    @GetMapping("/api/games/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameService.gameById(id);
    }

    @PostMapping("/api/games/create")
    public ResponseEntity<Game> createGame(@RequestParam("name") String name,
                                           @RequestParam("image") MultipartFile imageFile) throws IOException {
        Game newGame = new Game();
        newGame.setName(name);
        Game savedGame = gameService.addGame(newGame, imageFile);
        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    // Endpoint to hide a game
    @PatchMapping("/api/games/{id}/hide")
    public ResponseEntity<Game> hideGame(@PathVariable Long id) {
        Game hiddenGame = gameService.hideGame(id);
        return new ResponseEntity<>(hiddenGame, HttpStatus.OK);
    }

    @PatchMapping("/api/games/{id}/unhide") // Endpoint to unhide a game
    public ResponseEntity<Game> unhideGame(@PathVariable Long id) {
        Game game = gameService.unhideGame(id); // Call service to unhide the game
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
    @PutMapping("/api/games/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) MultipartFile image) {
        Game updatedGame = gameService.updateGame(id, name, image);
        return ResponseEntity.ok(updatedGame);
    }

}
