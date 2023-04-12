package pe.bazan.luis.plugins.thebridgepe.game;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Color;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.thebridgepe.instances.ArenaTeam;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;

public class Arena {
    final private HashMap<Color, ArenaTeam> teams;
    final private MultiverseWorld world;
    final private int minX, minY, minZ, maxX, maxY, maxZ;

    public Arena(MultiverseWorld world, ConfigurationSection config) {
        this.world = world;
        this.teams = new HashMap<>();
        this.minX = config.getIntegerList("build-box.min").get(0);
        this.minY = config.getIntegerList("build-box.min").get(1);
        this.minZ = config.getIntegerList("build-box.min").get(2);
        this.maxX = config.getIntegerList("build-box.max").get(0);
        this.maxY = config.getIntegerList("build-box.max").get(1);
        this.maxZ = config.getIntegerList("build-box.max").get(2);
        configWorld();
        configTeams(config);
    }

    private void configTeams(ConfigurationSection config) {
        Location blueSpawn = new Location(
                world.getCBWorld(),
                config.getDouble("blue-spawn.x"),
                config.getDouble("blue-spawn.y"),
                config.getDouble("blue-spawn.z"),
                (float) config.getDouble("blue-spawn.yaw"),
                (float) config.getDouble("blue-spawn.pitch")
        );
        Location redSpawn = new Location(
                world.getCBWorld(),
                config.getDouble("red-spawn.x"),
                config.getDouble("red-spawn.y"),
                config.getDouble("red-spawn.z"),
                (float) config.getDouble("red-spawn.yaw"),
                (float) config.getDouble("red-spawn.pitch")
        );

        this.teams.put(Color.BLUE, new ArenaTeam(this, Color.BLUE, blueSpawn));
        this.teams.put(Color.RED, new ArenaTeam(this, Color.RED, redSpawn));
    }

    private void configWorld() {
        // Environment config
        this.world.setTime("6000");
        this.world.setEnableWeather(false);
        this.world.setAutoLoad(false);
        this.world.setDifficulty(Difficulty.NORMAL);

        // Player config
        this.world.setHunger(true);
        this.world.setPVPMode(true);
        this.world.setAllowFlight(false);
        this.world.setGameMode(GameMode.SURVIVAL);

        // Entity spawn
        this.world.setAllowAnimalSpawn(false);
        this.world.setAllowMonsterSpawn(false);
    }

    public boolean inBuildBox(Location loc) {
        if (loc.getX() > maxX || loc.getX() < minX) return false;
        if (loc.getY() > maxY || loc.getY() < minY) return false;
        if (loc.getZ() > maxZ || loc.getZ() < minZ) return false;
        return true;
    }

    public void setPlayers(Color color, Player[] players) {
        ArenaTeam team = teams.get(color);
        if (team != null)
            team.setPlayers(Arrays.asList(players));
    }

    public @Nullable ArenaTeam team(Color color) {
        return teams.get(color);
    }

    public boolean containsPlayer(Player player) {
        return teams.get(Color.BLUE).containsPlayer(player)
                || teams.get(Color.RED).containsPlayer(player);
    }
}
