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
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class HUDComponent {

    private String content;
    private ContentType type;

    private Integer priority;

    private Component serializedContent;

    public boolean canParse(Player player) {
        String papi = content;
        String s = PlaceholderAPI.setPlaceholders(player, papi);
        return (!papi.equals(s) && !s.isBlank());
    }

    public Component render(Player player) {

        serializedContent = MiniMessage.miniMessage().deserialize(content);
        TextComponent serializedContentText = (TextComponent) serializedContent;
        //System.out.println(serializedContentText.content()); //%xxx%



        String placeholders = PlaceholderAPI.setPlaceholders(player, content);

        int totalWidth = FontWidth.getTotalWidth(placeholders);

        String shortestNegChars = FontNegative.getShortestNegChars(totalWidth);
        //String backGround = FontBackGround.getBackGround(totalWidth);


        Key boss_bar = Key.key("boss_bar_1");
        Key aDefault = Key.key("default");

        Component backGround = FontBackGround.getBackGround(totalWidth, boss_bar, aDefault);
        backGround = backGround.color(TextColor.color(255, 254, 253));


        TextComponent placeholderText = Component.text(placeholders);
        placeholderText = placeholderText.color(TextColor.color(255, 255, 255));
        return backGround.append(placeholderText);

        //return Component.text(placeholders).append(negativeComponentWithFont).append(backGround);

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
