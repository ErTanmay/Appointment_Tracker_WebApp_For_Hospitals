package com.tsl.appointmenttracker.service;

import com.tsl.appointmenttracker.model.AdminUser;
import org.springframework.stereotype.Service;

@Service
public interface AdminUserService {


    public String checkAdminCredentials(String oldusername, String oldpassword);

    String updateAdminCredentials(String oldusername, String newusername, String newpassword);
}
