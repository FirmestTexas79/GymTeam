package com.gymos.web.dto;


import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto {

    private Long id;
    private String username;
    private String email;
    private String password;

}