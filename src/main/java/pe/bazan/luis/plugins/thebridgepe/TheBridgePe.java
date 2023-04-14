package pe.bazan.luis.plugins.thebridgepe;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pe.bazan.luis.plugins.thebridgepe.configs.ArenasConfig;
import pe.bazan.luis.plugins.thebridgepe.instances.TheBridgeGenerator;
import pe.bazan.luis.plugins.thebridgepe.utils.Schematics;

public final class TheBridgePe extends JavaPlugin {
    private static TheBridgePe instance;
    private WorldManager worldManager;
    private ArenasConfig arenasConfig;
    private GameManager currentGame;

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
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new TheBridgeGenerator();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getScheduler().cancelTasks(this);
    }

    public static TheBridgePe getInstance() {
        return instance;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void setCurrentGame(GameManager currentGame) {
        this.currentGame = currentGame;
    }

    public ArenasConfig getArenasConfig() {
        return arenasConfig;
    }

    public GameManager getCurrentGame() {
        return currentGame;
    }
}
