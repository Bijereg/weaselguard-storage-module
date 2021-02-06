package com.weaselguard.weaselguardstoragemodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WeaselguardStorageModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeaselguardStorageModuleApplication.class, args);
    }

}
