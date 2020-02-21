package com.pool.tronik;

import com.pool.tronik.utils.MSystemIPAddress;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by andreivasilevitsky on 03/05/2019.
 */
@Component
public class CustomizationBean implements
        WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override
    public void customize(ConfigurableServletWebServerFactory configurableServletWebServerFactory) {
        try {
            MSystemIPAddress mSystemIPAddress = new MSystemIPAddress();
            mSystemIPAddress.enumerateAllNetInterface();
            InetAddress inetAddress = mSystemIPAddress.getSystemIP();

            configurableServletWebServerFactory.setAddress(inetAddress);
            System.out.println("Your IP address : " + inetAddress);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
