package com.example.spotify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
public class MusicRequest {
    private String name;
    private List<UUID> listOfSingersId;
    private List<UUID> listOfGenresId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date date;
}
