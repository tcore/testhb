package com.tcore.converter;

import com.tcore.domain.User;
import com.tcore.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Can't convert null to Department");
        }
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setId(user.getId());

        return dto;
    }
}
