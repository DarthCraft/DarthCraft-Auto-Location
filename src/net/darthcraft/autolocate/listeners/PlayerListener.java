package net.darthcraft.autolocate.listeners;

import java.util.Arrays;
import java.util.List;
import net.darthcraft.autolocate.DarthCraftLocation;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener
    {

    private final DarthCraftLocation plugin;

    public static final List<String> COUNTRIES = Arrays.asList("AU");

    public PlayerListener(DarthCraftLocation plugin)
        {
        this.plugin = plugin;

        }

    public void teleport(World world, Player player, int x, int y, int z)
        {
        player.teleport(new Location(world, x, y, z));
        }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoinEvent(PlayerJoinEvent event)
        {

        Player player = event.getPlayer();

        if (player.hasPlayedBefore())
            {
            return;
            }
        String country = plugin.ipResolver.getCountryISO(player.getAddress().getHostName());

        if (COUNTRIES.contains(country))
            {
            teleport(player.getWorld(), player, plugin.config.getInt(country.toUpperCase() + ".X"), plugin.config.getInt(country.toUpperCase() + ".Y"), plugin.config.getInt(country.toUpperCase() + ".Z"));
            }
        else
            {
            teleport(player.getWorld(), player, plugin.config.getInt("OTHER" + ".X"), plugin.config.getInt("OTHER" + ".Y"), plugin.config.getInt("OTHER" + ".Z"));

            }

        }

    }
