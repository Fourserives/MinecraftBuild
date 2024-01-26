package minecraftbuild.cookforplayer.dao;

import java.util.List;

//配方类
public class CookingRecipe {

    //对应的烹饪工具
    private final CookingTool cookingTool;
    //对应的烹饪材料
    private final List<String> ingredients;

    public CookingRecipe(CookingTool cookingTool, List<String> ingredients) {
        this.cookingTool = cookingTool;
        this.ingredients = ingredients;
    }

    public CookingTool getCookingTool() {
        return cookingTool;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}