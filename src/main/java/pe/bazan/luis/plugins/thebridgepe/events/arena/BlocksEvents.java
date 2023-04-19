package pe.bazan.luis.plugins.thebridgepe.events.arena;

import com.sk89q.worldedit.event.platform.BlockInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pe.bazan.luis.plugins.thebridgepe.GameManager;
import pe.bazan.luis.plugins.thebridgepe.TheBridgePe;

public class BlocksEvents implements Listener {
    public void onPLayerPlaceBlock(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        GameManager gameManager = TheBridgePe.getInstance().getCurrentGame();
        if (
                gameManager == null
            || gameManager.getArenaFromPlayer(player) == null
        ) return;

        if (!gameManager.getArenaFromPlayer(player).inBuildBox(e.getBlock().getLocation()))
            e.setCancelled(true);
    }

    public void onPlayerBreakeBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        GameManager gameManager = TheBridgePe.getInstance().getCurrentGame();
        if (
                gameManager == null
                        || gameManager.getArenaFromPlayer(player) == null
        ) return;

        if (!gameManager.getArenaFromPlayer(player).inBuildBox(e.getBlock().getLocation()))
            e.setCancelled(true);
    }

    public void onPlayerInteractWithBLock(BlockInteractEvent e) {

    }
}
