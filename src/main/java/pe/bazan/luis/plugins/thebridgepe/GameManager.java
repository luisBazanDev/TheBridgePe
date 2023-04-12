package pe.bazan.luis.plugins.thebridgepe;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.thebridgepe.game.Arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameManager {
    private List<Arena> arenas;
    private List<Player> players;
    private List<MultiverseWorld> freeWorlds;
    private int playersPerTeam;

    public GameManager(int arenas, List<Player> players, int playersPerTeam) {
        this.players = players;
        this.playersPerTeam = playersPerTeam;

        preload();
    }

    public void preload() {
        // Randomize teams
        List<Player> lostPlayers = players;
        Random random = new Random();

        for (int i = 0; i < players.size() / (playersPerTeam * 2); i++) {
            Arena arena = new Arena(freeWorlds.remove(0), TheBridgePe.getInstance().getArenasConfig().getDefaultConfiguration());
            List<Player> blueTeam = new ArrayList<>();
            List<Player> redTeam = new ArrayList<>();
            for (int j = 1; j <= playersPerTeam * 2; j++) {
                if (j % 2 == 0) blueTeam.add(lostPlayers.remove(random.nextInt(lostPlayers.size())));
                else redTeam.add(lostPlayers.remove(random.nextInt(lostPlayers.size())));
            }

            arena.setPlayers(Color.BLUE, (Player[]) blueTeam.toArray());
            arena.setPlayers(Color.RED, (Player[]) redTeam.toArray());
            arenas.add(arena);
        }
    }
}
