package com.example.spotify.repos;

import com.example.spotify.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface LinkRepo extends JpaRepository<Link, UUID> {
}
