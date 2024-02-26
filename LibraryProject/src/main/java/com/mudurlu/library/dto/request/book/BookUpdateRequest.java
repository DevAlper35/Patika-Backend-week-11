package com.mudurlu.library.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private int id;

    private String name;

    private LocalDate publicationYear;

    private int stock;


    private int authorId;


    private int publisherId;
}
