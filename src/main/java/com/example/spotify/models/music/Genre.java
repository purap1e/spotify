package com.example.spotify.models.music;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column
    private String name;
}
