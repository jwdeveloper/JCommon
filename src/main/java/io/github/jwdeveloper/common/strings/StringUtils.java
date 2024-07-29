package io.github.jwdeveloper.common.strings;

public class StringUtils
{
    public static String EMPTY = "";

    public StringUtils() {
    }

    public static boolean isNullOrEmpty(String string) {
        if (string == null) {
            return true;
        } else if (string.equals(EMPTY)) {
            return true;
        } else {
            return string.length() == 0;
        }
    }

    public static boolean isNotNullOrEmpty(String string) {
        return !isNullOrEmpty(string);
    }

    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        } else {
            String var10000 = str.substring(0, 1).toUpperCase();
            return var10000 + str.substring(1);
        }
    }

    public static String deCapitalize(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        } else {
            String var10000 = str.substring(0, 1).toLowerCase();
            return var10000 + str.substring(1);
        }
    }

    public static String removeWhitespaces(String string) {
        return string.replaceAll("\\s", "");
    }

    public static String separator() {
        return System.lineSeparator();
    }
}
