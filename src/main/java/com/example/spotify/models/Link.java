package com.example.spotify.models;

import com.example.spotify.models.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

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
    @Enumerated(STRING)
    private Type type;
}
