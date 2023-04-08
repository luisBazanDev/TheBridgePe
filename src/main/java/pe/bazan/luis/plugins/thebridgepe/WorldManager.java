package pe.bazan.luis.plugins.thebridgepe;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {
    private MultiverseCore multiverseCore;
    private List<MultiverseWorld> worlds;

    public WorldManager() {
        this.multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        this.worlds = new ArrayList<>();
        registerWorlds();
    }

    private void registerWorlds() {
        for (MultiverseWorld multiverseWorld : multiverseCore.getMVWorldManager().getMVWorlds()) {
            if (multiverseWorld.getName().startsWith("the-bridge")) worlds.add(multiverseWorld);
        }
    }

    public void resetWorlds() {
        for (MultiverseWorld multiverseWorld : worlds) {
            String name = multiverseWorld.getName();
            multiverseCore.deleteWorld(name);
            multiverseCore.cloneWorld("world", name, "");
        }
    }

    public MultiverseCore getMultiverseCore() {
        return multiverseCore;
    }

    public List<MultiverseWorld> getWorlds() {
        return worlds;
    }
}
