package pe.bazan.luis.plugins.thebridgepe.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pe.bazan.luis.plugins.thebridgepe.TheBridgePe;

public class CreateArena {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("reset worlds...");
        if(TheBridgePe.getInstance().getWorldManager().getMultiverseCore().cloneWorld("the_bridge", "a",  "")) {

        }
        sender.sendMessage("worlds resets");
    }
}
