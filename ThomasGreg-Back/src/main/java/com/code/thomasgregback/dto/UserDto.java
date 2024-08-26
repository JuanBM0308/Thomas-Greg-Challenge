package com.code.thomasgregback.dto;

import com.code.thomasgregback.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String mail;
    private String password;
    private Long role;
}
