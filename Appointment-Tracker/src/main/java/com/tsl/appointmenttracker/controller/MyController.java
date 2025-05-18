package com.tsl.appointmenttracker.controller;

import com.tsl.appointmenttracker.model.AdminUser;
import com.tsl.appointmenttracker.model.AppointmentDetails;
import com.tsl.appointmenttracker.service.AdminUserService;
import com.tsl.appointmenttracker.service.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin")
public class MyController {

    private AppointmentService appointmentService;
    private AdminUserService adminUserService;

    @Autowired
    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = {"", "/", "/home"})
    public String homePageView() {
       return "Home";
    }

    @GetMapping("ReadAllAppointments")
    public String reaAllAppointmentsView(Model model) {
        List<AppointmentDetails> list = appointmentService.getAllAppointments();
        model.addAttribute("appointments", list);
        return "ReadAllAppointments";
    }

    @GetMapping("AddAppointments")
    public String readAllAppointmentsView() {
        return "AddAppointments";
    }

    @GetMapping("ChangeAdminCredentials")
    public String changeAdminCredentialsView() {
        return "ChangeAdminCredentials";
    }

    @PostMapping("AddAppointments")
    public String addAppointment(@ModelAttribute AppointmentDetails appointmentDetails,
                               RedirectAttributes redirectAttributes) {
        System.out.println(">>>>>>>>>>>>>>>>>>>" + appointmentDetails.toString());
        AppointmentDetails save = appointmentService.addAppointment(appointmentDetails);

        if(save != null) {
            System.out.println("Appointment added successfully");
            redirectAttributes.addFlashAttribute("messageS", "Appointment added successfully!!!!!");
        } else {
            System.out.println("Failed to add appointment");
            redirectAttributes.addFlashAttribute("messageF", "Failed to add appointment!!!!!!!");
        }
    return "redirect:/admin/AddAppointments";
    }

    @GetMapping("deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        appointmentService.deleteAppointment(id);
        redirectAttributes.addFlashAttribute("message", "Appointment deleted successfully!!!!!");
        return "redirect:/admin/ReadAllAppointments";
    }

    @PostMapping("changecredentials")
    public String addAppointment( @RequestParam("oldusername") String oldusername,
                                  @RequestParam("oldpassword") String oldpassword,
                                  @RequestParam("newusername") String newusername,
                                  @RequestParam("newpassword") String newpassword,
                                  RedirectAttributes redirectAttributes) {

        String result = adminUserService.checkAdminCredentials(oldusername, oldpassword);
        System.out.println("result: " + result);

        if (result.equals("SUCCESS")) {
            String resultStr = adminUserService.updateAdminCredentials(oldusername, newusername, newpassword);
            if(resultStr.equals("SUCCESS")){
                redirectAttributes.addFlashAttribute("message", "CREDENTIALS CHANGED SUCCESSFULLY!!!!!!");
            }else{
                redirectAttributes.addFlashAttribute("message", "SOMETHING WENT WRONG!!!!!!");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "WRONG CREDENTIALS!!!!!!");
        }

        return "redirect:/admin/home";
    }



}
