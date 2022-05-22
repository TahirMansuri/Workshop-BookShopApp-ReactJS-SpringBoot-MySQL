package com.infogalaxy.workshopbookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 21-05-2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotNull(message = "Address must be provided.")
    private String street;
    private String town;
    @NotNull(message = "District must be provided.")
    private String district;
    @NotNull(message = "State must be selected.")
    private String state;
    @NotNull(message = "Country must be provided.")
    private String country;
    @NotNull(message = "Pincode Must be provided")
    @Size(min = 6, message = "Pincode must be of 6 Digit Only.")
    private String pincode;
}
