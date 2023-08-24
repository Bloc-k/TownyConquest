package me.dysxleia.townyconquest.conquest.zone;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import me.dysxleia.townyconquest.TownyConquest;
import org.bukkit.Bukkit;

import java.util.logging.Level;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class ConquestZoneFlag {

    public static StateFlag CONQUEST_ZONE_FLAG;

    public static void registerZoneFlag() {

        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("conquest-zone", true);
            registry.register(flag);
            CONQUEST_ZONE_FLAG = flag; // only set our field if there was no error
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("conquest-zone");
            if (existing instanceof StateFlag) {
                CONQUEST_ZONE_FLAG = (StateFlag) existing;
            } else {
                Bukkit.getLogger().log(Level.SEVERE, "Conquest zone flag failed to hook. Disabling TownyConquest");
                Bukkit.getPluginManager().disablePlugin(getPlugin(TownyConquest.class));
            }
        }
    }

}
