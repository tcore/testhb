package com.tcore.converter;

import com.tcore.domain.User;
import com.tcore.dto.CreateUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDtoToDomainConverter implements Converter<CreateUserDto, User> {
    @Override
    public User convert(CreateUserDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Can't convert null to Department");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setPassword(dto.getPassword());

        return user;
    }
}
