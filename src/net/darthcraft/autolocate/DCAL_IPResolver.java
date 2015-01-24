package net.darthcraft.autolocate;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DCAL_IPResolver
{

    DatabaseReader reader;

    public DCAL_IPResolver()
    {
        File database = new File("GeoLite2-City.mmdb");

        try
        {
            reader = new DatabaseReader.Builder(database).build();
        }
        catch (IOException ex)
        {
            Logger.getLogger(DCAL_IPResolver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This will get the country iSO code (Eg US, GB etc) and return it for
     * usage.
     *
     * @param ip
     * @return IsoCode
     */
    public String getCountryISO(String ip)
    {
        try
        {
            CityResponse response = reader.city(InetAddress.getByName(ip));;

            Country country = response.getCountry();

            return country.getIsoCode();
        }
        catch (IOException | GeoIp2Exception ex)
        {
            Logger.getLogger(DCAL_IPResolver.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
