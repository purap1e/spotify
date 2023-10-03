package com.example.spotify.models.music;

import com.example.spotify.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "singers")
@Document(indexName = "singers")
public class Singer extends BaseEntity {

    @Column(name = "name")
    private String searchSimilar;

    @JoinColumn(name = "link_id")
    @OneToOne(fetch = EAGER, cascade = ALL)
    @Field(type = FieldType.Nested)
    private Link link;
}
