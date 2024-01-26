package minecraftbuild.cookforplayer.event;

import minecraftbuild.cookforplayer.CookForPlayer;
import minecraftbuild.cookforplayer.dao.CookingRecipe;
import minecraftbuild.cookforplayer.dao.CookingTool;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CookingListener implements Listener {

    private CookForPlayer plugin;

    public CookingListener(CookForPlayer plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void handleInventoryClick(InventoryClickEvent event, Player player) {
        // 处理烹饪按钮点击事件
        if (event.getRawSlot() == 7) {

            // 获取玩家放入的烹饪材料
            ItemStack[] cookingMatrix = Arrays.copyOfRange(event.getInventory().getContents(), 0, 4);

            // 查找匹配的烹饪配方
            CookingRecipe matchingRecipe = findMatchingRecipe(cookingMatrix);

            if (matchingRecipe != null) {
                // 执行烹饪逻辑
                handleCooking(player);

                event.setCancelled(true);
                // 关闭GUI界面
                player.closeInventory();
            } else {
                player.sendMessage("未找到匹配的烹饪配方！");
            }
        }
    }

    private CookingRecipe findMatchingRecipe(ItemStack[] cookingMatrix) {
        // 遍历已知的烹饪配方，查找是否有匹配的配方
        for (CookingRecipe recipe : plugin.getKnownRecipes()) {
            if (isRecipeMatch(recipe, cookingMatrix)) {
                return recipe; // 找到匹配的配方，返回该配方对象
            }
        }
        return null;
    }

    private boolean isRecipeMatch(CookingRecipe recipe, ItemStack[] cookingMatrix) {
        // 获取配方所需的原料列表
        List<String> requiredIngredients = recipe.getIngredients();

        // 检查配方所需的原料是否与玩家放入的原料匹配
        for (String requiredIngredient : requiredIngredients) {
            boolean ingredientFound = false;

            // 遍历玩家放入的原料
            for (ItemStack itemStack : cookingMatrix) {
                if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
                    String ingredientDisplayName = itemStack.getItemMeta().getDisplayName();
                    // 检查原料是否匹配
                    if (requiredIngredient.equals(ingredientDisplayName)) {
                        ingredientFound = true;
                        break;
                    }
                }
            }
            // 如果配方所需的原料未在玩家放入的原料中找到，则返回 false
            if (!ingredientFound) {
                return false;
            }
        }

        // 所有配方所需的原料都匹配
        return true;
    }

    /*
        后续连接腐化值相关内容，最终的烹饪材料自定义单独一个dao
     */
    //烹饪完成
    private void handleCooking(Player player) {
        player.sendMessage("成功制作了烹饪食物！");
        player.getInventory().addItem(/* 烹饪完成后的物品 */);
    }
}