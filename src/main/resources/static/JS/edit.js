document.getElementById("editUsernameForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the default form submission

    const username = document.getElementById("username").value.trim(); // Trim spaces

    fetch(`/api/profile/name?name=${encodeURIComponent(username)}`, { // Send as request parameter
        method: 'POST'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to update username");
        }
        return response.json();
    })
    .then(data => {
        alert("Username updated successfully!");
    })
    .catch(error => {
        console.error("Error updating username:", error);
        alert("There was an error updating the username.");
    });
});


// Handle Bio Update
document.getElementById("editBioForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the default form submission

    const bio = document.getElementById("bio").value.trim(); // Trim spaces

    fetch(`/api/profile/bio?bio=${encodeURIComponent(bio)}`, { // Send as request parameter
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        alert("Bio updated successfully!");
    })
    .catch(error => {
        console.error("Error updating bio:", error);
        alert("There was an error updating the bio.");
    });
});

// Handle Games Update
document.getElementById("editGamesForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the default form submission

    const games = document.getElementById("games").value.trim(); // Trim spaces

    fetch(`/api/profile/games?games=${encodeURIComponent(games)}`, { // Send as request parameter
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        alert("Favorite games updated successfully!");
    })
    .catch(error => {
        console.error("Error updating favorite games:", error);
        alert("There was an error updating the favorite games.");
    });
});

// Handle Profile Picture Update
document.getElementById("editProfilePicForm").addEventListener("submit", async function (event) {
    event.preventDefault(); // Prevent default form submission

    const fileInput = document.getElementById("profilePic");

    if (fileInput.files.length === 0) {
        alert("Please select a file!");
        return;
    }

    const formData = new FormData();
    formData.append("profilePic", fileInput.files[0]); // Append the image file

    console.log("FormData Entries:");
    for (let pair of formData.entries()) {
        console.log(pair[0], pair[1]); // Debug: Check if file is appended correctly
    }

    try {
        const response = await fetch("/api/profile/profilePic", { // Ensure correct API endpoint
            method: "POST",
            body: formData
        });

        console.log("Response Status:", response.status);

        if (response.ok) {
            const data = await response.text();
            alert("Success: " + data);
        } else {
            const errorText = await response.text();
            alert("Error: " + errorText);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("There was an error uploading the profile picture.");
    }
});

