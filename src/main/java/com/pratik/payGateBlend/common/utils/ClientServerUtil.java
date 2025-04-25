package com.pratik.payGateBlend.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientServerUtil {
    public static String getServerIpAddress() throws UnknownHostException {
        InetAddress IP = InetAddress.getLocalHost();
        String address = IP.toString();
        return address.substring(address.indexOf('/')+1);
    }

    public static String getServerMacAddress()
            throws UnknownHostException, SocketException {

        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        byte[] hardwareAddress = ni.getHardwareAddress();

        String[] hexadecimal = new String[hardwareAddress.length];
        for (int i = 0; i < hardwareAddress.length; i++) {
            hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
        }

        return String.join(":", hexadecimal);


    }
}
