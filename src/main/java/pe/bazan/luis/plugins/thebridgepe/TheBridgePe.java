package pe.bazan.luis.plugins.thebridgepe;

import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.thebridgepe.configs.ArenasConfig;
import pe.bazan.luis.plugins.thebridgepe.utils.Schematics;

public final class TheBridgePe extends JavaPlugin {
    private static TheBridgePe instance;
    private WorldManager worldManager;
    private ArenasConfig arenasConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.arenasConfig = new ArenasConfig();
        this.worldManager = new WorldManager();
        Schematics.reloadSchematics();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("thebridge").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TheBridgePe getInstance() {
        return instance;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }
}
