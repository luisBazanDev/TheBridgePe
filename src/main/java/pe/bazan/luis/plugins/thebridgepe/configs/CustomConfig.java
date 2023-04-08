package pe.bazan.luis.plugins.thebridgepe.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CustomConfig {
    private final File configFile;
    private FileConfiguration config;

    public CustomConfig(JavaPlugin plugin, String filename) {
        configFile = new File(plugin.getDataFolder(), filename);
        reload();
    }

    public void reload() {
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(configFile);

        try (InputStream defaultStream = getClass().getResourceAsStream("/" + configFile.getName())) {
            if (defaultStream != null && config.getKeys(true).isEmpty()) {
                config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream, StandardCharsets.UTF_8)));
                config.options().copyDefaults(true);
                save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}