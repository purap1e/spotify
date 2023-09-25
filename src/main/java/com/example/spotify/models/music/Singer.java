package com.example.spotify.models.music;

import com.example.spotify.models.BaseEntity;
import com.example.spotify.models.Link;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "singers")
public class Singer extends BaseEntity {

    @Column
    private String name;

    @Column
    @OneToOne(fetch = EAGER, cascade = ALL)
    private Link link;
}
