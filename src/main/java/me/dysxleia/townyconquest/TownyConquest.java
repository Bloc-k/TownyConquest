package me.dysxleia.townyconquest;

import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import me.dysxleia.townyconquest.commands.NationDominance;
import me.dysxleia.townyconquest.conquest.ConquestController;
import me.dysxleia.townyconquest.conquest.zone.ConquestZoneFlag;
import org.bukkit.plugin.java.JavaPlugin;

public final class TownyConquest extends JavaPlugin {

    public final TownyConquest plugin = this;

    public static final ConquestController controller = new ConquestController();

    @Override
    public void onLoad() {
        ConquestZoneFlag.registerZoneFlag();
    }

    @Override
    public void onEnable(){
        TownyCommandAddonAPI.addSubCommand(TownyCommandAddonAPI.CommandType.NATION, "dominance", new NationDominance());
    }

    public TownyConquest getInstance() {
        return this.plugin;
    }

}
