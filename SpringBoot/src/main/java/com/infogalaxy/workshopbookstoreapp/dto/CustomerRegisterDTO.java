package com.infogalaxy.workshopbookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterDTO {

    @NotNull(message = "Firstname is Required.")

    private String firstName;

    @NotNull(message = "Lastname is Required.")

    private String lastName;

    @NotNull(message = "KYC is Required.")
    @Size(min = 12, message = "KYC No should be of 12 digits.")
    private String kyc;

    //@JsonFormat(pattern = "YYYY-MM-DD")
    @NotNull(message = "Date of Birth is Required.")
    @Past(message = "Date of Birth must be of Past Date")
    //LocalDate format must be YYYY-MM-DD
    private LocalDate dob;

    //@JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate registerDate;
    private LocalDate updateDate;

    @NotNull(message = "Username must be provided.")
    private String username;

    @NotNull(message = "Password must be provided.")
    private String password;

    @NotNull(message = "Please Enter the EMail ID")
    @Email(message = "Email ID should be of Proper format.")
    private String emailId;

    private String role;

    private Boolean verify;
    private String verificationCode;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
}
