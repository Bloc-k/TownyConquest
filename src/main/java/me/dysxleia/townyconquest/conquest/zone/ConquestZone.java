package me.dysxleia.townyconquest.conquest.zone;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Government;
import com.palmergames.bukkit.towny.object.Nation;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.util.ArrayList;
import java.util.List;

public class ConquestZone {

    private final ProtectedRegion zone;

    public ConquestZone(ProtectedRegion zoneRegion) {
        this.zone = zoneRegion;
    }

    public <T> List<Government> getContributors(Class<T> government) throws TownyException {

        List<Government> contributors = new ArrayList<>();

        for (Government gov : government == Nation.class ? TownyAPI.getInstance().getNations() : TownyAPI.getInstance().getTowns()) {
            if (hasContributor(gov)) {
                contributors.add(gov);
            }
        }

        return contributors;
    }


    public boolean hasContributor(Government gov) throws TownyException {

        Location loc = BukkitAdapter.adapt(gov.getSpawn());

        ApplicableRegionSet set = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(loc);

        return set.getRegions().contains(zone);
    }

    public ProtectedRegion getWGRegion() {
        return this.zone;
    }

}
