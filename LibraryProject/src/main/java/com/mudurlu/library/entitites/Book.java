package com.mudurlu.library.entitites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "serial")
    private int id;
    @NotNull
    @Column(name = "book_name")
    private String name;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "book_pub_year")
    private LocalDate publicationYear;
    @NotNull
    @Column(name = "book_stock")
    private int stock;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_author_id",referencedColumnName = "author_id")
    private Author author;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_publisher_id",referencedColumnName = "publisher_id")
    private Publisher publisher;


    @OneToMany(mappedBy = "book")
    private List<BookBorrowing> bookBorrow;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "books2cat",
            joinColumns = {@JoinColumn(name = "books2cat_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "books2cat_category_id")}
    )
    private List<Category> categoryList;
}
