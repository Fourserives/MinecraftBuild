package minecraftbuild.cookforplayer.event;

import minecraftbuild.cookforplayer.CookForPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

//测试前端框架
public class CookingBlock implements Listener {

    private final CookForPlayer plugin;
    private CookingListener cookingListener;

    public CookingBlock(CookForPlayer plugin) {
        this.plugin = plugin;
        this.cookingListener = new CookingListener(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            System.out.println(clickedBlock);
            if (clickedBlock != null && clickedBlock.getType() == Material.LOG) {
                // 右键点击了木头，打开自定义GUI
                openCookingGUI(event.getPlayer());
            }
        }
    }

    private void openCookingGUI(Player player) {
        // 创建一个包含五个格子的GUI界面
        Inventory cookingGUI = Bukkit.createInventory(player, 9, "烹饪界面");

        // 添加四个食材槽
        for (int i = 0; i < 4; i++) {
            cookingGUI.setItem(i, new ItemStack(Material.AIR)); // 初始化为空气块
        }

        // 添加烹饪按钮
        ItemStack cookButton = new ItemStack(Material.BLAZE_ROD);
        ItemMeta buttonMeta = cookButton.getItemMeta();
        buttonMeta.setDisplayName("开始烹饪");
        cookButton.setItemMeta(buttonMeta);

        // 将烹饪按钮放在第四个格子
        cookingGUI.setItem(7, cookButton);

        // 打开GUI界面
        player.openInventory(cookingGUI);
    }

    //核心部分：调用烹饪类
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Player) {
            Player player = (Player) event.getInventory().getHolder();
            if (event.getView().getTitle().equals("烹饪界面")) {
                ItemStack[] cookingMatrix = Arrays.copyOfRange(event.getInventory().getContents(), 0, 4);

                // 调用CookingListener的方法处理点击事件 ——> 开始进行烹饪
                cookingListener.handleInventoryClick(event, player);
            }
        }
    }

}