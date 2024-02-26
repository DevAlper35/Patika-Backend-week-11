package com.mudurlu.library.dto.request.publisher;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {

    private int id;

    private String name;

    private LocalDate establishmentYear;

    private String address;

}
