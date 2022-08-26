package homeward.plugin.homewardinfobar.compatibilities.provided;

import com.caizi.compatibilities.CompatibilityPlugin;
import homeward.plugin.homewardinfobar.HomewardInfoBar;
import homeward.plugin.homewardinfobar.bossbar.InfoHUD;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftCompatibility extends CompatibilityPlugin<Listener> {

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        HomewardInfoBar.customLogger.send("你打碎了方块 " + event.getBlock(), event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {

        if (HomewardInfoBar.hudManager.getHUDPool().containsKey(event.getPlayer()))
            HomewardInfoBar.hudManager.getHUDPool().get(event.getPlayer()).showMyBossBar((Audience) event.getPlayer());

        else {
            HomewardInfoBar.hudManager.getHUDPool().put(event.getPlayer(), new InfoHUD());
            HomewardInfoBar.hudManager.getHUDPool().get(event.getPlayer()).showMyBossBar((Audience) event.getPlayer());
        }
        String papi = "%player_world_time_12%";
        String s = PlaceholderAPI.setPlaceholders(event.getPlayer(), papi);
        Component newText = Component.text(s);
        HomewardInfoBar.hudManager.getHUDPool().get(event.getPlayer()).getActiveBar().name(newText);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerLeave(PlayerQuitEvent event) {
        HomewardInfoBar.hudManager.getHUDPool().remove(event.getPlayer());
    }


}
