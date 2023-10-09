package com.example.spotify.es_models;

import com.example.spotify.helper.Indices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = Indices.MUSIC_INDEX)
public class ESMusic {

    @Id
    private String id;
    private String name;
    private UUID musicId;
}
