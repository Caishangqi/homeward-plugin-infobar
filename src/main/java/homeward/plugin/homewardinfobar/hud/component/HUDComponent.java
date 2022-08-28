package homeward.plugin.homewardinfobar.hud.component;

import homeward.plugin.homewardinfobar.font.FontBackGround;
import homeward.plugin.homewardinfobar.font.FontWidth;
import homeward.plugin.homewardinfobar.hud.ContentType;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.entity.Player;

public class HUDComponent {

    private String content;
    private ContentType type;

    private Integer priority;

    private Component serializedContent;

    public boolean canParse(Player player) {
        TextComponent deserialize = (TextComponent) MiniMessage.miniMessage().deserialize(content);
        String s = PlaceholderAPI.setPlaceholders(player, deserialize.content());

        //这个复杂逻辑基于需要给papi加上图标设计的，以及papi空值返回 "" 的特性设计的
        if (PlaceholderAPI.containsPlaceholders(s)) { //这代表根本就没有
            return false;
        } else if (deserialize.content().equals(s)) { //代表重复
            return false;
        } else return !deserialize.content().contains(s);

    }

    public Component render(Player player) {

        serializedContent = MiniMessage.miniMessage().deserialize(content);
        serializedContent = serializedContent.applyFallbackStyle(Style.style(TextColor.color(255, 255, 255)));
        //这个是没有字体<>的
        TextComponent serializedContentText = (TextComponent) serializedContent;

        //这个包含了其他的格式
        String placeholders_0 = PlaceholderAPI.setPlaceholders(player, content);
        Component component = MiniMessage.miniMessage().deserialize(placeholders_0);
        //System.out.println(content);


        //这个是算阴影的
        String placeholders = PlaceholderAPI.setPlaceholders(player, serializedContentText.content());

        int totalWidth = FontWidth.getTotalWidth(placeholders);

        Key boss_bar = Key.key("boss_bar_1");
        Key aDefault = Key.key("default");

        Component backGround = FontBackGround.getBackGround(totalWidth, boss_bar, aDefault);
        backGround = backGround.color(TextColor.color(255, 254, 253));

        Component test = Component.text("뀂").font(boss_bar).color(TextColor.color(255, 254, 253));
        test = test.append(Component.text("%worldguard_region_name%").font(aDefault).color(TextColor.color(255, 255, 255)));
        final String json = GsonComponentSerializer.gson().serialize(test);

        //System.out.println(json);
        return backGround.append(component);

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
