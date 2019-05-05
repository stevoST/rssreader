package com.domain.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String link;
    private String description;
    private String pubDate;
}
