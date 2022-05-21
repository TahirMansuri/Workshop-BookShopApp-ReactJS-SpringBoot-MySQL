package com.infogalaxy.workshopbookstoreapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "adminbooks")
public class AdminBookEntity {

    @Id
    @Column(name = "serialno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serialNo;
    @NotNull(message = "Book Code must be provided")
    private String bookCode;
    @NotNull(message = "Book Name must be provided")
    private String name;
    @NotNull(message = "Author name must be entered")
    private String author;
    private String description;
    @Column(length = 10000)
    private String logo;
    private double price;
    private int availableQuantity;

    private boolean isAdmin;
    private boolean isRemoved;
    private boolean isOutOfStock;

    private LocalDate addDate;
    private LocalDate updateDate;
}
