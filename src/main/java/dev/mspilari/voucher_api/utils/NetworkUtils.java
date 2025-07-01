package dev.mspilari.voucher_api.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.springframework.stereotype.Component;

@Component
public class NetworkUtils {
    public String getLocalIpAddress() {
        try {
            NetworkInterface iface = NetworkInterface.getByName("wlp2s0"); // interface correta que identificamos

            if (iface != null && iface.isUp()) {
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && addr.isSiteLocalAddress()) {
                        return addr.getHostAddress(); // deve retornar 192.168.0.11
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "localhost"; // fallback
    }
}
