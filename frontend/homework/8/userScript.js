function display(recipes) {
    const container = document.getElementById("recipeContainer");

    container.innerHTML = "";

    recipes.forEach((recipe) => {
        const tile = document.createElement("div");
        tile.classList.add("tile");
        const image = document.createElement("img");
        image.src = recipe.image;
        const name = document.createElement("h3");
        name.textContent = recipe.name;
        const rating = document.createElement("p");
        rating.classList.add("rating-circle");
        rating.textContent = recipe.rating;
        const ingredients = document.createElement("div");
        ingredients.classList.add("ingredients");
        const ingredientsTitle = document.createElement("p");
        ingredientsTitle.textContent = "Ingredients:";
        const ingredientsList = document.createElement("ul");
        recipe.ingredients.forEach((ingredient) => {
            const li = document.createElement("li");
            li.classList.add("ingredient");
            li.textContent = ingredient;
            ingredientsList.appendChild(li);
        });
        ingredients.appendChild(ingredientsTitle);
        ingredients.appendChild(ingredientsList);

        tile.appendChild(image);
        tile.appendChild(name);
        tile.appendChild(rating);
        tile.appendChild(ingredients);

        const extraDetails = document.createElement("div");
        extraDetails.classList.add("extra-details");

        const cuisine = document.createElement("p");
        cuisine.classList.add("detail");
        cuisine.textContent = recipe.cuisine;
        extraDetails.appendChild(cuisine);

        const difficulty = document.createElement("p");
        difficulty.classList.add("detail");
        difficulty.textContent = recipe.difficulty;
        extraDetails.appendChild(difficulty);

        const timeTaken = document.createElement("p");
        timeTaken.classList.add("detail");
        timeTaken.textContent = recipe.timeTaken + " Mins";
        extraDetails.appendChild(timeTaken);

        const caloriesCount = document.createElement("p");
        caloriesCount.classList.add("detail");
        caloriesCount.textContent = recipe.caloriesCount + " kCal";
        extraDetails.appendChild(caloriesCount);

        tile.appendChild(extraDetails);
        container.appendChild(tile);
    });
}
async function fetchRecipes() {
    const response = await fetch("http://localhost:3000");
    const recipes = await response.json();
    display(recipes);
}

async function searchRecipes() {
    const query = document.getElementById("searchInput").value.trim();
    if (query !== "") {
        const response = await fetch(
            `http://localhost:3000/search?query=${query}`
        );
        const recipes = await response.json();
        display(recipes);
        document.getElementById("searchInput").value = "";
    }
}

window.onload = fetchRecipes;
