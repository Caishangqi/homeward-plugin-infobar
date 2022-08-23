package homeward.plugin.homewardinfobar.font;

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
    private static final int offset_x = 4;

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
}
