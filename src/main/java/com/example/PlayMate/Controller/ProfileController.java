package com.example.PlayMate.Controller;

import com.example.PlayMate.Entity.Profile;
import com.example.PlayMate.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // 🔹 Get Full Profile (Name, Bio, Games, Profile Pic)
    @GetMapping("/full")
    public ResponseEntity<Profile> getFullProfile() {
        Optional<Profile> profile = profileService.getProfile();
        return profile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Get Name
    @GetMapping("/name")
    public ResponseEntity<String> getName() {
        String name = profileService.getName();
        return name != null ? ResponseEntity.ok(name) : ResponseEntity.notFound().build();
    }

    // 🔹 Save Name
    @PostMapping("/name")
    public ResponseEntity<Profile> saveName(@RequestParam String name) {
        Profile profile = profileService.saveName(name);
        return ResponseEntity.ok(profile);
    }

    // 🔹 Get Bio
    @GetMapping("/bio")
    public ResponseEntity<String> getBio() {
        String bio = profileService.getBio();
        return bio != null ? ResponseEntity.ok(bio) : ResponseEntity.notFound().build();
    }

    @GetMapping("/username")
    public ResponseEntity<String> getUserName() {
        String username = profileService.getUserName();
        return username != null ? ResponseEntity.ok(username) : ResponseEntity.notFound().build();
    }

    // 🔹 Save Bio
    @PostMapping("/bio")
    public ResponseEntity<Profile> saveBio(@RequestParam String bio) {
        Profile profile = profileService.saveBio(bio);
        return ResponseEntity.ok(profile);
    }

    // 🔹 Get Games
    @GetMapping("/games")
    public ResponseEntity<String> getGames() {
        String games = profileService.getGames();
        return games != null ? ResponseEntity.ok(games) : ResponseEntity.notFound().build();
    }

    // 🔹 Save Games
    @PostMapping("/games")
    public ResponseEntity<Profile> saveGames(@RequestParam String games) {
        Profile profile = profileService.saveGames(games);
        return ResponseEntity.ok(profile);
    }

    // 🔹 Get Profile Picture
    @PostMapping("/profilePic")
    public String setProfilePic(@RequestParam("profilePic") MultipartFile profilePic) {
        return profileService.setProfilePic(profilePic);
    }

    @GetMapping("/pic")
    public ResponseEntity<String> getProfilePic() {
        String base64Image = profileService.getProfilePic();
        if (base64Image != null) {
            return ResponseEntity.ok(base64Image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile picture not found");
    }
}
