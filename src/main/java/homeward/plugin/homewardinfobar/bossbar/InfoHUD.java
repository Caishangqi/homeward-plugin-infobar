package homeward.plugin.homewardinfobar.bossbar;

import homeward.plugin.homewardinfobar.HomewardInfoBar;
import homeward.plugin.homewardinfobar.hud.component.HUDComponent;
import lombok.NonNull;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class InfoHUD {

    private @Nullable BossBar activeBar;

    public void showMyBossBar(final @NonNull Audience target) {

        final Component name = Component.text("Awesome BossBar");
        // Creates a red boss bar which has no progress and no notches
        final BossBar emptyBar = BossBar.bossBar(name, 0, BossBar.Color.YELLOW, BossBar.Overlay.PROGRESS);
        // Creates a green boss bar which has 50% progress and 10 notches
        final BossBar halfBar = BossBar.bossBar(name, 0.5f, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_10);
        // etc..
        final BossBar fullBar = BossBar.bossBar(name, 1, BossBar.Color.YELLOW, BossBar.Overlay.PROGRESS);

        // Send a bossbar to your audience
        target.showBossBar(emptyBar);

        // Store it locally to be able to hide it manually later
        this.activeBar = emptyBar.addFlag(BossBar.Flag.CREATE_WORLD_FOG);
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
            //TextComponent textComponent = new TextComponent();
            //textComponent.setFont("boss_bar_1");
            //textComponent.setText("ꈈ");
            //Test End

            //fontKey
            Key boss_bar = Key.key("boss_bar_1");
            Key aDefault = Key.key("default");

            //<color:#FFFEFD>

            //negative space
            TextComponent negativeSpace = Component.text("");
            Component negativeSpaceComponent = negativeSpace.font(aDefault).color(TextColor.color(255, 254, 253));

            //black center""
            TextComponent blackCenter = Component.text("ꈁ").color(TextColor.color(255, 254, 253));
            Component backCenterComponent = blackCenter.font(boss_bar);

            //ChatColor.translateAlternateColorCodes('&',"&eꈈ")
            TextComponent blackCenter2 = Component.text("ꈈ").color(TextColor.color(255, 254, 253));
            Component backCenterComponent2 = blackCenter2.font(boss_bar);



            Component newText = backCenterComponent.append(negativeSpaceComponent).append(backCenterComponent2).append(Component.text(s).color(TextColor.color(255, 255, 255)));

            this.activeBar.name(newText);
        }


    }
}
