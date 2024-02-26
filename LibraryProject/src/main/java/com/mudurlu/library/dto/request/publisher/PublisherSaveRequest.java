package com.mudurlu.library.dto.request.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {
    private String name;

    private LocalDate establishmentYear;

    private String address;
}
