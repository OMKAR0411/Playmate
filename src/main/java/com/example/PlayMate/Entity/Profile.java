package com.example.PlayMate.Entity;

import jakarta.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bio;

    @Lob // Large object for storing profile picture
    @Column(columnDefinition = "LONGBLOB") // Force the column type to be LONGBLOB
    private byte[] profilePic;

    private String games;

    @Transient  // This field will not be persisted in the database
    private String base64Image; // Field for Base64 image representation

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;  // One-to-One Mapping with User

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public byte[] getProfilePic() { return profilePic; }
    public void setProfilePic(byte[] profilePic) { this.profilePic = profilePic; }

    public String getGames() { return games; }
    public void setGames(String games) { this.games = games; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
