package com.web.app.dao;

import com.web.app.dao.entity.RoleEntity;
import com.web.app.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity getByName(String name);
}
