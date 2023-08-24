package me.dysxleia.townyconquest.conquest;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Government;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import me.dysxleia.townyconquest.conquest.zone.ConquestZone;

public class ConquestScore {

    public static float calculateConquestScore(Nation n, ConquestZone zone) throws TownyException {

        if (zone == null || !zone.hasContributor(n)) return 0;

        int nationShare = 0;
        int totalClaimBlocks = 0;

        for (Town t : n.getTowns()) {
            if (zone.hasContributor(t)) {
                nationShare += t.getNumTownBlocks();
            }
        }

        for (Government t : zone.getContributors(Town.class)) {
            totalClaimBlocks += t.getTownBlocks().size();
        }

        return (float) 100 * nationShare / totalClaimBlocks;
    }

}
