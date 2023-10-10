package com.example.spotify.models.music;

import com.example.spotify.models.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "musics")
public class Music extends BaseEntity {

    @Column
    private String name;

    @ManyToMany(cascade = ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "music_singers", joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "singer_id", referencedColumnName = "id"))
    private List<Singer> singers = new ArrayList<>();

    @Column(name = "date_of_creation")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dateOfCreation;

    @JoinColumn(name = "image_id")
    @OneToOne(fetch = EAGER, cascade = ALL)
    private Image image;

    @ManyToMany(cascade = ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "music_genres", joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres = new ArrayList<>();
}
