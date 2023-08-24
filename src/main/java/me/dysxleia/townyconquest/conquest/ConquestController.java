package me.dysxleia.townyconquest.conquest;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Government;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.dysxleia.townyconquest.conquest.zone.ConquestZone;
import me.dysxleia.townyconquest.conquest.zone.ConquestZoneFlag;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public final class ConquestController {

    private List<ConquestZone> zones = new ArrayList<>();

    public ConquestController() {
        this.zones = getConquestZones();
    }

    public List<ConquestZone> getConquestZones() {

        List<ConquestZone> conquestZones = new ArrayList<>();

        for (World w : Bukkit.getServer().getWorlds()) {
            RegionManager manager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(w));
            assert manager != null;
            if (manager.getRegions().isEmpty() || manager.getRegions() == null) continue;

            for (ProtectedRegion r : manager.getRegions().values()) {
                if (r.getFlags().containsKey(ConquestZoneFlag.CONQUEST_ZONE_FLAG)) {
                    ConquestZone zone = new ConquestZone(r);
                    conquestZones.add(zone);
                }
            }
        }

        return conquestZones;
    }

    public ConquestZone getConquestZone(Government gov) throws TownyException {
        for (ConquestZone z : zones) {
            if (z.hasContributor(gov)) {
                return z;
            }
        }
        return null;
    }




}
