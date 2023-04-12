package pe.bazan.luis.plugins.thebridgepe.instances;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.thebridgepe.game.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaTeam {
    private Color color;
    private Arena arena;
    private Location spawn;
    private List<Player> players = new ArrayList<>();

    public ArenaTeam(Arena arena, Color color, Location spawn) {
        this.arena = arena;
        this.color = color;
        this.spawn = spawn;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Arena getArena() {
        return arena;
    }

    public boolean containsPlayer(Player player) {
        return players.contains(player);
    }
}
