package com.weaselguard.weaselguardstoragemodule.configs;

import com.weaselguard.weaselguardstoragemodule.models.Event;
import org.springframework.core.convert.converter.Converter;

public class StringToEventPriorityConverter implements Converter<String, Event.PRIORITY> {
    @Override
    public Event.PRIORITY convert(String source) {
        try {
            return Event.PRIORITY.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
