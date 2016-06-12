package com.tcore.dto;

import lombok.Getter;
import lombok.Setter;

public class AbstractUserDto {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;
}
