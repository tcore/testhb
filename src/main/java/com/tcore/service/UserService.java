package com.tcore.service;

import com.tcore.converter.ConverterService;
import com.tcore.domain.User;
import com.tcore.dto.CreateUserDto;
import com.tcore.dto.UserDto;
import com.tcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConverterService converterService;

    public UserDto find(Long id) {
        User user = userRepository.findOne(id);

        return converterService.convert(user, UserDto.class);
    }

    public UserDto create(CreateUserDto userDto) {
        User user = converterService.convert(userDto, User.class);
        userRepository.saveAndFlush(user);

        return converterService.convert(user, UserDto.class);
    }

    public UserDto update(Long id, CreateUserDto userDto) {
        User user = userRepository.findOne(id);
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userRepository.saveAndFlush(user);

        return converterService.convert(user, UserDto.class);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}
