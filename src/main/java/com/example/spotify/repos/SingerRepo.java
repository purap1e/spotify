package com.example.spotify.repos;

import com.example.spotify.models.music.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface SingerRepo extends JpaRepository<Singer, UUID> {
}
