package org.acme.patient.onboarding.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostname = inetAddress.getHostName();
        System.setProperty("HOSTNAME", hostname);
        System.out.println("HOSTNAME=" + hostname);

        SpringApplication.run(Application.class, args);
    }
}
