package homeward.plugin.homewardinfobar.hud.component;

import homeward.plugin.homewardinfobar.hud.ContentType;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class HUDComponent {

    private String content;
    private ContentType type;

    public boolean canParse(Player player) {
        String papi = content;
        String s = PlaceholderAPI.setPlaceholders(player, papi);
        return (!papi.equals(s) && !s.isBlank());
    }

    public Component render() {
        return Component.text(this.content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }
}
