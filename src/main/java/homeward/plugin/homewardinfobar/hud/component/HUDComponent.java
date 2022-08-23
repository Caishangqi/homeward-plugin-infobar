package homeward.plugin.homewardinfobar.hud.component;

import homeward.plugin.homewardinfobar.font.FontBackGround;
import homeward.plugin.homewardinfobar.font.FontNegative;
import homeward.plugin.homewardinfobar.font.FontWidth;
import homeward.plugin.homewardinfobar.hud.ContentType;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class HUDComponent {

    private String content;
    private ContentType type;

    public boolean canParse(Player player) {
        String papi = content;
        String s = PlaceholderAPI.setPlaceholders(player, papi);
        return (!papi.equals(s) && !s.isBlank());
    }

    public Component render(Player player) {

        String placeholders = PlaceholderAPI.setPlaceholders(player, content);

        int totalWidth = FontWidth.getTotalWidth(placeholders);

        String shortestNegChars = FontNegative.getShortestNegChars(totalWidth);
        String backGround = FontBackGround.getBackGround(totalWidth);

        Key boss_bar = Key.key("boss_bar_1");
        Key aDefault = Key.key("default");

        TextComponent backGroundComponent = Component.text(backGround).color(TextColor.color(255, 254, 253));
        Component backGroundComponentWithFont = backGroundComponent.font(boss_bar);


        TextComponent negativeComponent = Component.text(shortestNegChars);
        Component negativeComponentWithFont = negativeComponent.font(aDefault);

        return Component.text(placeholders).append(negativeComponentWithFont).append(backGroundComponentWithFont);

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
