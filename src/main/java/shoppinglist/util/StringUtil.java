package shoppinglist.util;

public class StringUtil {
    private StringUtil() {}

    public static boolean isStringAnInteger(String string) {
        return string.matches("[0-9]*");
    }
}
