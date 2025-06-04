package org.plugin.testPlugin2.managers;

public class Color {
    public static final Color instance = new Color();
    public String convert(String text) {
        return text.replaceAll("&", "§");
    }
}
