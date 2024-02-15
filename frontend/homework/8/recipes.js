"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const cors_1 = __importDefault(require("cors"));
const app = (0, express_1.default)();
app.use(express_1.default.json());
app.use((0, cors_1.default)());
let recipes;
function mapper(responseRecipes) {
    return responseRecipes.reduce((reducedRecipe, recipe) => {
        let newRecipe = {
            image: recipe.image,
            name: recipe.name,
            rating: recipe.rating,
            cuisine: recipe.cuisine,
            ingredients: recipe.ingredients,
            difficulty: recipe.difficulty,
            timeTaken: recipe.cookTimeMinutes + recipe.prepTimeMinutes,
            caloriesCount: recipe.caloriesPerServing,
        };
        reducedRecipe.push(newRecipe);
        return reducedRecipe;
    }, []);
}
async function fetchRecipesFromAPI() {
    const response = await fetch("https://dummyjson.com/recipes");
    const result = await response.json();
    let responseRecipes = result.recipes;
    recipes = mapper(responseRecipes);
}
async function searchRecipes(query) {
    query = query.trim().replaceAll(" ", "-");
    const response = await fetch(`https://dummyjson.com/recipes/search?q=${query}`);
    const result = await response.json();
    let responseRecipes = result.recipes;
    return mapper(responseRecipes);
}
function printAllRecipes() {
    console.log(recipes);
}
app.get("/", (req, res) => {
    (async () => {
        await fetchRecipesFromAPI();
        res.send(recipes);
    })();
});
app.get("/search?:query", (req, res) => {
    (async () => {
        res.send(await searchRecipes(req.params.query));
    })();
});
app.listen(3000, () => {
    console.log(`[server]: Server is running at http://localhost:3000`);
});
