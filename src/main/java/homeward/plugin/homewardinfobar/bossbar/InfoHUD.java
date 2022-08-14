package homeward.plugin.homewardinfobar.bossbar;

import homeward.plugin.homewardinfobar.HomewardInfoBar;
import homeward.plugin.homewardinfobar.hud.component.HUDComponent;
import lombok.NonNull;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class InfoHUD {

    private @Nullable BossBar activeBar;

    public void showMyBossBar(final @NonNull Audience target) {

        final Component name = Component.text("Awesome BossBar");
        // Creates a red boss bar which has no progress and no notches
        final BossBar emptyBar = BossBar.bossBar(name, 0, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        // Creates a green boss bar which has 50% progress and 10 notches
        final BossBar halfBar = BossBar.bossBar(name, 0.5f, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_10);
        // etc..
        final BossBar fullBar = BossBar.bossBar(name, 1, BossBar.Color.YELLOW, BossBar.Overlay.PROGRESS);

        // Send a bossbar to your audience
        target.showBossBar(fullBar);

        // Store it locally to be able to hide it manually later
        this.activeBar = fullBar;
    }

    public void hideActiveBossBar(final @NonNull Audience target) {
        target.hideBossBar(this.activeBar);
        this.activeBar = null;
    }

    public BossBar getActiveBar() {
        return activeBar;
    }

    public void setActiveBar(BossBar activeBar) {
        this.activeBar = activeBar;
    }

    public void refreshHUD(Player player) {
        HUDComponent componentToShow = null;

        for (HUDComponent component : HomewardInfoBar.hudManager.getDisplayPriority().getLoadSequence()) {
            if (component.canParse(player)) {
                componentToShow = component;
                break;
            }
        }

        if (componentToShow != null) {
            String papi = componentToShow.getContent() + "  " + "%player_world_time_12%";
            String s = PlaceholderAPI.setPlaceholders(player, papi);

            //Test Begin
            TextComponent textComponent = new TextComponent();
            textComponent.setFont("boss_bar_1");
            textComponent.setText("ꈈ");
            //Test End

            Component newText = Component.text(s).append(Component.text("ꈈ"));
            Key boss_bar_1 = Key.key("boss_bar_1");

            this.activeBar.name(newText.font(boss_bar_1));
        }


    }
}
