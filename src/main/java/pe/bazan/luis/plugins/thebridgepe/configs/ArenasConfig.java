package pe.bazan.luis.plugins.thebridgepe.configs;

import pe.bazan.luis.plugins.thebridgepe.TheBridgePe;

public class ArenasConfig {
    private CustomConfig customConfig;

    public ArenasConfig() {
        customConfig = new CustomConfig(TheBridgePe.getInstance(), "arenasConfig.yml");
    }

    public CustomConfig getCustomConfig() {
        return customConfig;
    }
}
