package com.mudurlu.library.dto.request.book;

import com.mudurlu.library.entitites.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {


    private String name;

    private LocalDate publicationYear;

    private int stock;

    private int authorId;

    private int publisherId;
    private List<Category> categoryList;
}
