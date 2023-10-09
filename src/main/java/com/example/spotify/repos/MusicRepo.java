package com.example.spotify.repos;

import com.example.spotify.models.music.Music;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.UUID;

@Repository
public interface MusicRepo extends JpaRepository<Music, UUID> {
}
