package pe.bazan.luis.plugins.thebridgepe.utils;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import pe.bazan.luis.plugins.thebridgepe.TheBridgePe;

import javax.annotation.Nullable;
import java.io.*;
import java.util.HashMap;

public class Schematics {
    private static HashMap<String, Clipboard> schematics = new HashMap<>();

    public static @Nullable Clipboard loadClipboard(File file) {
        Clipboard clipboard = null;

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        if (format == null) return null;
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();
        }  catch (IOException e) {
            //e.printStackTrace();
        }

        return clipboard;
    }

    public static void reloadSchematics() {
        File schematicsDirectory = new File(TheBridgePe.getInstance().getDataFolder(), "schematics");

        if (!schematicsDirectory.exists()) {
            schematicsDirectory.mkdirs();

            // Load default schematics
            for (String fileName : new String[]{"blue-box.schem", "red-box.schem"}) {
                try {
                    TheBridgePe.getInstance().saveResource("schematics/"+fileName, false);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        for (File file : schematicsDirectory.listFiles()) {
            Clipboard clipboard = loadClipboard(file);
            if (clipboard != null) schematics.put(file.getName().replaceAll("\\.\\w+$", ""), clipboard);
        }
    }

    public static HashMap<String, Clipboard> getSchematics() {
        return schematics;
    }
}
