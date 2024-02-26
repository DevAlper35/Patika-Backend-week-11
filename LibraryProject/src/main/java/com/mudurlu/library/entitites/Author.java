package com.mudurlu.library.entitites;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", columnDefinition = "serial")
    private int id;
    @NotNull
    @Column(name = "author_name")
    private String name;
    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "author_birth_date")
    private LocalDate birthDate;
    @NotNull
    @Column(name = "author_country")
    private String country;

    @JsonBackReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> bookList;
}