package com.gaoice.cloud.nebula.service.user.repository;

import com.gaoice.cloud.nebula.module.jpa.repository.BaseJpaRepository;
import com.gaoice.cloud.nebula.service.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author gaoice
 */
@Repository
public interface UserRepository extends BaseJpaRepository<User> {

    User findByUsername(String username);
}
