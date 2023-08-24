package me.dysxleia.townyconquest.commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import me.dysxleia.townyconquest.conquest.ConquestScore;
import me.dysxleia.townyconquest.conquest.zone.ConquestZone;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.dysxleia.townyconquest.TownyConquest.controller;

public class NationDominance implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            Bukkit.getLogger().warning("Only a player can run this");
            return true;
        }

        Player p = (Player) commandSender;
        Resident r = TownyAPI.getInstance().getResident(p);
        Nation n;
        ConquestZone zone;
        try {
            assert r != null;
            n = r.getNation();
            zone = controller.getConquestZone(n);
            p.sendMessage(ChatColor.translateAlternateColorCodes(
                    '&', "&6Your Nation has a dominance score of &e" + ConquestScore.calculateConquestScore(n, zone))
                    + " &6in the zone &e" + controller.getConquestZone(n)
            );

        } catch (TownyException e) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError executing. Are you a part of a nation?"));
            e.printStackTrace();
            return true;
        }



        return true;
    }

}
