package com.prem.ylan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OSController {

    @GetMapping("/osinfo")
    public Map<String, String> getOSInfo() {
        Map<String, String> response = new HashMap<>();
        response.put("os", System.getProperty("os.name"));
        return response;
    }
    @GetMapping("/ip")
    public Map<String,String> getip() throws IOException {
        InetAddress ip = null;
        Map<String, String> response = new HashMap<>();
        String hostaddress = null;
        try {
            ip = InetAddress.getLocalHost();
            hostaddress = ip.getHostAddress();
        } catch (UnknownHostException ignored) {}
        response.put("ip",hostaddress);
        return response;
    }
}
