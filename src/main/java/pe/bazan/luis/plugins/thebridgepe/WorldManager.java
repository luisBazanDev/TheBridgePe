package pe.bazan.luis.plugins.thebridgepe;

import com.onarandombox.MultiverseCore.MVWorld;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {
    private MultiverseCore multiverseCore;
    private List<MultiverseWorld> worlds;
    private List<MultiverseWorld> freeWorlds;

    public WorldManager() {
        this.multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        this.worlds = new ArrayList<>();
        registerWorlds();
    }

    private void registerWorlds() {
        World world = Bukkit.getWorld("the-bridge");

        if (world == null) {
            multiverseCore.getMVWorldManager().addWorld("the-bridge", World.Environment.NORMAL, "seed", WorldType.FLAT, false, "TheBridgePe");
        }

        for (MultiverseWorld multiverseWorld : multiverseCore.getMVWorldManager().getMVWorlds()) {
            if (multiverseWorld.getName().startsWith("the-bridge")) worlds.add(multiverseWorld);
        }
    }

    public void resetWorlds() {
        for (MultiverseWorld multiverseWorld : worlds) {
            String name = multiverseWorld.getName();
            multiverseCore.deleteWorld(name);
            multiverseCore.cloneWorld("the-bridge", name, "TheBridgePe");
        }
        freeWorlds = worlds;
    }

    public MultiverseCore getMultiverseCore() {
        return multiverseCore;
    }

    public List<MultiverseWorld> getWorlds() {
        return worlds;
    }

    public MultiverseWorld getFreeWorld() {
        if (freeWorlds.size() >= 1) return freeWorlds.remove(0);
        else return generateWorld();
    }

    public MultiverseWorld generateWorld() {
        String worldName = String.format("the-bridge-%s", worlds.size());
        multiverseCore.cloneWorld("the-bridge", worldName, "TheBridgePe");
        MultiverseWorld world = multiverseCore.getMVWorldManager().getMVWorld(worldName);
        if (world != null) {
            worlds.add(world);
            return world;
        }
        return null;
    }
}
