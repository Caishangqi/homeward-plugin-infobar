package homeward.plugin.homewardinfobar.util.config;

import homeward.plugin.homewardinfobar.HomewardInfoBar;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

@UtilityClass
public class MainConfiguration {

    public ConfigurationSection getGeneralSettings() {
        return HomewardInfoBar.config.getConfigurationSection("general-setting");
    }

    public List<?> getDisplayPriorityByPlugin() {
        return HomewardInfoBar.config.getList("display-priority-by-plugin");
    }

    public List<?> getDisplayPriorityByPlaceHolder() {
        return HomewardInfoBar.config.getList("display-priority-by-placeholder");
    }


}
