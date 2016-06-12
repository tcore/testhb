package com.tcore.dto;

import lombok.Getter;
import lombok.Setter;

public class CreateUserDto extends AbstractUserDto {
    @Getter
    @Setter
    private String password;
}
