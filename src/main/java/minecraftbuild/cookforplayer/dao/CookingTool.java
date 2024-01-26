package minecraftbuild.cookforplayer.dao;

//烹饪工具
public enum CookingTool {
    COOKING_POT(1);
    // 可以添加更多的烹饪工具

    private final int toolId;

    CookingTool(int toolId) {
        this.toolId = toolId;
    }

    public int getToolId() {
        return toolId;
    }
}