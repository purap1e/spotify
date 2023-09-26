package com.example.spotify.repos;

import com.example.spotify.models.music.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface SingerRepo extends ElasticsearchRepository<Singer, UUID> {

    @Query("{\"fuzzy\": {\"name\": \"?0\"}}")
    List<Singer> findByName(String name, Pageable pageable);
}
