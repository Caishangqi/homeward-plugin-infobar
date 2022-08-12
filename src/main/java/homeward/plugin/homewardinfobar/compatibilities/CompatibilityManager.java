package homeward.plugin.homewardinfobar.compatibilities;

import com.caizi.utils.logs.ConsoleLogger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class CompatibilityManager extends com.caizi.compatibilities.CompatibilityManager {


    public CompatibilityManager(Plugin plugin, ConsoleLogger logger, Class<? extends Listener> clazz) {
        super(plugin, logger, clazz);
    }

    @Override
    protected void addUnloadedCompatibilityList() {
        Arrays.stream(CompatibilityList.values()).toList().forEach(K -> {
            if (Bukkit.getPluginManager().isPluginEnabled(K.getPluginName()) && K.getNative()) {
                UNLOADED_COMPATIBILITY.put(K.getPluginName(), K.getCompatibilityPlugin());

            }
        });
    }
}
