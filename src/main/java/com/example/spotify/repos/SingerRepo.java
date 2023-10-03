package com.example.spotify.repos;

import com.example.spotify.models.music.Singer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SingerRepo extends ElasticsearchRepository<Singer, UUID> {

    @Query("{\"fuzzy\": {\"searchSimilar\": \"?0\"}}")
    List<Singer> findBySearchSimilar(String searchSimilar, Pageable pageable);
}
