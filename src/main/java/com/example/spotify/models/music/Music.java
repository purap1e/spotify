package com.example.spotify.models.music;

import com.example.spotify.models.BaseEntity;
import com.example.spotify.models.Link;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "musics")
public class Music extends BaseEntity {

    @Column
    private String name;

    @ManyToMany(fetch = EAGER, cascade = ALL)
    @JoinTable(name = "music_singers", joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "singer_id", referencedColumnName = "id"))
    private List<Singer> singers = new ArrayList<>();

    @Column(name = "date_of_creation")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date createdAt;

    @Column(name = "link")
    @OneToOne(fetch = EAGER, cascade = ALL)
    private Link link;

    @ManyToMany(fetch = EAGER, cascade = ALL)
    @JoinTable(name = "music_genres", joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres = new ArrayList<>();
}
