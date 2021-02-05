package com.weaselguard.weaselguardstoragemodule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceAddress;
    private String sourceApplication;
    private String protocol;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDatetime;
    private PRIORITY priority;
    private String message;
    private String comment;

    @ManyToMany
    private List<Incident> incidents;

    public enum PRIORITY {
        EMERG,
        ALERT,
        CRIT,
        ERR,
        WARNING,
        NOTICE,
        INFO,
        DEBUG,
        OTHER
    }
}
