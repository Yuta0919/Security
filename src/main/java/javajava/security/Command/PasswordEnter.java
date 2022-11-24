package javajava.security.Command;

import javajava.security.Security;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PasswordEnter implements CommandExecutor {
    public static int pass;
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        pass= Security.plugin.getConfig().getInt("Pass");
        if (args[0].equals(String.valueOf(pass))) {
            int a = Security.joinmap.get(sender.getName());
            if(a==0){
                Security.joinmap.put(sender.getName(),1);
                sender.sendMessage(ChatColor.RED+"制限を解除しました");
            }
        }else{
            sender.sendMessage(ChatColor.RED+"正しいパスワードを打ってください");
        }
        return true;
    }
}
