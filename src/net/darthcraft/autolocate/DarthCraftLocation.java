package net.darthcraft.autolocate;

import net.pravian.bukkitlib.BukkitLib;
import net.pravian.bukkitlib.command.BukkitCommandHandler;
import net.pravian.bukkitlib.config.YamlConfig;
import net.pravian.bukkitlib.implementation.BukkitPlugin;
import net.pravian.bukkitlib.util.LoggerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

public class DarthCraftLocation extends BukkitPlugin
    {

    public DarthCraftLocation plugin;
    public YamlConfig config;
    public BukkitCommandHandler handler;
    public PluginManager pm;
    public DCAL_IPResolver ipResolver;

    @Override
    public void onLoad()
        {
        this.plugin = this;
        this.handler = new BukkitCommandHandler(plugin);
        this.config = new YamlConfig(plugin, "config.yml");
        this.pm = Bukkit.getPluginManager();

        config.load();
        }

    @Override
    public void onEnable()
        {
        BukkitLib.init(plugin);
        ipResolver = new DCAL_IPResolver();

        LoggerUtils.info(plugin, "The DarthCraft Auto Location Plugin has been Enabled!");
        }

    @Override
    public void onDisable()
        {
        LoggerUtils.info(plugin, "The DarthCraft Auto Location Plugin has been Disabled!");
        }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
        {
        return handler.handleCommand(sender, cmd, commandLabel, args);
        }
    }
