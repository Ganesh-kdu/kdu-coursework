import express, { Express, Request, Response } from "express";
import cors from "cors";

const app :Express = express();

app.use(express.json());
app.use(cors());

interface IResponseRecipe{
    id:string,
    name:string,
    ingredients:string[],
    instructions:string[],
    prepTimeMinutes:number,
    cookTimeMinutes:number,
    servings:number,
    difficulty:string,
    cuisine:string,
    caloriesPerServing:number,
    tags:string[],
    userId:number,
    image:string,
    rating:number,
    reviewCount:number,
    mealType:string[]
}

interface IRecipe{
    image:string,
    name:string,
    rating:number
    cuisine:string,
    ingredients:string[],
    difficulty:string,
    timeTaken:number,
    caloriesCount:number,
}
let recipes:IRecipe[];

function mapper(responseRecipes:IResponseRecipe[]){
    return responseRecipes.reduce<IRecipe[]>((reducedRecipe:IRecipe[], recipe:IResponseRecipe)=>{
        let newRecipe:IRecipe = {
            image:recipe.image,
            name:recipe.name,
            rating:recipe.rating,
            cuisine:recipe.cuisine,
            ingredients:recipe.ingredients,
            difficulty:recipe.difficulty,
            timeTaken:recipe.cookTimeMinutes+recipe.prepTimeMinutes,
            caloriesCount:recipe.caloriesPerServing,
        };
        reducedRecipe.push(newRecipe);
        return reducedRecipe;
    },  []);
}
async function fetchRecipesFromAPI() {
    const response = await fetch("https://dummyjson.com/recipes");
    const result = await response.json();
    let responseRecipes:IResponseRecipe[] = result.recipes;
    recipes = mapper(responseRecipes);
}

async function searchRecipes(query:string) {
    query = query.trim().replaceAll(" ","-");
    const response = await fetch(`https://dummyjson.com/recipes/search?q=${query}`);
    const result = await response.json();
    let responseRecipes:IResponseRecipe[] = result.recipes;
    return mapper(responseRecipes);
}
function printAllRecipes(){
    console.log(recipes);
}

app.get("/", (req: Request, res: Response) => {
    (async () => {
        await fetchRecipesFromAPI()
        res.send(recipes);
    })();
  });

app.get("/search?:query", (req: Request, res: Response) => {
    (async () => {
        res.send(await searchRecipes(req.params.query));
    })();    
  });

app.listen(3000, () => {
    console.log(`[server]: Server is running at http://localhost:3000`);
  }); 