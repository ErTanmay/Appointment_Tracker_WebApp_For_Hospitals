package com.tsl.appointmenttracker.service;

import com.tsl.appointmenttracker.dao.AppointmentDeatilsDAO;
import com.tsl.appointmenttracker.model.AppointmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceIMPL implements AppointmentService {

    private AppointmentDeatilsDAO appointmentDeatilsDAO;

    @Autowired
    public void setAppointmentDeatilsDAO(AppointmentDeatilsDAO appointmentDeatilsDAO) {
        this.appointmentDeatilsDAO = appointmentDeatilsDAO;
    }

    @Override
    public AppointmentDetails addAppointment(AppointmentDetails appointmentDetails) {
        return appointmentDeatilsDAO.save(appointmentDetails);
    }

    @Override
    public List<AppointmentDetails> getAllAppointments() {
        return appointmentDeatilsDAO.findAll();
    }

    @Override
    public void deleteAppointment(int id) {
        appointmentDeatilsDAO.deleteById(id);
    }
}
