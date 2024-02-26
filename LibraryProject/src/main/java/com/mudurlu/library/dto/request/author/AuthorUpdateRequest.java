package com.mudurlu.library.dto.request.author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    @Positive(message = "ID degeri pozitif bir sayi giriniz")
    private int id;
    @NotNull(message = "Yazar ismi boş olamaz")
    private String name;

    private LocalDate birthDate;

    private String country;
}
