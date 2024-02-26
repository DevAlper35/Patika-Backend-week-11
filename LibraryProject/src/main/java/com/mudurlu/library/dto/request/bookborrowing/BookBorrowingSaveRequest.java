package com.mudurlu.library.dto.request.bookborrowing;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingSaveRequest {

    private String name;

    private LocalDate borrowingDate;

    private LocalDate returningDate;
    private int bookId;
}
