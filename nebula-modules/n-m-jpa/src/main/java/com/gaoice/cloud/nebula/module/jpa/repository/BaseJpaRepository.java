package com.gaoice.cloud.nebula.module.jpa.repository;

import com.gaoice.cloud.nebula.module.jpa.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author gaoice
 */
public interface BaseJpaRepository<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
