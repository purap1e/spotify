package com.example.spotify.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserSpotify extends BaseEntity{

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = EAGER, cascade = ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


}
