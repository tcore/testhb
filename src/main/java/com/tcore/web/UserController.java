package com.tcore.web;

import com.tcore.dto.CreateUserDto;
import com.tcore.dto.UserDto;
import com.tcore.exception.ResourceNotFoundException;
import com.tcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/{userId}")
    public UserDto find(@PathVariable("userId") Long id) {
        UserDto userDto = userService.find(id);
        if (null == userDto) {
            throw new ResourceNotFoundException();
        }

        return userDto;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserDto create(@RequestBody CreateUserDto userDto) {
        return userService.create(userDto);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public UserDto update(@PathVariable("userId") Long userId, @RequestBody CreateUserDto userDto) {
        return userService.update(userId, userDto);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("userId") Long userId) {
        UserDto userDto = userService.find(userId);
        if (null == userDto) {
            throw new ResourceNotFoundException();
        }

        userService.delete(userDto.getId());
    }
}
