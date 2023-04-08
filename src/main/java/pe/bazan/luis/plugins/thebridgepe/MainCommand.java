package pe.bazan.luis.plugins.thebridgepe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pe.bazan.luis.plugins.thebridgepe.commands.ResetWorlds;
import pe.bazan.luis.plugins.thebridgepe.commands.Schematics;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Hi");
            return false;
        }
        if (args[0].equalsIgnoreCase("reset")) {
            ResetWorlds.run(sender, command, label, args);
        }
        if (args[0].equalsIgnoreCase("schematic")) {
            Schematics.run(sender, command, label, args);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completer = new ArrayList<>();
        if (args.length == 1) {
            String arg0 = args[0];
            String[] words = new String[]{"reset", "schematic"};
            for (String search : words) {
                if (search.startsWith(arg0)) {
                    completer.add(search);
                }
            }
        }
        if (args.length == 2) {
            String arg0 = args[0];
            String arg1 = args[1];
            if (arg0.equalsIgnoreCase("schematic")) {
                String[] words = new String[]{"place"};
                for (String search : words) {
                    if (search.startsWith(arg1)) {
                        completer.add(search);
                    }
                }
            }
        }
        if (args.length == 3) {
            String arg0 = args[0];
            String arg1 = args[1];
            String arg2 = args[2];
            if (arg0.equalsIgnoreCase("schematic")) {
                if (arg1.equalsIgnoreCase("place")) {
                    for (String search : pe.bazan.luis.plugins.thebridgepe.utils.Schematics.getSchematics().keySet()) {
                        if (search.startsWith(arg2)) {
                            completer.add(search);
                        }
                    }
                }
            }
        }

        return completer;
    }
}
