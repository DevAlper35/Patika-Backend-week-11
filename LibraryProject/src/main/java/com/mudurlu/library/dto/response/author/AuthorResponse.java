package com.mudurlu.library.dto.response.author;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private int id;

    private String name;

    private LocalDate birthDate;

    private String country;
}
