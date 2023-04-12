package pe.bazan.luis.plugins.thebridgepe.configs;

import org.bukkit.configuration.ConfigurationSection;
import pe.bazan.luis.plugins.thebridgepe.TheBridgePe;

public class ArenasConfig {
    private CustomConfig customConfig;

    public ArenasConfig() {
        customConfig = new CustomConfig(TheBridgePe.getInstance(), "arenasConfig.yml");
    }

    public ConfigurationSection getDefaultConfiguration() {
        return getSection("the-bridge");
    }

    public CustomConfig getCustomConfig() {
        return customConfig;
    }

    public ConfigurationSection getSection(String path) {
        return customConfig.getConfig().getConfigurationSection(path);
    }
}
