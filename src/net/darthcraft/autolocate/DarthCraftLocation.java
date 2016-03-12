package net.darthcraft.autolocate;

import java.io.InputStream;
import java.util.Properties;
import net.pravian.aero.Aero;
import net.pravian.aero.internal.AeroContainer;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class DarthCraftLocation extends JavaPlugin implements AeroContainer {

    private final DarthCraftLocation plugin;
    private final Aero aero;
    //

    public DarthCraftLocation() {
        this.plugin = this;
        this.aero = new Aero(plugin);
    }

    @Override
    public void onLoad() {
        Bukkit.getServicesManager().register(AeroContainer.class, this, plugin, ServicePriority.Normal);
    }

    @Override
    public void onEnable() {
        aero.init();
        new InternalMetricsSubmitter(plugin).submit();
    }

    @Override
    public void onDisable() {
        this.aero.deinit();
    }

    @Override
    public Aero getAero() {
        return aero;
    }

}
