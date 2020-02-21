package com.pool.tronik.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by andreivasilevitsky on 22/05/2019.
 */
public class MSystemIPAddress {

    public void enumerateAllNetInterface() {
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets))
                displayInterfaceInformation(netint);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    static void displayInterfaceInformation(NetworkInterface netint) {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            System.out.printf("InetAddress: %s\n", inetAddress);
        }
        System.out.printf("\n");
    }
    public  InetAddress getSystemIP(){
        try{
            InetAddress sysIP=null;
            String OSName=  System.getProperty("os.name");
            if(OSName.contains("Windows") || OSName.contains("Mac OS X")){
                sysIP   = InetAddress.getLocalHost();
            }
            else{
                sysIP=getSystemIP4Linux("eth0");
                if(sysIP==null){
                    sysIP=getSystemIP4Linux("eth1");
                    if(sysIP==null){
                        sysIP=getSystemIP4Linux("eth2");
                        if(sysIP==null){
                            sysIP=getSystemIP4Linux("usb0");
                            if (sysIP == null) {
                                sysIP = getSystemIP4Linux("wlan0");
                            }
                        }
                    }
                }
            }
            return sysIP;
        }
        catch(Exception E){
            System.err.println("System IP Exp : "+E.getMessage());
            return null;
        }
    }

    private  InetAddress getSystemIP4Linux(String name){
        try{
            //String ip="";
            NetworkInterface networkInterface = NetworkInterface.getByName(name);
            Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
            InetAddress currentAddress = inetAddress.nextElement();
            while(inetAddress.hasMoreElements()){
                currentAddress = inetAddress.nextElement();
                if(currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress()){
                    //ip = currentAddress.toString();
                    break;
                }
            }
            /*if(ip.startsWith("/")){
                ip=ip.substring(1);
            }
            return ip;*/
            return currentAddress;
        }
        catch (Exception E) {
            System.err.println("System Linux IP Exp : "+E.getMessage());
            return null;
        }
    }
}
