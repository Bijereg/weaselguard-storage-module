package com.weaselguard.weaselguardstoragemodule.configs;

import com.weaselguard.weaselguardstoragemodule.models.Incident;
import org.springframework.core.convert.converter.Converter;

public class StringToIncidentAccuracyConverter implements Converter<String, Incident.ACCURACY> {
    @Override
    public Incident.ACCURACY convert(String source) {
        try {
            return Incident.ACCURACY.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
