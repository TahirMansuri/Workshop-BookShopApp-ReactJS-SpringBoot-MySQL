package com.infogalaxy.workshopbookstoreapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 20-05-2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userbooks")
public class UserBookEntity {

    @Id
    @Column(name = "serialno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
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

    private String orderNumber;
    private int purchaseQuantity;

    private String checkoutDateTime;
    private String updateDateTime;

    private boolean isAddedToCart;
    private boolean isAddedToWishList;
    private boolean isCheckedOut;

    private long serialNumber;

    @JsonIgnore
    @ManyToMany(mappedBy = "booksList", fetch = FetchType.LAZY)
    private List<UserEntity> userEntityList;


    private LocalDate addDate;
    private LocalDate updateDate;
}
