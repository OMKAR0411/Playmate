// Fetch active games from the backend and display them
document.addEventListener('DOMContentLoaded', function () {
    fetchActiveGames();
});
// Fetch all active games from the backend and display them
function fetchActiveGames() {
    fetch('/api/games')
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch games");
            return response.json();
        })
        .then(games => {
            const gamesContainer = document.getElementById('gamesContainer');
            gamesContainer.innerHTML = ''; // Clear the existing games list

            // Loop through each game and dynamically create its element
            games.forEach(game => {
                const gameElement = document.createElement('div');
                gameElement.classList.add('game');
                gameElement.innerHTML = `
                    <h3 class="game-name" style="cursor: pointer;" data-id="${game.id}">${game.name}</h3>
                    <img
                        src="data:image/jpeg;base64,${game.base64Image}"
                        alt="${game.name}"
                        class="game-image"
                        style="cursor: pointer;"
                        data-id="${game.id}"
                    >
                `;

                // Add click event listeners for the game name and image
                gameElement.querySelector('.game-name').addEventListener('click', () => {
                    redirectToCommunity(game.id);
                });

                gameElement.querySelector('.game-image').addEventListener('click', () => {
                    redirectToCommunity(game.id);
                });

                gamesContainer.appendChild(gameElement);
            });
        })
        .catch(error => {
            console.error('Error fetching active games:', error);
            alert('Unable to load games. Please try again later.');
        });
}

// Redirect to the community page for the selected game
function redirectToCommunity(gameId) {
    window.location.href = `community.html?gameId=${gameId}`;
}


// Handle the form submission for creating a new game
document.getElementById('createGameForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent the default form submission

    // Get form data
    const formData = new FormData(this);

    // Send the data to the server using fetch
    fetch('/api/games/create', {
        method: 'POST',
        body: formData,
    })
        .then(response => response.json())
        .then(game => {
            console.log('New game created:', game);
            // After the game is created, fetch and display the updated list of active games
            fetchActiveGames();
        })
        .catch(error => {
            console.error('Error creating new game:', error);
        });

    // Reset the form after submission
    this.reset();
});


// Hide game function
function hide() {
    const gameId = document.getElementById('gameId').value;

    if (!gameId) {
        alert("Please Enter a Valid Game ID");
        return;
    }

    fetch(`/api/games/${gameId}/hide`, {
        method: 'PATCH'
    })
        .then(response => response.json())
        .then(game => {
            console.log('Game hidden:', game);
            // After hiding the game, fetch and display the updated list of active games
            fetchActiveGames();
        })
        .catch(error => {
            console.error('Error hiding the game:', error);
        });
}

// Unhide game function
function unhide() {
    const gameId = document.getElementById('gameId').value;

    if (!gameId) {
        alert("Please Enter a Valid Game ID");
        return;
    }

    fetch(`/api/games/${gameId}/unhide`, {
        method: 'PATCH'
    })
        .then(response => response.json())
        .then(game => {
            console.log('Game unhidden:', game);
            // After un-hiding the game, fetch and display the updated list of active games
            fetchActiveGames();
        })
        .catch(error => {
            console.error('Error un-hiding the game:', error);
        });
}
function logout() {
    fetch("/logout", {
        method: "POST", // POST is typically used for logout in Spring Security
        headers: {
            "Content-Type": "application/json",
        },
    })
    .then(response => {
        if (response.ok) {
            // Redirect to login page after successful logout
            window.location.href = "/login";  // Redirect to login page
        } else {
            alert("Logout failed. Please try again.");
        }
    })
    .catch(error => {
        console.error("Error during logout:", error);
        alert("An error occurred while logging out. Please try again.");
    });
}








