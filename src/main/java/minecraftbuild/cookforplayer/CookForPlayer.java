package minecraftbuild.cookforplayer;

import minecraftbuild.cookforplayer.dao.CookingRecipe;
import minecraftbuild.cookforplayer.dao.CookingTool;
import minecraftbuild.cookforplayer.event.CookingBlock;
import minecraftbuild.cookforplayer.event.CookingListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class CookForPlayer extends JavaPlugin {

    //配方定义
    private final CookingRecipe seafoodSoupRecipe = new CookingRecipe(
            CookingTool.COOKING_POT,
            Arrays.asList("Milk Bucket","Raw Salmon")
    );
    private final List<CookingRecipe> knownRecipes = Arrays.asList(seafoodSoupRecipe);


    @Override
    public void onEnable() {
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new CookingListener(this), this);
        getServer().getPluginManager().registerEvents(new CookingBlock(this), this);

    }

    public List<CookingRecipe> getKnownRecipes() {
        return knownRecipes;
    }
}
