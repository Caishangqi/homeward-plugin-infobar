package homeward.plugin.homewardinfobar.hud;

import homeward.plugin.homewardinfobar.bossbar.InfoHUD;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HUDManager {

    public static final HUDManager INSTANCE = new HUDManager();

    private HashMap<Player, InfoHUD> HUDPool = new HashMap<>();

    private HUDManager() {}

    public HashMap<Player, InfoHUD> getHUDPool() {
        return HUDPool;
    }

    public void setHUDPool(HashMap<Player, InfoHUD> HUDPool) {
        this.HUDPool = HUDPool;
    }
}
