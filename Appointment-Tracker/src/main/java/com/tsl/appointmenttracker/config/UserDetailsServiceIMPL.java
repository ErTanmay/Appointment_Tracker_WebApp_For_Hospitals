package com.tsl.appointmenttracker.config;

import com.tsl.appointmenttracker.dao.AdminUserDAO;
import com.tsl.appointmenttracker.model.AdminUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService {

    private AdminUserDAO adminDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAdminDAO(AdminUserDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @PostConstruct
    public void init() {

        long count = adminDAO.count();
        if (count == 0) {
            AdminUser admin = new AdminUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            adminDAO.save(admin);
        }

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AdminUser> adminRecord = adminDAO.findByUsername(username);

        AdminUser admin = adminRecord.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.withUsername(admin.getUsername()).password(admin.getPassword()).build();

    }
}
