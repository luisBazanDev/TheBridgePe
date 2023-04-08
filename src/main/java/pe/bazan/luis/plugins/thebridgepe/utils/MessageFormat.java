package pe.bazan.luis.plugins.thebridgepe.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

public class MessageFormat {
    public static String formatColors(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static Component formatMC(String text) {
        return Component.text(formatColors(text));
    }
}
