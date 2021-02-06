package com.weaselguard.weaselguardstoragemodule.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weaselguard")
@Data
public class WeaselGuardProperties {
    private Security security;

    @Data
    public static class Security {
        private Keys keys;

        @Data
        public static class Keys {
            private String storage;
        }
    }
}
