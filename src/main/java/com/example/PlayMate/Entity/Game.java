package com.example.PlayMate.Entity;

import jakarta.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob// Annotation to store large binary data
    @Column(columnDefinition = "LONGBLOB") // Force the column type to be LONGBLOB
    private byte[] image; // Field for storing the image as a binary object

    private boolean isActive = true; // Field to mark the game as active or hidden (default is active)

    @Transient  // This field will not be persisted in the database
    private String base64Image; // Field for Base64 image representation

    public Game() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
