package homeward.plugin.homewardinfobar.scheduler;

import homeward.plugin.homewardinfobar.HomewardInfoBar;
import org.bukkit.Bukkit;

public class OnTickScheduler {

    public static final OnTickScheduler INSTANCE = new OnTickScheduler();

    private OnTickScheduler() {

    }

    public void runScheduler() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(HomewardInfoBar.getInstance(), () -> {
            HomewardInfoBar.hudManager.getHUDPool().forEach((K, V) -> {
                V.refreshHUD(K);
            });
        }, 1, 5);
    }


}
