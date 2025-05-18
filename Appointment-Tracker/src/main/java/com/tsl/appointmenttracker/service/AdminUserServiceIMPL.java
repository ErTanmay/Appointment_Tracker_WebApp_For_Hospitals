package com.tsl.appointmenttracker.service;

import com.tsl.appointmenttracker.dao.AdminUserDAO;
import com.tsl.appointmenttracker.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserServiceIMPL implements AdminUserService {

    private AdminUserDAO adminUserDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAdminUserDAO(AdminUserDAO adminUserDAO) {
        this.adminUserDAO = adminUserDAO;
    }

    @Override
    public String checkAdminCredentials(String oldusername, String oldpassword) {

        Optional<AdminUser> admin = adminUserDAO.findByUsername(oldusername);

        if(admin.isPresent()){

            AdminUser admin1 = admin.get();
            String password = admin1.getPassword();

            if(passwordEncoder.matches(oldpassword, password)){
                return "SUCCESS";
            }else{
                return "Wrong Credentials";
            }
        }else{
            return "Wrong Credentials";
        }
    }

    @Override
    public String updateAdminCredentials(String oldusername, String newusername, String newpassword) {
        int resultCount = adminUserDAO.updateCredentials(oldusername, newusername, passwordEncoder.encode(newpassword));

        if(resultCount==1){
            return "SUCCESS";
        }else{
            return "FAILURE";
        }
    }
}
