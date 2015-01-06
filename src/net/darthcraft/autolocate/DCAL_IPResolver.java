package net.darthcraft.autolocate;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DCAL_IPResolver
    {

    DatabaseReader reader;

    /* Debugging Use for when I forget what I am doing...
     public static void main(String[] args) {
     DCAL_IPResolver ipResolver = new DCAL_IPResolver();
     System.out.println(ipResolver.getCountry("198.50.141.43"));
     }
     */
    public DCAL_IPResolver()
        {
        File database = new File("D://GeoLite2-City.mmdb");

        try
            {
            reader = new DatabaseReader.Builder(database).build();
            }
        catch (IOException ex)
            {
            Logger.getLogger(DCAL_IPResolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
