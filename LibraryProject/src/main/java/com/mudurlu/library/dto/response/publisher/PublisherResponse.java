package com.mudurlu.library.dto.response.publisher;

import com.mudurlu.library.entitites.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {

    private int id;

    private String name;


    private LocalDate establishmentYear;



}
