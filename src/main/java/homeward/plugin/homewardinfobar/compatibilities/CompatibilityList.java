package homeward.plugin.homewardinfobar.compatibilities;

import com.caizi.compatibilities.enumerate.BaseCompatiblePlugin;
import homeward.plugin.homewardinfobar.compatibilities.provided.MinecraftCompatibility;
import org.bukkit.event.Listener;

public enum CompatibilityList implements BaseCompatiblePlugin {

    MINECRAFT("Minecraft", true, MinecraftCompatibility.class);

    private final Boolean isNative;
    private String pluginName;
    private Class<? extends Listener> compatibilityPlugin;

    /**
     * 兼容列表枚举类的信息，如果兼容插件有自己的事件或者
     * 该插件使用的他的API，则需要把 {@link #isNative} 设置成 true
     *
     * @param pluginName
     * @param isNative
     * @param compatibilityPlugin
     */
    CompatibilityList(String pluginName, Boolean isNative, Class<? extends Listener> compatibilityPlugin) {
        this.pluginName = pluginName;
        this.isNative = isNative;
        this.compatibilityPlugin = compatibilityPlugin;
    }

    CompatibilityList(String pluginName, Boolean isNative) {
        this.pluginName = pluginName;
        this.isNative = isNative;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;

    }

    public boolean getNative() {
        return isNative;
    }

    public Class<? extends Listener> getCompatibilityPlugin() {
        return compatibilityPlugin;
    }
}
