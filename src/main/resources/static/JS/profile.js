// API URLs (Replace with your actual API endpoints)
const nameApi = "/api/profile/name";
const profilePhotoApi = "/api/profile/pic";
const usernameApi = "/api/profile/username";
const bioApi = "/api/profile/bio";
const favGamesApi = "/api/profile/games";

// Function to fetch and update profile data
async function fetchProfileName() {
    try {
        const nameResponse = await fetch(nameApi);
        const nameData = await nameResponse.text();
        document.getElementById("profile-name").innerText = nameData;
    } catch (error) {
        console.error("Error fetching profile data:", error);
    }
}

async function fetchProfileBio() {
    try {
        const BioResponse = await fetch(bioApi);
        const BioData = await BioResponse.text();
        document.getElementById("profile-bio").innerText = BioData;
    } catch (error) {
        console.error("Error fetching profile data:", error);
    }
}

async function fetchProfileFavGame() {
    try {
        const FavResponse = await fetch(favGamesApi);
        const FavData = await FavResponse.text();
        document.getElementById("profile-fav-games").innerText = FavData;
    } catch (error) {
        console.error("Error fetching profile data:", error);
    }
}

async function fetchProfileUserName() {
    try {
        const UserNameResponse = await fetch(usernameApi);
        const UserNameData = await UserNameResponse.text();
        document.getElementById("profile-username").innerText = UserNameData;
    } catch (error) {
        console.error("Error fetching profile data:", error);
    }
}

document.addEventListener("DOMContentLoaded", function() {
    // Fetch profile picture inside DOMContentLoaded to ensure the DOM is loaded
    async function fetchProfilePic() {
        const response = await fetch(profilePhotoApi);
        if (response.ok) {
            const imageUrl = await response.text(); // Get the Base64 string from the response
            const profilePicElement = document.getElementById("profile-photo");

            if (profilePicElement) {  // Ensure the element exists before modifying the src
                profilePicElement.src = `data:image/jpeg;base64,${imageUrl}`;  // Insert the Base64 string into the src attribute
            } else {
                console.error("Profile picture element not found");
            }
        } else {
            console.error("Profile picture not found");
        }
    }

    // Calling the function to fetch and update the profile data
    fetchProfilePic();  // This is inside DOMContentLoaded to ensure the DOM is loaded before calling the function.
    fetchProfileBio();
    fetchProfileUserName();
    fetchProfileFavGame();
    fetchProfileName();
});
