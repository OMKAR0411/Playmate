document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.getElementById("registerForm");

    registerForm.addEventListener("submit", async (event) => {
        event.preventDefault(); // Prevent the default form submission

        // Get the values from the form inputs
        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();
        const roles = document.getElementById("roles").value.trim();

        // Create a user object with the input values
        const user = { username, password, roles };

        try {
            // Disable the button to prevent multiple submissions
            const registerButton = document.querySelector("input[type='submit']");
            registerButton.disabled = true;
            registerButton.value = "Registering...";

            // Send the POST request to the backend API
            const response = await fetch("/api/users/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(user),
            });

            // Handle the response from the server
            if (response.ok) {
                const result = await response.text();
                alert("Success: " + result); // Display success message
                registerForm.reset(); // Clear the form inputs
            } else {
                const error = await response.text();
                alert("Error: " + error); // Display error message
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while registering. Please try again.");
        } finally {
            // Re-enable the button and reset its text
            const registerButton = document.querySelector("input[type='submit']");
            registerButton.disabled = false;
            registerButton.value = "Register";
        }
    });
});
