package com.example.spotify.models.user;

import com.example.spotify.models.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

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
    @Enumerated(STRING)
    private RoleName name;
}
