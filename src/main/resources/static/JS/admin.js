
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

    // Reset the form after s ubmission
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