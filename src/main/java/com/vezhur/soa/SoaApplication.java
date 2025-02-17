package com.vezhur.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoaApplication {
    public static void main(String[] args) {
        //System.setProperty("javax.net.ssl.trustStore", "./certs/spring.truststore");
        //System.setProperty("javax.net.ssl.trustStorePassword", "password");
        System.setProperty("jsse.enableSNIExtension", "false");
        SpringApplication.run(SoaApplication.class, args);
    }
}
