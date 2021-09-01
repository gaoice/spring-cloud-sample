package com.gaoice.cloud.nebula.service.user.controller;

import com.gaoice.cloud.nebula.module.web.controller.AbstractController;
import com.gaoice.cloud.nebula.service.user.entity.User;
import com.gaoice.cloud.nebula.service.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoice
 */
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {

    private final UserRepository userRepository;

    @GetMapping("/username/{username}")
    public User username(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
