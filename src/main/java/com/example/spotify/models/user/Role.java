package com.example.spotify.models.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column
    private String name;
}
