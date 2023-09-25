package com.example.spotify.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {

    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Invalid username: username is null")
    private String username;

    @NotNull(message = "Invalid password: password is null")
    @Size.List({
            @Size(min = 4, message = "Password's length must be minimum 4 characters"),
            @Size(max = 100, message = "Password's length must be maximum 100 characters")
    })
    private String password;
}
