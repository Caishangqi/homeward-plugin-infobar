package homeward.plugin.homewardinfobar.util.hudmanipulation;

import com.caizi.utils.logs.LoggerManipulationType;
import homeward.plugin.homewardinfobar.HomewardInfoBar;
import homeward.plugin.homewardinfobar.bossbar.InfoHUD;
import homeward.plugin.homewardinfobar.hud.ContentType;
import homeward.plugin.homewardinfobar.hud.DisplayPriority;
import homeward.plugin.homewardinfobar.hud.component.HUDComponent;
import homeward.plugin.homewardinfobar.util.config.MainConfiguration;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@UtilityClass
public class HUDManipulation {

    public void displayHUDForPlayer() {
        HashMap<Player, InfoHUD> hudPool = HomewardInfoBar.hudManager.getHUDPool();
        HomewardInfoBar.getInstance().getServer().getOnlinePlayers().forEach(K -> {
            hudPool.put(K, new InfoHUD());
            hudPool.get(K).showMyBossBar((Audience) K);
        });
    }

    public void hiddenDisplayHUDForPlayer() {
        HomewardInfoBar.consoleLogger.send(LoggerManipulationType.INFO, "&7尝试关闭所有在线玩家的HUD");
        try {
            HashMap<Player, InfoHUD> playerInfoHUDHashMap = new HashMap<>(HomewardInfoBar.hudManager.getHUDPool());
            if (playerInfoHUDHashMap.size() >= 1) {
                playerInfoHUDHashMap.forEach((K, V) -> {
                    V.hideActiveBossBar((Audience) K);
                });
            }
            HomewardInfoBar.consoleLogger.send(LoggerManipulationType.UNLOAD, "&7成功关闭所有在线玩家的HUD");
        } catch (Exception exception) {
            HomewardInfoBar.consoleLogger.send(LoggerManipulationType.ERROR, "&7关闭过程中出现错误 原因: &6" + exception.getCause());
        }

    }

    public void loadDisplayPriority() {
        switch (MainConfiguration.getGeneralSettings().getString("display-priority-type", "placeholder")) {
            case "plugin":
                injectPriority("getDisplayPriorityByPlugin", ContentType.PLUGIN);
            case "placeholder":
                injectPriority("getDisplayPriorityByPlaceHolder", ContentType.PLACEHOLDER);
        }
    }


    public void injectPriority(String getDisplayPriorityByWhat, ContentType type) {
        List<String> invoke = null;
        try {
            Method getDisplayPriorityBy = MainConfiguration.class.getDeclaredMethod(getDisplayPriorityByWhat);
            invoke = (List<String>) getDisplayPriorityBy.invoke(null);
        } catch (Exception exception) {
            HomewardInfoBar.consoleLogger.send(LoggerManipulationType.ERROR, "注入加载顺序发生错误 原因: " + exception.getCause());
        }

        List<String> displayPriorityByPlugin = invoke;

        DisplayPriority displayPriority = new DisplayPriority();
        displayPriorityByPlugin.forEach(K -> {
            HUDComponent hudComponent = new HUDComponent();
            hudComponent.setContent(K);
            hudComponent.setType(type);
            displayPriority.getLoadSequence().add(hudComponent);
        });
        HomewardInfoBar.hudManager.setDisplayPriority(displayPriority);


    }


}
