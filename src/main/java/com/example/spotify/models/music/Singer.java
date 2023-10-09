package com.example.spotify.models.music;

import com.example.spotify.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "singers")
public class Singer extends BaseEntity {

    @Column
    @Field(type = FieldType.Text, fielddata = true)
    private String name;

    @JoinColumn(name = "link_id")
    @OneToOne(fetch = EAGER, cascade = ALL)
    @Field(type = FieldType.Nested, includeInParent = true)
    private Link link;
}
