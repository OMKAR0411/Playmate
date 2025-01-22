// Fetch active games from the backend and display them
document.addEventListener('DOMContentLoaded', function () {
    fetchActiveGames();
});

// Fetch all active games from the backend
function fetchActiveGames() {
    fetch('/api/games')
        .then(response => response.json())
        .then(games => {
            const gamesContainer = document.getElementById('gamesContainer');
            gamesContainer.innerHTML = '';  // Clear existing games list

            // Loop through each game and create a game element
            games.forEach(game => {
                const gameElement = document.createElement('div');
                gameElement.classList.add('game');
                gameElement.innerHTML = `
                    <h3>${game.name}</h3>
                    <img src="data:image/jpeg;base64,${game.base64Image}" alt="${game.name}" class="game-image">
                `;
                gamesContainer.appendChild(gameElement);
            });
        })
        .catch(error => {
            console.error('Error fetching active games:', error);
        });
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