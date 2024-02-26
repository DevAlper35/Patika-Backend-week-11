package com.mudurlu.library.entitites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrowedbooks")
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id", columnDefinition = "serial")
    private int id;

    @Column(name = "borrower_name", nullable = false)
    private String name;


    @Temporal(TemporalType.DATE)
    @Column(name = "borrow_date",nullable = false)
    private LocalDate borrowingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "returning_date")
    private LocalDate returningDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrow_book_id",referencedColumnName = "book_id")
    private Book book;
}
