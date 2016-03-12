package net.darthcraft.autolocate.repair;

import net.darthcraft.autolocate.listeners.PlayerListener;
import net.pravian.aero.command.handler.AeroCommandHandler;
import net.pravian.aero.command.handler.SimpleCommandHandler;
import net.pravian.aero.config.YamlConfig;
import net.pravian.aero.plugin.AeroPlugin;
import net.pravian.aero.util.Loggers;
import org.bukkit.plugin.PluginManager;

public class DarthCraftAutoLocation extends AeroPlugin<DarthCraftAutoLocation>
  {

    public static DarthCraftAutoLocation plugin;
    public PlayerListener listener;
    public DCAL_IPResolver ipResolver;
    public static AeroCommandHandler handler;
    public Loggers logger;
    public YamlConfig config;

    @Override
    public void load()
    {
        DarthCraftAutoLocation.plugin = this;
        Loggers.info("Loading DC-AutoLocation.");
        config = new YamlConfig(plugin, "config.yml", true);
    }

    @Override
    public void enable()
    {
        DarthCraftAutoLocation.plugin = this;
        
        // Load Config
        config.load();
        
        // Register command class (not needed)
        handler = new SimpleCommandHandler(plugin);
        handler.setCommandClassPrefix("Command_");
        handler.registerAll();
        
        // Register Listener
        final PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), plugin);
        
        Loggers.info("Enabling DC-AutoLocation.");
    }

    @Override
    public void disable()
    {
        DarthCraftAutoLocation.plugin = this;

        Loggers.info("Disabling DC-AutoLocation.");
    }

  }
