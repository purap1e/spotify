package com.example.spotify.models.music;

import com.example.spotify.models.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

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
    @Enumerated(STRING)
    private GenreName name;
}
