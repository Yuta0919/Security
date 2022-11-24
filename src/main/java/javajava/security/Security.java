package javajava.security;

import javajava.security.Command.PasswordEnter;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Random;

public final class Security extends JavaPlugin implements Listener {
public static HashMap<String,Integer> joinmap = new HashMap<>();
public static JavaPlugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        PasswordEnter.pass= getConfig().getInt("Pass");
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("password").setExecutor(new PasswordEnter());
        plugin=this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getConfig().set("Pass", (new Random().nextInt(9000)+1000));
        saveConfig();
    }
    @EventHandler
    public static void onJoinEvent(PlayerJoinEvent e){
        if(!joinmap.containsKey(e.getPlayer().getName())) {
            joinmap.put(e.getPlayer().getName(), 0);
        }
    }
    @EventHandler
    public static void onMoveEvent(PlayerMoveEvent e){
        if(joinmap.get(e.getPlayer().getName())==0){
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED+"パスワードを入力してください");
        }
    }
}
