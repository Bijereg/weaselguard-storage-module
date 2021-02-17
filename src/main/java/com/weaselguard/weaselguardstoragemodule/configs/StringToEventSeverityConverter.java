package com.weaselguard.weaselguardstoragemodule.configs;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import org.springframework.core.convert.converter.Converter;

public class StringToEventSeverityConverter implements Converter<String, Event.SEVERITY> {

    @Override
    public Event.SEVERITY convert(String source) {
        try {
            return Event.SEVERITY.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
