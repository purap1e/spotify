package com.example.spotify.repos;

import com.example.spotify.models.music.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LinkRepo extends JpaRepository<Link, UUID> {
}
