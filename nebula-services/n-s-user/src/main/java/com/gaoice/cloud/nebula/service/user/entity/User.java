package com.gaoice.cloud.nebula.service.user.entity;

import com.gaoice.cloud.nebula.module.jpa.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author gaoice
 */
@Getter
@Setter
@Table
@Entity
public class User extends BaseEntity {

    private String username;

    private String password;

    private Boolean enabled = true;
}
