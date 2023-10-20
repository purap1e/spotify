package com.example.spotify.es_repos;

import com.example.spotify.es_models.ESMusic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
public interface ESMusicRepo extends ElasticsearchRepository<ESMusic, String> {
    @Query("{\"match_phrase_prefix\": {\"name\": {\"query\": \"?0\"}}}")
    List<ESMusic> findByName(String name, Pageable pageable);
}
