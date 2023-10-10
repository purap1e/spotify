package com.example.spotify.services;

import com.example.spotify.dto.SingerDTO;
import com.example.spotify.models.music.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface SingerService {
    SingerDTO save(String name, MultipartFile image);
    Singer get(UUID id);
    List<Singer> getAll();
}
