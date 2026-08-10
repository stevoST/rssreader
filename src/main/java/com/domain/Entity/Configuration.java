package com.domain.Entity;

import lombok.Data;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Data
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String feedName;
    private String feedLink;
    private String feedDateFormat;
}
