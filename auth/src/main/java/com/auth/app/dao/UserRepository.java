package com.auth.app.dao;

import com.auth.app.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getByUsername(String username);

    @Query("from USERS ue join fetch ue.roles r where r.name=:roleName")
    List<UserEntity> findByRole(@Param("roleName") String roleName);
}
