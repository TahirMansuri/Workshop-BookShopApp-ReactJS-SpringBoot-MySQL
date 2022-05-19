package com.infogalaxy.workshopbookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
