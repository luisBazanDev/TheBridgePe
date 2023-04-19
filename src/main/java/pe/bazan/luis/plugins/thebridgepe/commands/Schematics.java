package pe.bazan.luis.plugins.thebridgepe.commands;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.session.PasteBuilder;
import com.sk89q.worldedit.world.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.thebridgepe.TheBridgePe;
import pe.bazan.luis.plugins.thebridgepe.utils.MessageFormat;

import java.io.IOException;

public class Schematics {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            for (String schematic : pe.bazan.luis.plugins.thebridgepe.utils.Schematics.getSchematics().keySet()) {
                sender.sendMessage(schematic);
            }
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageFormat.formatMC("You are not a player, this commands only executes by player."));
            return;
        }

        Player player = (Player) sender;

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("schematic")) {
                if (args[1].equalsIgnoreCase("place")) {
                    String schemName = args[2];
                    Clipboard clipboard = pe.bazan.luis.plugins.thebridgepe.utils.Schematics.getSchematics().get(schemName);
                    if (clipboard == null) {
                        sender.sendMessage(MessageFormat.formatMC("&cSchem not found."));
                        return;
                    }

                    try (EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(player.getWorld()))) {
                        Operation operation = new ClipboardHolder(clipboard)
                                .createPaste(editSession)
                                .to(BlockVector3.at(
                                        player.getLocation().getX(),
                                        player.getLocation().getY(),
                                        player.getLocation().getZ()
                                ))
                                .ignoreAirBlocks(true)
                                .build();
                        try {
                            Operations.complete(operation);
                        } catch (WorldEditException e) {

                            sender.sendMessage(MessageFormat.formatMC("&cError on place schematic, check the console for more information."));
                            throw new RuntimeException(e);
                        }
                        sender.sendMessage(MessageFormat.formatMC("&aPlace complete!"));
                        return;
                    }
                }
            }
        }

        sender.sendMessage("Syntax error.");
    }
}
