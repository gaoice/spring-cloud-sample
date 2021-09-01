package com.gaoice.cloud.nebula.service.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author gaoice
 */
@Data
public class LoginParam {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
