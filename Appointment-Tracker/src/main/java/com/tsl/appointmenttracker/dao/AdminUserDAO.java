package com.tsl.appointmenttracker.dao;

import com.tsl.appointmenttracker.model.AdminUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserDAO extends JpaRepository<AdminUser, Integer> {

    Optional<AdminUser> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "update admin_user set username=:newusername, password=:newpassword where username=:oldusername" , nativeQuery = true)
    public int updateCredentials(
            @Param("oldusername") String oldusername,
            @Param("newusername") String newusername,
            @Param("newpassword") String newpassword);
}

