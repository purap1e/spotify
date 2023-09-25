package com.example.spotify.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "links")
public class Link {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column
    private String link;

    @Column
    private String type;
}
