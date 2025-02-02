package com.example.PlayMate.Service;

import com.example.PlayMate.Entity.Profile;
import com.example.PlayMate.Entity.User;
import com.example.PlayMate.Repository.ProfileRepo;
import com.example.PlayMate.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepo profileRepository;

    @Autowired
    private UserRepository userRepo;

    // 🔹 Get User ID from the currently authenticated username
    public User getUser() {
        // Fetch the username from the SecurityContext (logged-in user)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user entity by username
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        // Return the user's ID
        return user;
    }

    // 🔹 Save Name
    public Profile saveName(String name) {
        User user = getUser(); // Get the current logged-in user's ID
        Profile profile = profileRepository.findByUserId(user.getId()).orElse(new Profile());
        profile.setUser(user);
        profile.setName(name);
        return profileRepository.save(profile);
    }

    // 🔹 Get Name
    public String getName() {
        User user = getUser(); // Get the current logged-in user's ID
        return profileRepository.findByUserId(user.getId()).map(Profile::getName).orElse(null);
    }

    public String getUserName() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

    // 🔹 Save Bio
    public Profile saveBio(String bio) {
        User user = getUser(); // Get the current logged-in user's ID
        Profile profile = profileRepository.findByUserId(user.getId()).orElse(new Profile());
        profile.setBio(bio);
        return profileRepository.save(profile);
    }

    // 🔹 Get Bio
    public String getBio() {
        User user = getUser(); // Get the current logged-in user's ID
        return profileRepository.findByUserId(user.getId()).map(Profile::getBio).orElse(null);
    }

    // 🔹 Save Games
    public Profile saveGames(String games) {
        User user = getUser(); // Get the current logged-in user's ID
        Profile profile = profileRepository.findByUserId(user.getId()).orElse(new Profile());
        profile.setGames(games);
        return profileRepository.save(profile);
    }

    // 🔹 Get Games
    public String getGames() {
        User user = getUser(); // Get the current logged-in user's ID
        return profileRepository.findByUserId(user.getId()).map(Profile::getGames).orElse(null);
    }

    // 🔹 Save Profile Picture
    public Profile saveProfilePic(byte[] profilePic) {
        User user = getUser(); // Get the current logged-in user's ID
        Profile profile = profileRepository.findByUserId(user.getId()).orElse(new Profile());
        profile.setProfilePic(profilePic);
        return profileRepository.save(profile);
    }

    // 🔹 Get Profile Picture
    public String setProfilePic(MultipartFile profilePic) {
        try {
            if (profilePic.isEmpty()) {
                return "No file uploaded";
            }

            User user = getUser(); // Get the currently authenticated user

            // Convert MultipartFile to byte array
            byte[] profilePicBytes = profilePic.getBytes();

            // Verify the file size is not empty
            if (profilePicBytes.length == 0) {
                return "Profile picture is empty";
            }

            // Retrieve or create a new Profile entity
            Profile profile = profileRepository.findByUserId(user.getId()).orElse(new Profile());
            profile.setUser(user); // Ensure the user is set
            profile.setProfilePic(profilePicBytes); // Set the profile picture in byte array format

            // Save the updated profile
            profileRepository.save(profile);

            return "Profile picture updated successfully";
        } catch (IOException e) {
            return "Error updating profile picture: " + e.getMessage();
        }
    }



    public String getProfilePic () {
            // Get the logged-in user
            User user = getUser(); // Fetch the currently authenticated user

            // Retrieve the user's profile
            Optional<Profile> profileOptional = profileRepository.findByUserId(user.getId());

            if (profileOptional.isPresent() && profileOptional.get().getProfilePic() != null) {
                byte[] profilePicBytes = profileOptional.get().getProfilePic();

                // Convert byte array to Base64
                return  Base64.getEncoder().encodeToString(profilePicBytes);
            }

            return null; // Return null if the profile picture is not found
        }

        // 🔹 Get Full Profile
        public Optional<Profile> getProfile () {
            User user = getUser(); // Get the current logged-in user's ID
            return profileRepository.findByUserId(user.getId());
        }
    }