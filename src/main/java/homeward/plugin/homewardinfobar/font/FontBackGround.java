package homeward.plugin.homewardinfobar.font;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public enum FontBackGround {

    END('\ua201'),
    CENTER_1('\ua202'),
    CENTER_2('\ua203'),
    CENTER_4('\ua204'),
    CENTER_8('\ua205'),
    CENTER_16('\ua206'),
    CENTER_32('\ua207'),
    CENTER_64('\ua208'),
    CENTER_128('\ua209');


    private final char character;
    private static final int offset_x = 8;

    FontBackGround(char character) {
        this.character = character;
    }


    public static String getBackGround(int n) {
        n += offset_x;
        String offset = FontNegative.getShortestNegChars(n);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(END.character);
        if (n > 128) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_128.character);
            n -= 128;
        }
        if (n - 64 > 0) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_64.character);
            n -= 64;
        }
        if (n - 32 > 0) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_32.character);
            n -= 32;
        }
        if (n - 16 > 0) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_16.character);
            n -= 16;
        }
        if (n - 8 > 0) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_8.character);
            n -= 8;
        }
        if (n - 4 > 0) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_4.character);
            n -= 4;
        }
        if (n - 2 > 0) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_2.character);
            n -= 2;
        }
        if (n - 1 > 0) {
            stringBuilder.append(FontNegative.NEG_1.getCharacter());
            stringBuilder.append(CENTER_1.character);
        }
        stringBuilder.append(FontNegative.NEG_1.getCharacter());
        stringBuilder.append(END.character).append(offset);
        return stringBuilder.toString();
    }

    public static Component getBackGround(int n, Key font, Key defaultFont) {

        n += offset_x;

        TextComponent offset = Component.text(FontNegative.getShortestNegChars(n));
        Component offsetComponent = offset.font(defaultFont);

        //initial
        TextComponent text = Component.text(END.character);
        Component component = text.font(font);

        if (n > 128) {

            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_128, font, defaultFont);

            n -= 128;
        }
        if (n - 64 > 0) {
            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_64, font, defaultFont);
            n -= 64;
        }
        if (n - 32 > 0) {
            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_32, font, defaultFont);
            n -= 32;
        }
        if (n - 16 > 0) {
            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_16, font, defaultFont);
            n -= 16;
        }
        if (n - 8 > 0) {
            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_8, font, defaultFont);
            n -= 8;
        }
        if (n - 4 > 0) {
            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_4, font, defaultFont);
            n -= 4;
        }
        if (n - 2 > 0) {
            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_2, font, defaultFont);
            n -= 2;
        }
        if (n - 1 > 0) {
            component = backGroundSplicing(component, FontNegative.NEG_1, CENTER_1, font, defaultFont);

//            TextComponent textComponentNegative = Component.text(FontNegative.NEG_1.getCharacter());
//            Component textComponentNegativeComponent = textComponentNegative.font(defaultFont);
//
//            TextComponent textComponentCenter = Component.text(CENTER_1.character);
//            Component textComponentCenterComponent = textComponentCenter.font(font);
//
//            component = component.append(textComponentNegativeComponent).append(textComponentCenterComponent);
        }

        TextComponent textComponentNegative = Component.text(FontNegative.NEG_1.getCharacter());
        Component textComponentNegativeComponent = textComponentNegative.font(defaultFont);

        TextComponent finalText = Component.text(END.character);
        Component finalComponent = finalText.font(font);

        component = component.append(textComponentNegativeComponent).append(finalComponent).append(offsetComponent);


        return component;
    }

    private static Component backGroundSplicing(Component component, FontNegative fontNegative, FontBackGround fontBackGround, Key font, Key defaultFont) {
        TextComponent textComponentNegative = Component.text(fontNegative.getCharacter());
        Component textComponentNegativeComponent = textComponentNegative.font(defaultFont);
        TextComponent textComponentCenter = Component.text(fontBackGround.character);
        Component textComponentCenterComponent = textComponentCenter.font(font);
        return component.append(textComponentNegativeComponent).append(textComponentCenterComponent);
    }


}
