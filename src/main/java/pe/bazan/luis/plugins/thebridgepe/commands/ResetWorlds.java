package pe.bazan.luis.plugins.thebridgepe.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pe.bazan.luis.plugins.thebridgepe.TheBridgePe;

public class ResetWorlds {
    public static void run(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("reset worlds...");
        Bukkit.getScheduler().runTask(TheBridgePe.getInstance(), new Runnable() {
            @Override
            public void run() {
                TheBridgePe.getInstance().getWorldManager().resetWorlds();
                sender.sendMessage("worlds resets");
            }
        });
    }
}
